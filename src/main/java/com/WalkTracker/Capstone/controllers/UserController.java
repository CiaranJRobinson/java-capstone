package com.WalkTracker.Capstone.controllers;
//creates API endpoints and their actions, only interacts with the Service layer

import com.WalkTracker.Capstone.dtos.UserDto;
import com.WalkTracker.Capstone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//label as controller, and map to specific url endpoints
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    //Autowire in our Dependencies, which are the UserService because Controllers interact
    // with ServiceLayers and the PasswordEncoder so that we can grab incoming passwords
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto) {
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        return userService.addUser((userDto));
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto){
        return userService.userLogin(userDto);
    }
}
