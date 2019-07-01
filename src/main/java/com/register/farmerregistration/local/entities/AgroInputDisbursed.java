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


    public enum categoryEnum {
        Seed,
        Fertilizer,
        Others,
        Chemical
    }

    public enum varietyEnum {
        OPV,
        HYBRID,
        NPK,
        UREA,
        PRE,
        POST
    }

    public enum unitEnum {
        Bag,
        Pieces,
        Litres,
        Kg,
        Others
    }

    // Data members


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "input_generator")
    @SequenceGenerator(name = "input_generator", sequenceName = "input_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    Integer id;

//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;

    //@Column(name = "user_id")
    private Integer userId;
    private String input_type;
    private String unit;
    private String variety;
    private String status;
    private Integer quantity;
    private categoryEnum category = categoryEnum.Seed;
    private String createdAt;
    private String updatedAt;


    @Nullable
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "user_Id")
    private User user;

    @Transient
    private String error;


}
