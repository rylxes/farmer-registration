package com.register.farmerregistration.controller.DataGathering;


import com.register.farmerregistration.local.entities.PersonalData;
import com.register.farmerregistration.local.entities.User;
import com.register.farmerregistration.local.managers.PersonalDataManager;
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
public class AddPersonalDataController implements Initializable {

    private String usrId;
    public Integer dataID;
    @FXML
    private TextField name, resident, town, BVN, phone_no = new TextField();
    @FXML
    private ComboBox<ItemContent> title, gender, lga, stateId = new ComboBox<>();
    @FXML
    private TextArea farmaddress = new TextArea();
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
    PersonalDataManager personalDataManager;

    @Autowired
    UserManager userManager;

    @Autowired
    ComboItems comboItems;

    public Long productValue, locationValue;

    private String locationValueString, productValueString;


    public void initialize(URL url, ResourceBundle rb) {
        comboItems.setStateLgaCombo(stateId, lga);
        comboItems.setTitleCombo(title);
        comboItems.setGenderCombo(gender);
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) throws ParseException {
        Long id = 0L;
        try {
            User user = new User();
            user.setName(name.getText());
            User result = userManager.saveModel(user);
            if (result != null) {
                PersonalData personalDataH = new PersonalData();
                personalDataH.setTitle(comboItems.changeComboBox(title));
                personalDataH.setGender(comboItems.changeComboBox(gender));
                personalDataH.setName(name.getText());
                personalDataH.setResident(resident.getText());
                personalDataH.setTown(town.getText());
                personalDataH.setFarmaddress(farmaddress.getText());
                personalDataH.setState_id(((int) comboItems.changeComboBoxInt(stateId)));
                personalDataH.setLga((comboItems.changeComboBoxInt(lga)));
                personalDataH.setBVN(BVN.getText());
                personalDataH.setPhone_no(phone_no.getText());
                personalDataH.setUserId(((int) result.getId()));
                personalDataManager.save(personalDataH, btnSave);
            }


        } catch (Exception ex) {
            ex.getStackTrace();
        }


    }


    @FXML
    private void btnUpdateOnAction(ActionEvent event) throws ParseException {
        Boolean issaved = false;
        try {

            PersonalData personalDataH = new PersonalData();
            personalDataH.setTitle(comboItems.changeComboBox(title));
            personalDataH.setGender(comboItems.changeComboBox(gender));
            personalDataH.setName(name.getText());
            personalDataH.setResident(resident.getText());
            personalDataH.setTown(town.getText());
            personalDataH.setFarmaddress(farmaddress.getText());
            personalDataH.setState_id(((int) comboItems.changeComboBoxInt(stateId)));
            personalDataH.setLga((comboItems.changeComboBoxInt(lga)));
            personalDataH.setBVN(BVN.getText());
            personalDataH.setPhone_no(phone_no.getText());
            personalDataH.setId(((int) dataID));
            personalDataManager.update(personalDataH, btnSave);

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


            PersonalData theData = personalDataManager.findById(this.dataID);


            name.setText(theData.getName());
            resident.setText(theData.getName());
            town.setText(theData.getTown());
            farmaddress.setText(theData.getFarmaddress());
            BVN.setText(theData.getBVN());
            phone_no.setText(theData.getPhone_no());

            comboItems.loadCombo(stateId, lga, theData.getState_id(), theData.getLga());
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
