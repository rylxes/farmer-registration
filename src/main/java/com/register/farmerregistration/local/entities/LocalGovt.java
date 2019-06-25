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
@Table(name = "locals")
public class LocalGovt extends AppModel implements Serializable {

    @Id
    @Column(name = "local_id")
    private Integer id;

    @Column(name = "local_name")
    private String name;

    @Column(name = "state_id")
    private Integer stateId;


}
