package com.register.farmerregistration.local.managers;

import com.register.farmerregistration.local.annotation.EntityAnnotation;
import com.register.farmerregistration.local.entities.PersonalData;
import com.register.farmerregistration.local.repository.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Generated by Spring Data Generator on 22/06/2019
 */
@Component
@EntityAnnotation(entityClass = PersonalData.class)

public class PersonalDataManager extends BaseManager<PersonalData, Integer> {


    private PersonalDataRepository personalDataRepository;

    public List<PersonalData> findAllByUserType(String useType) {
        return personalDataRepository.findByUser_UserType(useType);
    }

    @Autowired
    public PersonalDataManager(PersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }

//	public List<PersonalData> findAllByUserType(String userType) {
//		return PersonalDataRepository.findAllByUser_userType(String userType);
//	}
}
