package com.register.farmerregistration.local.entities;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.lang.Nullable;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farm_generator")
    @SequenceGenerator(name = "farm_generator", sequenceName = "farm_seq", allocationSize = 50)
    @Column(name = "id", updatable = false, nullable = false)
    Integer id;



    private Long userId;
    private String farmlocation;
    private String no_of_hectares;
    private String coord_a_longitude;
    private String coord_a_latitude;
    private String coord_b_longitude;
    private String coord_b_latitude;
    private String coord_c_longitude;
    private String coord_c_latitude;
    private String coord_d_longitude;
    private String coord_d_latitude;
    private String soil_type;
    private String status;
    private String createdAt;
    private String updatedAt;


    @Nullable
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "userId")
    private User user;




    @Transient
    private String error;


}
