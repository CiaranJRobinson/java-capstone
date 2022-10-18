package com.WalkTracker.Capstone.services;
//moves the data where it needs to go or directing other layers to get the data
//receives data from the repo layer and passes to Controller
//business logic here

import com.WalkTracker.Capstone.dtos.StepsDto;
import com.WalkTracker.Capstone.entities.Steps;
import com.WalkTracker.Capstone.entities.User;
import com.WalkTracker.Capstone.repositories.StepsRepository;
import com.WalkTracker.Capstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StepsServiceImpl implements StepsService {
    @Autowired
    private StepsRepository stepsRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addSteps(StepsDto stepsDto, Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        Steps steps = new Steps(stepsDto);
        userOptional.ifPresent(steps::setUser);
        stepsRepository.saveAndFlush(steps);
    }

    @Override
    public List<StepsDto> getAllStepsByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Steps> stepsList = stepsRepository.findAllByUserEquals(userOptional.get());
            return  stepsList.stream().map(steps -> new StepsDto(steps)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
