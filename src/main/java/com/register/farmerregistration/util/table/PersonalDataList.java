package com.register.farmerregistration.util.table;

import com.register.farmerregistration.local.entities.State;
import lombok.Data;

import java.util.Objects;

@Data
public class PersonalDataList {

    int id;
    String phone_no;
    String name;
    String stateName;
    String BVN;
    State state;
    String user_type;


    public void setState(State state) {
        this.state = Objects.isNull(state) ? null : state;
        if (state != null) {
            this.stateName = state.getName();
        }
    }

    public PersonalDataList() {
    }

    public PersonalDataList(int id, String name, String BVN, String phone, State state) {

        this.state = Objects.isNull(state) ? null : state;
        this.id = id;
        this.phone_no = phone;
        this.name = name;
        if (state != null) {
            this.stateName = state.getName();
        }

        this.BVN = BVN;
        //this.user_type = user_type;
    }
}
