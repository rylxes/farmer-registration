package com.register.farmerregistration.local.entities;


import com.register.farmerregistration.local.generators.Identifiable;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
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
public class FarmData extends AppModel implements Serializable, Identifiable<Integer> {
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
    @GenericGenerator(
            name = "GEN_SEQ",
            strategy = "com.register.farmerregistration.local.generators.AssignedSequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "GEN_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "100000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "GEN_SEQ")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;



    @Column(name = "user_id")
    private Integer user_id;


    @Column(name = "farm_location")
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

    private String status = "Unverified";


    @Nullable
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "user_id")
    private User user;


    @Transient
    private String error;


}
