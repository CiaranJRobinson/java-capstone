package com.WalkTracker.Capstone.services;

import com.WalkTracker.Capstone.dtos.StepsDto;

import javax.transaction.Transactional;
import java.util.List;

public interface StepsService {
    @Transactional
    void addSteps(StepsDto stepsDto, Long userId);

    List<StepsDto> getAllStepsByUserId(Long userId);
}
