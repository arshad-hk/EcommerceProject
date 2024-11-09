package com.scaler.ECOMUserService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.ECOMUserService.dto.SignUpRequestDto;
import com.scaler.ECOMUserService.dto.UserDto;
import com.scaler.ECOMUserService.exception.InvalidCredentialsException;
import com.scaler.ECOMUserService.exception.InvalidTokenException;
import com.scaler.ECOMUserService.exception.UserNotFoundException;
import com.scaler.ECOMUserService.mapper.UserEntityDTOMapper;
import com.scaler.ECOMUserService.model.Role;
import com.scaler.ECOMUserService.model.Session;
import com.scaler.ECOMUserService.model.SessionStatus;
import com.scaler.ECOMUserService.model.User;
import com.scaler.ECOMUserService.repository.SessionRepository;
import com.scaler.ECOMUserService.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    private SecretKey secretKey;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String SECRET_KEY = "yourSecretKeyWhichIsStrongEnoughNotToBreak";

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder
                        , KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.kafkaTemplate = kafkaTemplate;
    }

    public ResponseEntity<UserDto> login(String email, String password) throws UserNotFoundException, InvalidCredentialsException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(String.format("User with email %s not found",email));
        }

        User user = userOptional.get();

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password entered");
        }

        //String token = RandomStringUtils.randomAlphanumeric(30);
        MacAlgorithm alg = Jwts.SIG.HS256; // HS256 algo added for JWT
        SecretKey key = alg.key().build(); // generating the secret key

        Map<String,Object> jwtClaims = new HashMap<>();
        jwtClaims.put("email", user.getEmail());
        jwtClaims.put("roles", user.getRoles());
        jwtClaims.put("createdAt", new Date());
        jwtClaims.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));

        String token = Jwts.builder()
                .claims(jwtClaims)
                .signWith(secretKey)
                .compact();

        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);

        UserDto userDto = UserEntityDTOMapper.convertUserToUserDto(user);

//        Map<String, String> headers = new HashMap<>();
//        headers.put(HttpHeaders.SET_COOKIE, token);

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, token);



        ResponseEntity<UserDto> response = new ResponseEntity<>(userDto, headers, HttpStatus.OK);
//        response.getHeaders().add(HttpHeaders.SET_COOKIE, token);

        return response;
    }

    public ResponseEntity<Void> logout(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (sessionOptional.isEmpty()) {
            return null;
        }

        Session session = sessionOptional.get();

        session.setSessionStatus(SessionStatus.ENDED);

        sessionRepository.save(session);

        return ResponseEntity.ok().build();
    }

    public UserDto signUp(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        
        User savedUser = userRepository.save(user);

        // notify the email service via kafka
        // ideally the dto should consist of only email and some metadata
        // reusing this dto for now
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setPassword(password);
        signUpRequestDto.setEmail(email);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            kafkaTemplate.send("email_topic", objectMapper.writeValueAsString(signUpRequestDto));
        } catch (JsonProcessingException e) {
            System.out.println("Could not send message over kafka. Exception:"+ e);
        }

        return UserDto.from(savedUser);
    }

    public SessionStatus validate(String token, Long userId) {
        System.out.printf("Token is %s and userId is: %d%n",token,userId);
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (sessionOptional.isEmpty() || sessionOptional.get().getSessionStatus()==SessionStatus.ENDED) {
            throw new InvalidTokenException("Token is invalid");
        }

//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), Jwts.SIG.HS256.toString());
//        JwtParser jwtParser = Jwts.parser()
//                .verifyWith(secretKeySpec)
//                .build();
//
//        try {
//            jwtParser.parse(token);
//        }
//        catch (Exception e){
//            throw new InvalidTokenException("Token is invalid!");
//        }

        return SessionStatus.ACTIVE;
    }

    public List<Session> getAllSessions(){
        return sessionRepository.findAll();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
