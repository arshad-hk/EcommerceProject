package com.scaler.ECOMUserService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDto {
    private String token;
    private Long userId;
}
