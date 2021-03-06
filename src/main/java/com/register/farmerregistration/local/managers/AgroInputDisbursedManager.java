package com.register.farmerregistration.local.managers;

import com.register.farmerregistration.local.annotation.EntityAnnotation;
import com.register.farmerregistration.local.entities.AgroInputDisbursed;
import com.register.farmerregistration.local.repository.AgroInputDisbursedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* Generated by Spring Data Generator on 22/06/2019
*/
@Component
@EntityAnnotation(entityClass = AgroInputDisbursed.class)
public class AgroInputDisbursedManager extends BaseManager<AgroInputDisbursed, Long> {

	private AgroInputDisbursedRepository agroInputDisbursedRepository;

	@Autowired
	public AgroInputDisbursedManager(AgroInputDisbursedRepository agroInputDisbursedRepository) {
		this.agroInputDisbursedRepository = agroInputDisbursedRepository;
	}

	public List<AgroInputDisbursed> findAllByUserType(String useType) {
		return agroInputDisbursedRepository.findByUser_UserType(useType);
	}
}
