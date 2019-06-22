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
@Table(name = "agro_input_disbursed")
public class AgroInputDisbursed extends AppModel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6856689286048420607L;

    /**
     *
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID1 = 1L;


    enum categoryEnum {
        Seed,
        Fertilizer,
        Others,
        Chemical
    }

    // Data members

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Integer userID;
    private String input_type;
    private String unit;
    private String variety;
    private String status;
    private Integer quantity;
    private categoryEnum category = categoryEnum.Seed;
    private String createdAt;
    private String updatedAt;


    @Transient
    private String error;


}
