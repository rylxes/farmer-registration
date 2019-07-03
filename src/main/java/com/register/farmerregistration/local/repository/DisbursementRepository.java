package com.register.farmerregistration.local.repository;

import com.register.farmerregistration.local.entities.AgroInputDisbursed;
import com.register.farmerregistration.local.entities.FarmData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Generated by Spring Data Generator on 22/06/2019
 */
@Repository
public interface DisbursementRepository extends BaseRepository<AgroInputDisbursed, Integer> {
    List<AgroInputDisbursed> findByUser_UserType(String userType);
}
