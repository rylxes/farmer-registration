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
@Table(name = "contacts")
public class Contact extends AppModel implements Serializable {
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
    @Column(name = "id", nullable = false)
    private long id;

    private Integer userID;




    private String name;
    private String phone_no;
    private String email;
    private String details;
    private String createdAt;
    private String updatedAt;


    @Transient
    private String error;


}
