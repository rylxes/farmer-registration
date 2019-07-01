package com.register.farmerregistration.controller.DataGathering;


import com.register.farmerregistration.local.entities.AgroInputDisbursed;
import com.register.farmerregistration.local.entities.FarmData;
import com.register.farmerregistration.local.entities.FarmData;
import com.register.farmerregistration.local.entities.User;
import com.register.farmerregistration.local.managers.AgroInputDisbursedManager;
import com.register.farmerregistration.local.managers.FarmDataManager;
import com.register.farmerregistration.local.managers.FarmDataManager;
import com.register.farmerregistration.local.managers.UserManager;
import com.register.farmerregistration.util.ComboItems;
import com.register.farmerregistration.util.ItemContent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;


/**
 * Created by sherriff on 13/10/2016.
 */
@Slf4j
@Controller
public class AddDisbursementController implements Initializable {

    private String usrId;
    public Integer dataID;
    @FXML
    private TextField quantity;
    @FXML
    private ComboBox<ItemContent> userId = new ComboBox<>();
    @FXML
    private ComboBox<ItemContent> category = new ComboBox<>();
    @FXML
    private ComboBox<ItemContent> variety = new ComboBox<>();
    @FXML
    private ComboBox<ItemContent> unit = new ComboBox<>();
    @FXML
    Label lblContent;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    @FXML
    public Button btnClose;
    @FXML
    public Label lblCaption;
    private Stage primaryStage;
    @FXML
    private AnchorPane apContent;
    ObservableList<String> countryObList;


    @Autowired
    AgroInputDisbursedManager disbursedDataManager;

    @Autowired
    ComboItems comboItems;

//    public Long productValue, locationValue;
//
//    private String locationValueString, productValueString;


    public void initialize(URL url, ResourceBundle rb) {
        comboItems.setCategoryCombo(category);
        comboItems.setVarietyCombo(variety);
        comboItems.setUnitCombo(unit);
        comboItems.setUserCombo(userId);
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) throws ParseException {
        Long id = 0L;
        try {
            AgroInputDisbursed dataH = new AgroInputDisbursed();
            //dataH.setCategory(comboItems.changeComboBox(category));
            dataH.setInput_type(comboItems.changeComboBox(category));
            dataH.setVariety(comboItems.changeComboBox(variety));
            dataH.setQuantity(Integer.valueOf(quantity.getText()));
            dataH.setUnit(comboItems.changeComboBox(unit));
//            dataH.setSoil_type(soil_type.getText());
//            dataH.setUserId(comboItems.changeComboBoxLong(userId));
            disbursedDataManager.save(dataH, btnSave);


        } catch (Exception ex) {
            ex.getStackTrace();
        }


    }


    @FXML
    private void btnUpdateOnAction(ActionEvent event) throws ParseException {
        Boolean issaved = false;
        try {

            AgroInputDisbursed dataH = new AgroInputDisbursed();
            dataH.setInput_type(comboItems.changeComboBox(category));
            dataH.setVariety(comboItems.changeComboBox(variety));
            dataH.setQuantity(Integer.valueOf(quantity.getText()));
            dataH.setUnit(comboItems.changeComboBox(unit));
//            dataH.setNo_of_hectares(no_of_hectares.getText());
//            dataH.setCoord_a_longitude(coord_a_longitude.getText());
//            dataH.setCoord_a_latitude(coord_a_latitude.getText());
//            dataH.setFarmlocation(farmlocation.getText());
//            dataH.setSoil_type(soil_type.getText());
//            dataH.setUserId(comboItems.changeComboBoxLong(userId));
            dataH.setId(((int) dataID));
            disbursedDataManager.update(dataH, btnSave);

        } catch (Exception ex) {
            ex.getStackTrace();
        }


    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnUpdate.getScene().getWindow();
        stage.close();
    }

    public void showDetails() {
        try {


            AgroInputDisbursed theData = disbursedDataManager.findById(Long.valueOf(this.dataID));

            //comboItems.loadDisbusementCombo(theData.getCategory());
//            quantity.setText(theData.getCoord_a_longitude());
//            unit.setText(theData.geUnit());
//            farmlocation.setText(theData.getFarmlocation());
//            soil_type.setText(theData.getSoil_type());

            comboItems.loadUser(userId, theData.getUserId());
        } catch (Exception ex) {
            log.error("Exception caught", ex);
        }

    }

    @FXML
    private void apOnMouseDragged(MouseEvent event) {
    }

    @FXML
    private void apOnMousePressed(MouseEvent event) {
    }

    public void addSupplierStage(Stage stage) {
    }
}
