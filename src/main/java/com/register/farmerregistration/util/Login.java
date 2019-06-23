package com.register.farmerregistration.util;


import com.register.farmerregistration.controller.ApplicationController;
import com.register.farmerregistration.controller.LoginController;
import com.register.farmerregistration.local.entities.User;
import com.register.farmerregistration.local.managers.UserManager;
import com.register.farmerregistration.local.repository.UserRepository;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.register.farmerregistration.FarmerRegistrationApplication.springContext;


/**
 * Created by sherriff on 15/02/2017.
 */
@Slf4j
@Component
public class Login {


    @Autowired
    @Qualifier("localUserRepository")
    UserRepository usersGateway;




    @Autowired
    UserManager userManager;


    @Autowired
    LoginController loginController;



    private String UserName = "";
    private String UserId = "";
    private String UserBranchId = "";
    private String dataBase = "";
    public TextField tfUserName;
    public PasswordField pfUserPassword;
    public Label statusLabel;
    public Button btnLogin, btnCancel;
    public ProgressIndicator progressBar = new ProgressIndicator();


    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        UserSession.setUserDB(dataBase);
        this.dataBase = dataBase;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserBranchId() {
        return UserBranchId;
    }

    public void setUserBranchId(String userBranchId) {
        UserSession.setUserBranchId(userBranchId);
        UserBranchId = userBranchId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserSession.setUserID(userId);
        UserId = userId;
    }


    public void login(ActionEvent actionEvent) {
        btnLogin.setVisible(false);
        btnCancel.setVisible(false);
        try {
            statusLabel.setText("Hold on");
            Long userCount = usersGateway.getTotalUsers();

            loginLocal(actionEvent);
        } catch (Exception ex) {
            log.error("Exception caught", ex);
        }
    }


    private void reload() {
        tfUserName.setVisible(true);
        pfUserPassword.setVisible(true);
        btnLogin.setVisible(true);
        btnCancel.setVisible(true);
        progressBar.setVisible(false);
        statusLabel.setVisible(false);

    }

    private void loginLocal(ActionEvent actionEvent) {
        if (userManager.login(tfUserName.getText(), pfUserPassword.getText())) {
            final Task<FXMLLoader> task = new Task<FXMLLoader>() {
                @Override
                protected FXMLLoader call() throws IOException, InterruptedException {
                    updateMessage("Logging you in . . .");
                    progressBar.setVisible(true);
                    statusLabel.setVisible(true);
                    btnLogin.setVisible(false);
                    btnCancel.setVisible(false);
                    getClientDBLocal();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Application.fxml"));
                    loader.setControllerFactory(springContext::getBean);
                    updateMessage("Finished.");
                    return loader;
                }
            };
            //task.getValue();
            statusLabel.textProperty().bind(task.messageProperty());
            progressBar.progressProperty().bind(task.progressProperty());
            task.setOnSucceeded(e -> {
                reload();
                onsucceedTask(task, actionEvent);
            });

            task.setOnFailed(e ->
            {
                log.error("Exception caught", task.getException());
            });
            new Thread(task).start();
        } else {
            reload();

            Alert alert = new Alert(Alert.AlertType.ERROR, "The Username or Password is not correct. local ");
            alert.initModality(Modality.APPLICATION_MODAL);
            // This will not work in your code
            alert.initOwner(pfUserPassword.getScene().getWindow());
            alert.show();
        }
    }


    private void onsucceedTask(Task<FXMLLoader> task, ActionEvent actionEvent) {
        try {
            Parent root = task.getValue().load();
            Parent parent = (Parent) task.getValue().getRoot();
            Scene adminPanelScene = new Scene(parent);
            Stage adminPanelStage = new Stage();
            adminPanelStage.setMaximized(true);
            ApplicationController apControl = (ApplicationController) task.getValue().getController();
            apControl.btnHomeOnClick(actionEvent);
            adminPanelStage.setScene(adminPanelScene);
            adminPanelStage.getIcons().add(new Image("/image/icon.png"));
            //adminPanelStage.setTitle(this.rs.getString(3));
            adminPanelStage.show();
            Stage stage = (Stage) tfUserName.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            log.error("Exception caught", ex);
        }
    }

    private void getClientDBLocal() {
        try {
            User theUser = usersGateway.findByEmail(tfUserName.getText());
            setUserName(theUser.getEmail());
            setUserId(String.valueOf(theUser.getId()));
            UserSession.setCurrentAuth(theUser);
            setDataBase(dataBase);
        } catch (Exception ex) {
            System.out.println(ex);
            log.error("Exception caught", ex);
        }
    }
    private void schedulingTest() {
        try {

            Runnable runnable = new Runnable() {
                public void run() {

                    //thread();
                }
            };

            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(runnable, 40, 260, TimeUnit.SECONDS);

        } catch (Exception ex) {
            log.error("Exception caught", ex);
        }
    }

}
