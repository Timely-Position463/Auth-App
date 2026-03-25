package com.portfolio.project.Auth_App.services;

import com.portfolio.project.Auth_App.dto.UserDto;

public interface AuthService {
    UserDto registerUser(UserDto userDto);
}
