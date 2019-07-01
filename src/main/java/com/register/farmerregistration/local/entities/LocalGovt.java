package com.register.farmerregistration.local.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "lgas")
public class LocalGovt implements Serializable {

    @Id
    private Integer id;
    private String name;
    @Column(name = "state_id")
    private Integer stateId;


}
