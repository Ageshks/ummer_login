package com.example.aquafin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.aquafin.Dto.UserDto;
import com.example.aquafin.models.User;
import com.example.aquafin.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(UserDto userDto) {
        // Default role is "USER", can be overridden with provided role
        String role = userDto.getRole() != null ? userDto.getRole() : "USER"; 

        User user = new User(
            userDto.getEmail(),
            passwordEncoder.encode(userDto.getPassword()),
            userDto.getFullname(),
            role
        );
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
