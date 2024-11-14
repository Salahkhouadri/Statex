package com.cesur.splinterio.controllers;

import java.nio.file.Path;
import java.util.List;

import org.apache.kafka.common.metrics.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesur.splinterio.models.Incidence;
import com.cesur.splinterio.models.State;
import com.cesur.splinterio.services.IncidenceService;
import com.cesur.splinterio.services.StateService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/state")
public class StateController {


    private StateService stateService;
    private IncidenceService incidenceService;
    @Autowired
    public StateController(StateService stateService, IncidenceService incidenceService) {
        this.stateService = stateService;
    }

    @GetMapping("/")
    public ResponseEntity<List<State>> getAllStates() {
        List<State> states = stateService.getAllStates();
        if(states.size() > 0){
            return ResponseEntity.ok(states);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{idState}")
    public ResponseEntity<State> getStateById(@PathVariable(name="idState")Long idState) {
        State state = stateService.getStateById(idState);
        if(state != null){
            return ResponseEntity.ok(state);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<State> changeActive(@PathVariable Long id, @RequestBody State newState) {
        State stateToUpdate = stateService.getStateById(id);
        if (stateToUpdate != null) {
            stateToUpdate.setActive(newState.getActive());
            stateService.saveState(stateToUpdate);
            return ResponseEntity.ok(stateToUpdate);
        } else {
            return ResponseEntity.notFound().build();
            
        }
    }
    @PostMapping("/")
    public ResponseEntity<State> createState(@RequestBody State state) {
        State stateGuardar =stateService.saveState(state);
        if(stateGuardar!=null){
            return ResponseEntity.ok(state);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStateById(@PathVariable Long id) {
    State state = stateService.getStateById(id);
    if (state != null) {
        stateService.deleteStateById(id);
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
    }
    @GetMapping("/{incidenceId}/{stateId}")
    public ResponseEntity<Incidence> assignStateToIncidence(@PathVariable Long incidenceId, @PathVariable Long stateId) {

    Incidence incidence = incidenceService.getIncidencesById(incidenceId);
    if (incidence == null) {
        return ResponseEntity.notFound().build(); 
    }

    State state = stateService.getStateById(stateId);
    if (state == null) {
        return ResponseEntity.notFound().build();
    }

    incidence.setState(state);
    Incidence updatedIncidence = incidenceService.saveIncidence(incidence); 

    return ResponseEntity.ok(updatedIncidence);
}
    
    
    
}
