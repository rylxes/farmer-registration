package com.register.farmerregistration.local.entities;


import com.register.farmerregistration.local.AfterLogin;
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
@Table(name = "IUsers", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User extends AppModel implements Serializable {
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
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String password;
    private String passport;
    private String user_type;
    private String type = typeENUM.NONLOGIN.toString();
    private String status = statusEnum.Active.toString();
    @Nullable
    private String email_verified_at;


    private String createdAt;
    private String updatedAt;


    @Transient
    private String error;


}
