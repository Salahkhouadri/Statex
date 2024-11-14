package com.cesur.splinterio.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesur.splinterio.models.State;
@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    
}
