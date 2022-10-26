package com.WalkTracker.Capstone.services;
//moves the data where it needs to go or directing other layers to get the data

import com.WalkTracker.Capstone.dtos.UserDto;
import com.WalkTracker.Capstone.entities.User;
import com.WalkTracker.Capstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    //resolve dependencies needed for things to work, autowired pulls them in from repo
    @Autowired
    private UserRepository userRepository;
    //pulls from config file
    @Autowired
    private PasswordEncoder passwordEncoder;
    //transactional saves to the database and resolves openings
    @Override
    @Transactional
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        //saves info to the db and flushes it
        userRepository.saveAndFlush(user);
        response.add("http://localhost:8080/home.html");
        return response;
    }

    @Override
    public List<String> userLogin(UserDto userDto){
        List<String> response = new ArrayList<>();
        //Optional is a box to hold the possibility of an entry. This is so if it is null, our code doesn't blow up
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        //^grabbed the username from the repository if it matches what was given in the Dto
        //checks to see if the username exists in repo
        if (userOptional.isPresent()){
            //compare the dto password entry with the repo password
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
                response.add("http://localhost:8080/steps.html");
                //adds ID to the response so know who's logged in
                response.add(String.valueOf(userOptional.get().getId()));
            }else{
                response.add("Username or password is invalid");
            }
        }else{
            response.add("Username of password is invalid");
        }
        return response;
    }

}
