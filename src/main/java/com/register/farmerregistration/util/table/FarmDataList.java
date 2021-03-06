package com.register.farmerregistration.util.table;


import com.register.farmerregistration.local.entities.User;
import lombok.Data;

import java.util.Objects;

@Data
public class FarmDataList {

    Integer id;
    String name;
    String no_of_hectares;
    String farmlocation;

    User user;

    public void setUser(User user) {
        this.user = Objects.isNull(user) ? null : user;
        if (user != null) {
            this.name = user.getName();
        }
    }
}
