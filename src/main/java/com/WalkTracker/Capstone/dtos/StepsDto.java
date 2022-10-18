package com.WalkTracker.Capstone.dtos;

import com.WalkTracker.Capstone.entities.Steps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepsDto implements Serializable {
    private Long stepsId;
    private Long stepCount;
    private Date dateOfWalk;

    private UserDto userDto;
    //constructor that pulls over the entity
    public StepsDto(Steps steps){
        if (steps.getStepsId() != null){
            this.stepsId = steps.getStepsId();
        }
        if (steps.getStepCount() != null){
            this.stepCount = steps.getStepCount();
        }
        if (steps.getDateOfWalk() != null){
            this.dateOfWalk = steps.getDateOfWalk();
        }
    }
}
