package com.cesur.splinterio.services;


import java.util.List;

import com.cesur.splinterio.models.State;

public interface StateService {
    State saveState(State state);
    State getStateById(Long id);
    List<State> getAllStates();
    void deleteStateById(Long id);
}
