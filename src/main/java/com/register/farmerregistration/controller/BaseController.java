package com.register.farmerregistration.controller;


import com.register.farmerregistration.FarmerRegistrationApplication;
import com.register.farmerregistration.util.I18N;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public abstract class BaseController {

    protected Stage stage;
    protected I18N i18N;

    public <T> void init(Stage stage, HashMap<String, T> parameters) {
        this.stage = stage;
        this.i18N = FarmerRegistrationApplication.i18n;

        this.stage.setOnHiding(e -> {
            onClose();
        });
        this.stage.setOnHidden(e -> {
            onClose();
        });
    }

    public Stage getStage() {
        return stage;
    }

    public I18N getI18N() {
        return i18N;
    }

    public String getWindowTitle(String titleKey) {
        return FarmerRegistrationApplication.i18n.getString(titleKey);
    }

    protected abstract void onClose();
}
