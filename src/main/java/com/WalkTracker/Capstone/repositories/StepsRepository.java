package com.WalkTracker.Capstone.repositories;

import com.WalkTracker.Capstone.entities.Steps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//like a warehouse worker, gets the data from the database, hands it to the Service Layer
//only interacts with the service and entities layers

@Repository
public interface StepsRepository extends JpaRepository<Steps, Long> {

}
