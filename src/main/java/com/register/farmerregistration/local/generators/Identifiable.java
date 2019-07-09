package com.register.farmerregistration.local.generators;

import java.io.Serializable;

public interface Identifiable<T extends Serializable> {
    T getId();
}
