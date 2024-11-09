package com.scaler.ECOMUserService.controller;

import com.scaler.ECOMUserService.dto.SetUserRolesRequestDto;
import com.scaler.ECOMUserService.dto.UserDto;
import com.scaler.ECOMUserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getUserDetails(userId);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDto> setUserRoles(@PathVariable("id") Long userId, @RequestBody SetUserRolesRequestDto request) {

        UserDto userDto = userService.setUserRoles(userId, request.getRoleIds());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


}
