package com.register.farmerregistration.controller;

/**
 * Created by sherriff on 27/09/2016.
 */


import com.register.farmerregistration.local.managers.UserManager;
import com.register.farmerregistration.util.CustomPf;
import com.register.farmerregistration.util.CustomTf;
import com.register.farmerregistration.util.Login;
import com.register.farmerregistration.util.WindowsUtils;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

;


@Slf4j
@Controller
public class LoginController extends BaseController implements Initializable {

    public static final String PATH_FXML = "/fxml/Login.fxml";
    public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;

    @FXML
    private TextField tfUserName = new TextField();
    @FXML
    private PasswordField pfUserPassword = new PasswordField();
    private Stage window;
    CustomTf cTF = new CustomTf();
    CustomPf cPF = new CustomPf();
    @FXML
    private AnchorPane acContent;
    @FXML
    private Group groupContent;
    @FXML
    private Button btnUserNameTfClear;
    @FXML
    public Button btnClose, btnMinimize;
    @FXML
    private Label statusLabel;
    @FXML
    private ProgressIndicator progressBar;
    @FXML
    private Button btnPassFieldClear;
    @FXML
    private Button btnLogin, btnCancel;
    private static AnchorPane mainLayout;

    @Override
    protected void onClose() {

    }

    @Autowired
    Login loginComponent;


    @Autowired
    UserManager userManager;



    public void initialize(URL location, ResourceBundle resources) {
        this.cTF.clearTextFieldByButton(this
                .tfUserName, this.btnUserNameTfClear);
        this.cPF.clearPassFieldByButton(this.pfUserPassword, this.btnPassFieldClear);
        BooleanBinding boolenBinding = this.tfUserName.textProperty().isEmpty().or(this.pfUserPassword.textProperty().isEmpty());
        this.btnLogin.disableProperty().bind(boolenBinding);
        progressBar.setVisible(false);
        statusLabel.setVisible(false);

        try {
            System.out.println(userManager.findAll());
            loginAction();
            //startSession();
        } catch (Exception var3) {
            log.error(var3.getMessage());
        }
    }


    private void loginAction() throws IOException {


        btnLogin.setOnAction((event) -> {

            loginComponent.tfUserName = tfUserName;
            loginComponent.pfUserPassword = pfUserPassword;
            loginComponent.btnLogin = btnLogin;
            loginComponent.btnCancel = btnCancel;
            loginComponent.progressBar = progressBar;
            loginComponent.statusLabel = statusLabel;
            loginComponent.login(event);

        });

        pfUserPassword.setOnAction((event) -> {
            loginComponent.tfUserName = tfUserName;
            loginComponent.pfUserPassword = pfUserPassword;
            loginComponent.btnLogin = btnLogin;
            loginComponent.btnCancel = btnCancel;
            loginComponent.progressBar = progressBar;
            loginComponent.statusLabel = statusLabel;
            loginComponent.login(event);

        });


    }

    private void resetFields() {
        tfUserName.setText("");
        pfUserPassword.setText("");
    }

    @FXML
    public void cancelAction(ActionEvent event) {
        resetFields();
    }

    @FXML
    private void pfUserNameOnHitEnter(ActionEvent event) {
        try {
            loginAction();
        } catch (IOException var3) {
            log.error( var3.getMessage());
        }

    }

    @FXML
    private void pfUserPassOnHitEnter(ActionEvent event) {
        try {
            loginAction();

        } catch (IOException var3) {
            log.error(var3.getMessage());
        }

    }

    public void addLoginStage(Stage stage) {
    }

    @FXML
    public void btnMinimizeOnAction(ActionEvent event) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }


}

