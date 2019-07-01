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
@Table(name = "states")
public class State implements Serializable {

    @Id
    private Integer id;

    private String name;


}
