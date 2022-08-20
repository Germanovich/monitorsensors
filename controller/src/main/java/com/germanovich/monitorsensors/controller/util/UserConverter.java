package com.germanovich.monitorsensors.controller.util;

import com.germanovich.monitorsensors.dto.user.UserDto;
import com.germanovich.monitorsensors.model.user.User;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserConverter {

    public User convertUserDtoToUser(final UserDto userDto) {

        return User.builder()
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto convertUserToUserDto(final User user) {

        return UserDto.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }

    public List<UserDto> convertUserListToUserDtoList(final List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        userList.forEach(user -> {
            var userDto = convertUserToUserDto(user);
            if (userDto != null) {
                userDtoList.add(userDto);
            }
        });
        return userDtoList;
    }
}
