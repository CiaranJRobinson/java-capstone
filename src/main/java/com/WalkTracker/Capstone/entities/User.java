package com.WalkTracker.Capstone.entities;

import com.WalkTracker.Capstone.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity //tells spring that this class is being mapped to a data source
@Table(name="Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private Date dateOfBirth;

    //connect the primary key to foreign key
    @OneToMany(mappedBy = "user", fetch= FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Steps> stepsSet = new HashSet<>();

    //getters and setters created

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    //Constructors pulling in Dto and preventing null exceptions
    public User(UserDto userDto){
        if (userDto.getUsername() != null){
            this.username = userDto.getUsername();
        }
        if (userDto.getPassword() != null){
            this.password = userDto.getPassword();
        }
        if (userDto.getEmail() != null){
            this.email = userDto.getEmail();
        }
        if (userDto.getDateOfBirth() != null){
            this.dateOfBirth = userDto.getDateOfBirth();
        }
    }
}
