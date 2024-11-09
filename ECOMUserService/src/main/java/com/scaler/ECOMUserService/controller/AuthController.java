package com.scaler.ECOMUserService.controller;

import com.scaler.ECOMUserService.dto.*;
import com.scaler.ECOMUserService.model.Session;
import com.scaler.ECOMUserService.model.SessionStatus;
import com.scaler.ECOMUserService.model.User;
import com.scaler.ECOMUserService.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto request) {
        return authService.login(request.getEmail(), request.getPassword());
//        return null;
    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<Void> logout(@PathVariable("id")Long userId, @RequestHeader("token")String token) {
        return authService.logout(token, userId);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto request) {
        UserDto userDto = authService.signUp(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDto request) {
        SessionStatus sessionStatus = authService.validate(request.getToken(), request.getUserId());

        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }


    // below APIs are for practice only
    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getAllSessions(){
        List<Session> allSessions = authService.getAllSessions();
        return ResponseEntity.ok(allSessions);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = authService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

}
