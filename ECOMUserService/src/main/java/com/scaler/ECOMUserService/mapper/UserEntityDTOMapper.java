package com.scaler.ECOMUserService.mapper;

import com.scaler.ECOMUserService.dto.UserDto;
import com.scaler.ECOMUserService.model.User;

public class UserEntityDTOMapper {

    public static UserDto convertUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
