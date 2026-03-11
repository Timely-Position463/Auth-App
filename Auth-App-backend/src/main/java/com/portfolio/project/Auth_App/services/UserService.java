package com.portfolio.project.Auth_App.services;

import com.portfolio.project.Auth_App.dto.UserDto;

public interface UserService {
    // create user
    UserDto createUser(UserDto userDto);

    // get user by email
    UserDto getUSerByEmail(String email);

    //update user by id
    UserDto updateUserById(UserDto userDto, String userId);

    void deleteUser(String userId);

    UserDto getUserById(String userId);

    Iterable<UserDto> getAllUsers();
}
