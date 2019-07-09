package com.register.farmerregistration.local.managers;

import com.register.farmerregistration.local.entities.State;
import com.register.farmerregistration.local.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* Generated by Spring Data Generator on 23/06/2019
*/
@Component
public class StateManager extends BaseManager<State, Integer> {

	private StateRepository stateRepository;

	@Autowired
	public StateManager(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}

}
