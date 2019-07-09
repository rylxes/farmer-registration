package com.register.farmerregistration.local.entities;


import com.register.farmerregistration.local.AfterLogin;
import com.register.farmerregistration.local.generators.Identifiable;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
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
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User extends AppModel implements  Serializable, Identifiable<Integer> {
    /**
     *
     */
    private static final long serialVersionUID = -6856689286048420607L;


    enum statusEnum {
        Active,
        Suspended,
        Inactive
    }

    enum typeENUM {
        Login,
        NONLOGIN
    }

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
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "GEN_SEQ")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;


    private String name;
    private String email;


    @Column(name = "EMAIL_VERIFIED_AT")
    private String emailVerifiedAt;


    @Column(name = "REMEMBER_TOKEN")
    private String rememberToken;

    private String password;
    private String passport;

    @Column(name = "user_type")
    private String userType;


    private String type = typeENUM.NONLOGIN.toString();
    private String status = statusEnum.Active.toString();


    @Transient
    private String error;


}
