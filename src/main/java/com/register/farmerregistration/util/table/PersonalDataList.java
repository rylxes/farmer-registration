package com.register.farmerregistration.util.table;

import lombok.Data;

@Data
public class PersonalDataList {

    int id;
    String phone_no;
    String name;
    String stateName;
    String BVN;


    public PersonalDataList() {
    }

    public PersonalDataList(int id, String name, String BVN, String phone, String stateName) {
        this.id = id;
        this.phone_no = phone;
        this.name = name;
        this.stateName = stateName;
        this.BVN = BVN;
    }
}
