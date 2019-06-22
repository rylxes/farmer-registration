package com.register.farmerregistration.local.entities;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;



@Entity
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "personal_data")
public class PersonalData extends AppModel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6856689286048420607L;

    /**
     *
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID1 = 1L;


    enum titleEnum {
        MR,
        MRS,
        MISS
    }


    enum genderEnum {
        MALE,
        FEMALE
    }

    private static String MR = "Mr";
    private static String MRS = "Mrs";
    private static String MISS = "Miss";

    // Data members

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Integer userID;
    private titleEnum title = titleEnum.MR;
    private String name;
    private String town;
    private String lga;
    private Integer state_id;
    private String phone_no;
    private String bio;
    private String passport;
    private String BVN;
    private flagEnum flag = flagEnum.Active;
    private String createdAt;
    private String updatedAt;


    @Transient
    private String error;


}
