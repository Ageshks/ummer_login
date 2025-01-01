package com.example.aquafin.services;

import java.util.List;

import com.example.aquafin.Dtos.UserDto;
import com.example.aquafin.models.User;

public interface  UserService {
    User save(UserDto userDto);
    List<User> getAllUsers();
}
