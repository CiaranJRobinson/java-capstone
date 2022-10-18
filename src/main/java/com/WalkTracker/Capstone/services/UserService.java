package com.WalkTracker.Capstone.services;

import com.WalkTracker.Capstone.dtos.UserDto;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    //transactional saves to the database and resolves openings
    @Transactional
    List<String> addUser(UserDto userDto);

    List<String> userLogin(UserDto userDto);
}