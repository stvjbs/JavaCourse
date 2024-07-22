package com.andersenlab.spring.dto.dtoMapper;

import com.andersenlab.spring.dto.UserDto;
import com.andersenlab.spring.entity.User;

public class UserDtoMapper {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUserStatus(user.isUserStatus());
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setUserStatus(userDto.isUserStatus());
        return user;
    }
}
