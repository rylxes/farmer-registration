package com.register.farmerregistration.controller.DataGathering;


import com.register.farmerregistration.local.entities.FarmData;
import com.register.farmerregistration.local.entities.FarmData;
import com.register.farmerregistration.local.entities.User;
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
public class AddFarmDataController implements Initializable {

    private String usrId;
    public long dataID;
    @FXML
    private TextField coord_a_longitude, coord_a_latitude, soil_type, no_of_hectares = new TextField();
    @FXML
    private ComboBox<ItemContent> userId = new ComboBox<>();
    @FXML
    private TextArea farmlocation = new TextArea();
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
    FarmDataManager personalDataManager;

    @Autowired
    ComboItems comboItems;

    public Long productValue, locationValue;

    private String locationValueString, productValueString;


    public void initialize(URL url, ResourceBundle rb) {
        comboItems.setUserCombo(userId);
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) throws ParseException {
        Long id = 0L;
        try {
            FarmData dataH = new FarmData();
            dataH.setNo_of_hectares(no_of_hectares.getText());
            dataH.setCoord_a_longitude(coord_a_longitude.getText());
            dataH.setCoord_a_latitude(coord_a_latitude.getText());
            dataH.setFarmlocation(farmlocation.getText());
            dataH.setSoil_type(soil_type.getText());
            dataH.setUserId(comboItems.changeComboBoxLong(userId));
            personalDataManager.save(dataH, btnSave);


        } catch (Exception ex) {
            ex.getStackTrace();
        }


    }


    @FXML
    private void btnUpdateOnAction(ActionEvent event) throws ParseException {
        Boolean issaved = false;
        try {

            FarmData dataH = new FarmData();
            dataH.setNo_of_hectares(no_of_hectares.getText());
            dataH.setCoord_a_longitude(coord_a_longitude.getText());
            dataH.setCoord_a_latitude(coord_a_latitude.getText());
            dataH.setFarmlocation(farmlocation.getText());
            dataH.setSoil_type(soil_type.getText());
            dataH.setUserId(comboItems.changeComboBoxLong(userId));
            dataH.setId(((int) dataID));
            personalDataManager.update(dataH, btnSave);

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


            FarmData theData = personalDataManager.findById(this.dataID);


            no_of_hectares.setText(theData.getNo_of_hectares());
            coord_a_longitude.setText(theData.getCoord_a_longitude());
            coord_a_latitude.setText(theData.getCoord_a_latitude());
            farmlocation.setText(theData.getFarmlocation());
            soil_type.setText(theData.getSoil_type());

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
