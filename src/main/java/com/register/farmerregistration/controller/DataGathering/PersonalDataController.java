package com.register.farmerregistration.controller.DataGathering;


import com.register.farmerregistration.controller.BaseController;
import com.register.farmerregistration.local.entities.PersonalData;
import com.register.farmerregistration.local.managers.PersonalDataManager;
import com.register.farmerregistration.util.WindowsUtils;
import com.register.farmerregistration.util.table.PersonalDataList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.register.farmerregistration.FarmerRegistrationApplication.springContext;

;

/**
 * Created by sherriff on 13/10/2016.
 */
@Slf4j
@Controller
public class PersonalDataController extends BaseController implements Initializable {

    public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
    public static final String PATH_FXML = "/fxml/data-gathering/PersonalData.fxml";
    @FXML
    private AnchorPane acSupplierMainContent;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<PersonalDataList> tblData;
    ObservableList<PersonalDataList> myDataDetails = FXCollections.observableArrayList();

    @FXML
    private Button btnRefresh;


    @Autowired
    PersonalDataManager personalDataManager;

    String userId;

    @Override
    protected void onClose() {

    }

    public void initialize(URL location, ResourceBundle resources) {
        loadTbl();
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(
                new PropertyValueFactory<>("name"));


        TableColumn bvn = new TableColumn("BVN");
        bvn.setCellValueFactory(
                new PropertyValueFactory<>("BVN"));


        TableColumn phone = new TableColumn("Phone");
        phone.setCellValueFactory(
                new PropertyValueFactory<>("phone_no"));


        TableColumn state = new TableColumn("State");
        state.setCellValueFactory(
                new PropertyValueFactory<>("stateName"));


        tblData.getColumns().addAll(name, bvn, phone, state);
        tfSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(oldValue, newValue);
            }
        });

    }

    public void search(String oldVal, String newVal) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        if (oldVal != null && (newVal.length() < oldVal.length())) {
            // Restore the lists original set of entries
            // and start from the beginning
            tblData.setItems(myDataDetails);
        }

        // Break out all of the parts of the search text
        // by splitting on white space
        String[] parts = newVal.toUpperCase().split(" ");

        // Filter out the entries that don't contain the entered text
        ObservableList<PersonalDataList> subentries = FXCollections.observableArrayList();
        for (PersonalDataList entry : tblData.getItems()) {

            boolean match = true;
            String entryText = entry.getName();
            int entryint = entry.getId();
            for (String part : parts) {
                // The entry needs to contain all portions of the
                // search string *but* in any order
                if (!entryText.toUpperCase().contains(part)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                PersonalDataList personalData = new PersonalDataList();
                personalData.setId(entryint);
                personalData.setName(entry.getName());
                personalData.setBVN(entry.getBVN());
                personalData.setPhone_no(entry.getPhone_no());
                personalData.setState(entry.getState());
                subentries.add(personalData);
            }
        }
        tblData.setItems(subentries);
    }


    @FXML
    private void tfSearchOnKeyReleased(Event event) {
    }

    @Autowired
    AddPersonalDataController addPersonalDataController;

    @FXML
    private void btnAddOnAction(ActionEvent event) {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setControllerFactory(springContext::getBean);
        fXMLLoader.setLocation(this.getClass().getResource("/fxml/data-gathering/AddPersonalData.fxml"));

        try {
            fXMLLoader.load();
            Parent ex = (Parent) fXMLLoader.getRoot();
            Scene scene = new Scene(ex);
            scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
            addPersonalDataController = (AddPersonalDataController) fXMLLoader.getController();
            addPersonalDataController.lblContent.setText("Add Personal Data");
            addPersonalDataController.btnUpdate.setVisible(false);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(true);
            stage.show();
        } catch (IOException var9) {
            log.error(var9.getMessage());
        }

    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (this.tblData.getSelectionModel().getSelectedItem() != null) {
            this.selectedView();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "EMPTY SELECTION");
            alert.initModality(Modality.APPLICATION_MODAL);
            // This will not work in your code
            alert.initOwner(tfSearch.getScene().getWindow());
            alert.show();
        }

    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (this.tblData.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("CONFIRM");
            alert.setContentText("Are You Sure to Delete this item??");
            alert.initStyle(StageStyle.UNDECORATED);
            Optional result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                PersonalDataList PersonalData = (PersonalDataList) this.tblData.getSelectionModel().getSelectedItem();
                int item = PersonalData.getId();
                try {
                    personalDataManager.delete(item);
                } catch (Exception ex) {
                    log.error("Exception caught", ex);
                }
                this.tblData.getItems().clear();
                this.showDetails();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "EMPTY SELECTION");
            alert.initModality(Modality.APPLICATION_MODAL);
            // This will not work in your code
            alert.initOwner(tfSearch.getScene().getWindow());
            alert.show();
        }


    }

    public void showDetails() {


        try {
            List<PersonalData> allData = personalDataManager.findAllByUserType("user");
            for (PersonalData eachData : allData) {

                try {
                    PersonalDataList personalData = new PersonalDataList();
                    personalData.setId(eachData.getId());
                    personalData.setName(eachData.getName());
                    personalData.setBVN(eachData.getBVN());
                    personalData.setPhone_no(eachData.getPhone_no());
                    personalData.setState(eachData.getState());
                    myDataDetails.add(personalData);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
            tblData.setItems(myDataDetails);
        } catch (Exception var3) {
            log.error("Exception caught", var3);
        }

    }


    public void selectedView() {
        PersonalDataList PersonalData = (PersonalDataList) this.tblData.getSelectionModel().getSelectedItem();
        String item = String.valueOf(PersonalData.getId());
        if (!item.isEmpty()) {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setControllerFactory(springContext::getBean);
            fXMLLoader.setLocation(this.getClass().getResource("/fxml/data-gathering/AddPersonalData.fxml"));

            try {
                fXMLLoader.load();
                Parent ex = (Parent) fXMLLoader.getRoot();
                Scene scene = new Scene(ex);
                scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
                addPersonalDataController = (AddPersonalDataController) fXMLLoader.getController();
                addPersonalDataController.lblContent.setText("Personal Data Details");
                addPersonalDataController.btnSave.setVisible(false);
                addPersonalDataController.dataID = PersonalData.getId();
                addPersonalDataController.showDetails();
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
            } catch (IOException var10) {
                log.error(var10.getMessage());
            }
        }

    }

    @FXML
    private void tblDataOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            this.selectedView();
        } else {
        }

    }

    void loadTbl() {
        myDataDetails.clear();
        this.showDetails();
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        loadTbl();
    }

    @FXML
    private void mbView(ActionEvent event) {
        this.btnUpdateOnAction(event);
    }

    @FXML
    private void mbViewHistory(ActionEvent event) {
    }

    @FXML
    private void mbEdit(ActionEvent event) {
        this.btnUpdateOnAction(event);
        // this.tfSearchOnType(event);
    }

    @FXML
    private void tblDataOnKeyPress(KeyEvent event) {
    }

    @FXML
    private void mbAddNew(ActionEvent event) {
        this.btnAddOnAction(event);
    }
}
