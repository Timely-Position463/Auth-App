package com.portfolio.project.Auth_App.services.Impl;


import com.portfolio.project.Auth_App.dto.UserDto;
import com.portfolio.project.Auth_App.entities.Provider;
import com.portfolio.project.Auth_App.entities.User;
import com.portfolio.project.Auth_App.exceptions.ResourceNotFoundException;
import com.portfolio.project.Auth_App.helpers.UserHelper;
import com.portfolio.project.Auth_App.repositories.UserRepository;
import com.portfolio.project.Auth_App.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        // if any other checks are required...

        User user = modelMapper.map(userDto, User.class);
        user.setProvider(userDto.getProvider() != null ? userDto.getProvider() : Provider.LOCAL);
        //need to assign role to the user
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUSerByEmail(String email) {
        User user=userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not Found with the given email!"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUserById(UserDto userDto, String userId) {
        UUID uID=UserHelper.parseUUID(userId);
        User existingUser=userRepository.findById(uID).orElseThrow(()-> new ResourceNotFoundException("User not found with the given id!"));
        if(userDto.getName()!=null)existingUser.setName(userDto.getName());
        if(userDto.getImage()!=null)existingUser.setImage(userDto.getImage());
        if(userDto.getProvider()!=null)existingUser.setProvider(userDto.getProvider());
        existingUser.setEnable(userDto.isEnable());
        existingUser.setUpdatedAt(Instant.now());
        User user=userRepository.save(existingUser);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        UUID uID= UserHelper.parseUUID(userId);
        User user=userRepository.findById(uID).orElseThrow(()-> new ResourceNotFoundException("User not found with this ID"));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user=userRepository.findById(UserHelper.parseUUID(userId)).orElseThrow(()-> new ResourceNotFoundException("User not found with the given id! "));

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public Iterable<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }
}
