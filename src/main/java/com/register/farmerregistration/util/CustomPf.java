package com.register.farmerregistration.util;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sherriff on 28/11/2016.
 */
public class CustomPf {
    private static final Logger logger = LoggerFactory.getLogger(CustomPf.class);
    public CustomPf() {
    }

    public void clearPassFieldByButton(final PasswordField value, final Button btn) {
        btn.setVisible(false);
        value.setOnKeyReleased(event -> {
            KeyCode keyCode = event.getCode();
            if(((String)value.textProperty().get()).length() >= 0 && !((String)value.textProperty().get()).equals("")) {
                if(((String)value.textProperty().get()).length() > -1 || !((String)value.textProperty().get()).equals("")) {
                    btn.setVisible(true);
                }
            } else {
                btn.setVisible(false);
            }
        });
        btn.setOnAction(event -> {
            value.clear();
            btn.setVisible(false);
            value.requestFocus();
        });
    }
}
