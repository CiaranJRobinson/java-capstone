package com.WalkTracker.Capstone.entities;

import com.WalkTracker.Capstone.dtos.StepsDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name ="Steps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Steps {

    @Column
    private Long stepsId;

    @Column
    private Long stepCount;

    @Column
    private Date dateOfWalk;

    //sets up the foreign key
    @ManyToOne
    @JsonBackReference
    private User user;

    public Long getStepsId() {
        return stepsId;
    }

    public void setUserid(Long userid) {
        this.stepsId = stepsId;
    }

    public Long getSteps() {
        return stepCount;
    }

    public void setSteps(Long steps) {
        this.stepCount = steps;
    }

    public Date getDateOfWalk() {
        return dateOfWalk;
    }

    public void setDateOfWalk(Date dateOfWalk) {
        this.dateOfWalk = dateOfWalk;
    }

    public Steps(StepsDto stepsDto){
        if(stepsDto.getStepsId() != null){
            this.stepsId = stepsDto.getStepsId();
        }
        if(stepsDto.getStepCount() != null){
            this.stepCount = stepsDto.getStepCount();
        }
        if(stepsDto.getDateOfWalk() != null){
            this.dateOfWalk = stepsDto.getDateOfWalk();
        }
    }
}
