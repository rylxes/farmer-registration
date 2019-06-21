package com.register.farmerregistration.local.entities;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "farm_data")
public class FarmData extends AppModel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6856689286048420607L;

    /**
     *
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID1 = 1L;


    @Id
    @Column(name = "id", nullable = false)
    private long id;
    private String userID;
    private String farmlocation;
    private String no_of_hectares;
    private Integer coord_a_longitude;
    private Integer coord_a_latitude;
    private Integer coord_b_longitude;
    private Integer coord_b_latitude;
    private Integer coord_c_longitude;
    private Integer coord_c_latitude;
    private Integer coord_d_longitude;
    private Integer coord_d_latitude;
    private String soil_type;
    private String status;
    private String createdAt;
    private String updatedAt;


    @Transient
    private String error;


}
