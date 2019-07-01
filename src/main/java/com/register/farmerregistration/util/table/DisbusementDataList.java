package com.register.farmerregistration.util.table;

import com.register.farmerregistration.local.entities.User;
import lombok.Data;

import java.util.Objects;

@Data
public class DisbusementDataList {
    Long id;
    String name;
    String category;
    String input_type;
    String variety;
    Integer quantity;
    String unit;
    User user;

    public void setName(String name) {
        this.name = Objects.isNull(name) ? "" : name;
    }

    public void setUser(User user) {
        this.user = Objects.isNull(user) ? null : user;
        if (user != null) {
            this.name = user.getName();
        }

    }
}