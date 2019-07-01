package com.register.farmerregistration;


import com.register.farmerregistration.controller.ApplicationController;
import com.register.farmerregistration.controller.DataGathering.FarmDataController;
import com.register.farmerregistration.controller.DataGathering.PersonalDataController;
import com.register.farmerregistration.util.I18N;
import com.register.farmerregistration.util.WindowsUtils;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
@EnableScheduling
public class FarmerRegistrationApplication extends Application {

    private static final Logger log = LoggerFactory.getLogger(FarmerRegistrationApplication.class);
    private Scene scene;


    public static void main(final String[] args) {
        //SpringApplicationBuilder builder = new SpringApplicationBuilder(FarmerRegistrationApplication.class);
        //builder.headless(false).run(args);
        launch(FarmerRegistrationApplication.class, args);
    }


    public static ConfigurableApplicationContext springContext;
    public static I18N i18n;
    public static HostServices hostServices;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(FarmerRegistrationApplication.class);
        //SpringApplicationBuilder builder = new SpringApplicationBuilder(FarmerRegistrationApplication.class);
        initI18N();
    }

    private void initI18N() {
        i18n = new I18N(I18N.ENGLISH);


    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }

    @Override
    public void start(Stage stage) throws Exception {

        startApplication(stage);
    }

    private void startApplication(final Stage primaryStage) {
        try {

//            FXMLLoader fXMLLoader = new FXMLLoader();
//            fXMLLoader.setControllerFactory(springContext::getBean);
//            fXMLLoader.load(this.getClass().getResource(ApplicationController.PATH_FXML).openStream());

            //WindowsUtils.openNewWindow(primaryStage, AboutController.PATH_FXML, "About", AboutController.PATH_ICON, null);
            //WindowsUtils.openNewWindow(primaryStage, FarmDataController.PATH_FXML, "Farm Data", FarmDataController.PATH_ICON, null);
            WindowsUtils.openNewWindow(primaryStage, ApplicationController.PATH_FXML, "Maisatech Nig Ltd", ApplicationController.PATH_ICON, null);
//            WindowsUtils.openNewWindow(primaryStage, PersonalDataController.PATH_FXML, "Personal Data", PersonalDataController.PATH_ICON, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}

