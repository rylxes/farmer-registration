package com.register.farmerregistration.local.entities;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.envers.Audited;
import org.springframework.lang.Nullable;

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


    public enum titleEnum {
        MR,
        MRS,
        MISS
    }


    public enum genderEnum {
        MALE,
        FEMALE
    }

    // Data members

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "data_generator")
    @SequenceGenerator(name = "data_generator", sequenceName = "data_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    Integer id;

    @Column(name = "user_id")
    private Integer userId;


    private String title = titleEnum.MR.toString();
    private String gender = genderEnum.MALE.toString();
    private String name;
    private String town;
    private String lga;

//    @Transient
//    private Integer lgaInt = Integer.valueOf(lga);

    @Column(name = "FARM_ADDRESS")
    private String farmaddress;


    @Column(name = "RESIDENTIAL_ADDRESS")
    private String resident;

    private Integer state_id;


    private String phone_no;
    private String bio;

    @Transient
    private String passport;

    @Column(name = "BVN")
    private String BVN;
    private String flag = flagEnum.Active.toString();




    @Nullable
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "state_id")
    private State state;


    @Nullable
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "user_id")
    private User user;

    @Transient
    private String error;


}
