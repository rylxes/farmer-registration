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
@Table(name = "agro_input_disbursed")
public class AgroInputDisbursed extends AppModel implements Serializable, Identifiable<Integer> {
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
    private Integer userId;


    private String input_type;
    private String unit;
    private String variety;
    private String status = "Unverified";
    private Integer quantity;
    private String category = categoryEnum.Seed.toString();


    @Nullable
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "user_id")
    private User user;

    @Transient
    private String error;


}
