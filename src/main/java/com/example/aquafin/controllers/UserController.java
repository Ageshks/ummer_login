package com.example.aquafin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.aquafin.Dto.UserDto;
import com.example.aquafin.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Show registration page
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";  // Thymeleaf template for registration
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerUser(UserDto userDto) {
        userService.save(userDto);
        return "redirect:/login";  // Redirect to login page after registration
    }

    // Show login page
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Thymeleaf template for login
    }

    // Super Admin page (only accessible by Super Admin)
    @GetMapping("/superadmin")
    public String superAdminPage(Model model) {
        model.addAttribute("message", "Welcome to the Super Admin page!");
        return "superadmin";  // Thymeleaf template for Super Admin page
    }

    // Admin page (only accessible by Admin)
    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("message", "Welcome to the Admin page!");
        return "admin";  // Thymeleaf template for Admin page
    }

    // User page (only accessible by User)
    @GetMapping("/user")
    public String userPage(Model model) {
        model.addAttribute("message", "Welcome to the User page!");
        return "user";  // Thymeleaf template for User page
    }

}
