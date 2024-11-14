package com.cesur.splinterio.services.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesur.splinterio.models.State;
import com.cesur.splinterio.repositories.StateRepository;
import com.cesur.splinterio.services.StateService;

@Service
public class StateServiceImpl implements StateService {

    private StateRepository stateRepository;

    @Autowired
    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State saveState(State state) {
        return stateRepository.save(state);
    }

    @Override
    public State getStateById(Long id) {
        Optional<State> st = stateRepository.findById(id);
        if(!st.isPresent()) {
            return null;
        }else{
            return st.get();
        }
    }

    @Override
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    @Override
    public void deleteStateById(Long id) {
        stateRepository.deleteById(id); 
    }

}
