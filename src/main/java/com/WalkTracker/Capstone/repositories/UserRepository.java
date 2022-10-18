package com.WalkTracker.Capstone.repositories;
//like a warehouse worker, gets the data from the database, hands it to the Service Layer
//only interacts with the service and entities layers

import com.WalkTracker.Capstone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
