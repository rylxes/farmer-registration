package com.register.farmerregistration.util.table;

public class PersonalDataList {

    long id;
    String phone_no;
    String name;
    String stateName;
    String BVN;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getBVN() {
        return BVN;
    }

    public void setBVN(String BVN) {
        this.BVN = BVN;
    }

    public PersonalDataList() {
    }

    public PersonalDataList(long id, String name, String BVN, String phone, String stateName) {
        this.id = id;
        this.phone_no = phone;
        this.name = name;
        this.stateName = stateName;
        this.BVN = BVN;
    }
}
