package com.WalkTracker.Capstone.dtos;

import com.WalkTracker.Capstone.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable { //serializable allows this class to be converted and sent outside the app

    private Long id;
    private String username;
    private String password;
    private String email;
    private Date dateOfBirth;

    Set<StepsDto> stepsDtoSet = new HashSet<>();
    //constructor to prevent null point exceptions, "sanity checker"
    public UserDto(User user){
        if (user.getId() != null){
            this.id = user.getId();
        }
        if (user.getUsername() != null){
            this.username = user.getUsername();
        }
        if (user.getPassword() != null){
            this.password = user.getPassword();
        }
        if (user.getEmail() != null){
            this.email = user.getEmail();
        }
        if (user.getDateOfBirth() != null){
            this.dateOfBirth = user.getDateOfBirth();
        }
    }

}
