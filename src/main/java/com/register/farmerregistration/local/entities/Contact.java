package com.register.farmerregistration.local.entities;


import com.register.farmerregistration.local.generators.Identifiable;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "contacts")
public class Contact extends AppModel implements Serializable , Identifiable<Long> {
    /**
     *
     */
    private static final long serialVersionUID = -6856689286048420607L;

    /**
     *
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID1 = 1L;


    // Data members


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
    private Long id;


    @Column(name = "user_id")
    private Integer userId;




    private String name;
    private String phone_no;
    private String email;
    private String details;


    @Transient
    private String error;


}
