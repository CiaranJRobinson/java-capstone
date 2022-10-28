package com.WalkTracker.Capstone.controllers;

import com.WalkTracker.Capstone.dtos.StepsDto;
import com.WalkTracker.Capstone.services.StepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/steps")
public class StepsController {
    @Autowired
    private StepsService stepsService;

    @GetMapping("/user/{userId}")
    public List<StepsDto> getStepsByUser(@PathVariable Long userId){
        return stepsService.getAllStepsByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    public List<StepsDto> addSteps(@RequestBody StepsDto stepsDto, @PathVariable Long userId){
        stepsService.addSteps(stepsDto, userId);
        return stepsService.getAllStepsByUserId(userId); 
    }
}
