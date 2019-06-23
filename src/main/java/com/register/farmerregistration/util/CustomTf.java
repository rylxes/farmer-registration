package com.register.farmerregistration.util;

/**
 * Created by sherriff on 28/11/2016.
 */
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomTf {
    private static final Logger logger = LoggerFactory.getLogger(CustomTf.class);
    public CustomTf() {
    }

    public void clearTextFieldByButton(TextField value, Button btn) {
        btn.setVisible(false);
        btn.setText((String)null);
        btn.minHeight(35.0D);
        btn.minHeight(25.0D);
        btn.getStylesheets().add("");
        value.setOnKeyReleased((event) -> {
            if(((String)value.textProperty().get()).length() >= 0 && !((String)value.textProperty().get()).equals("")) {
                if(((String)value.textProperty().get()).length() > -1 || !((String)value.textProperty().get()).equals("")) {
                    btn.setVisible(true);
                }
            } else {
                btn.setVisible(false);
            }

        });
        value.setOnKeyTyped((event) -> {
            if(((String)value.textProperty().get()).length() >= 0 && !((String)value.textProperty().get()).equals("")) {
                if(((String)value.textProperty().get()).length() > -1 || !((String)value.textProperty().get()).equals("")) {
                    btn.setVisible(true);
                }
            } else {
                btn.setVisible(false);
            }

        });
        value.setOnKeyPressed((event) -> {
            if(((String)value.textProperty().get()).length() >= 0 && !((String)value.textProperty().get()).equals("")) {
                if(((String)value.textProperty().get()).length() > -1 || !((String)value.textProperty().get()).equals("")) {
                    btn.setVisible(true);
                }
            } else {
                btn.setVisible(false);
            }

        });
        btn.setOnAction((event) -> {
            value.clear();
            btn.setVisible(false);
            value.requestFocus();
        });
    }

    public void numaricTextfield(TextField tField) {
        tField.setOnKeyReleased((event) -> {
            if(!tField.getText().matches("[0-9.]*")) {
                tField.deletePreviousChar();
            }

        });
        tField.setOnKeyPressed((event) -> {
            if(!tField.getText().matches("[0-9.]*")) {
                tField.deletePreviousChar();
            }

        });
        tField.setOnKeyTyped((event) -> {
            if(!tField.getText().matches("[0-9.]*")) {
                tField.deletePreviousChar();
            }

        });
    }
}

