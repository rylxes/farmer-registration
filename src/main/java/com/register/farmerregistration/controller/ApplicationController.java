package com.register.farmerregistration.controller;

/**
 * Created by sherriff on 27/09/2016.
 */


import com.register.farmerregistration.util.GlobalMethods;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static com.register.farmerregistration.FarmerRegistrationApplication.springContext;


@Slf4j
@Controller
public class ApplicationController implements Initializable {
    @FXML
    private StackPane acContent;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private ToggleButton sideMenuToogleBtn;
    @FXML
    private ImageView imgMenuBtn;
    @FXML
    private BorderPane appContent;
    @FXML
    private Button btnLogOut;
    @FXML
    private MenuItem miPopOver;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private AnchorPane acHead;
    @FXML
    private AnchorPane acMain;
    @FXML
    private MenuButton mbtnUsrInfoBox;
    @FXML
    private Button btnHome, btnPos;
    @FXML
    private ImageView imgHomeBtn, imgPosBtn;
    @FXML
    private Button btnInventory;
    @FXML
    private ImageView imgInventoryBtn;
    @FXML
    private Button btnStore;
    @FXML
    private ImageView imgStoreBtn;
    @FXML
    private Button btnPurchase;
    @FXML
    private ImageView imgPurchaseBtn;
    @FXML
    private Button btnReport;
    @FXML
    private ImageView imgReportBtn;
    @FXML
    private Button btnSell;
    @FXML
    private ImageView imgSellBtn;

    @FXML
    private Button btnSettings;
    @FXML
    private ImageView imgSettingsBtn;
    @FXML
    private Button btnAbout;
    @FXML
    private ImageView imgAboutBtn;
    @FXML
    private Label lblUsrName;
    @FXML
    private Label lblUsrNamePopOver;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblRoleAs;
    @FXML
    private Hyperlink hlEditUpdateAccount;
    @FXML
    private Circle imgUsrTop;
    @FXML
    private Circle circleImgUsr;
    @FXML
    private Label lblUserId;
    String usrName;
    String id;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;


    Image menuImage = new Image("/icon/menu.png");
    Image menuImageRed = new Image("/icon/menuRed.png");
    Image image;
    String defultStyle = "-fx-border-width: 0px 0px 0px 5px;-fx-border-color:none";
    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;-fx-border-color:#FF4E3C";


    Image home = new Image("/newicon/home.png");
    Image homeRed = new Image("/newicon/home_selected.png");


    Image pos = new Image("/newicon/pos.png");
    Image posRed = new Image("/newicon/pos_selected.png");


    Image shop = new Image("/newicon/shop.png");
    Image shopRed = new Image("/newicon/shop_selected.png");


    Image report = new Image("/newicon/report.png");
    Image reportRed = new Image("/newicon/report_selected.png");


    Image purchase = new Image("/newicon/purchase.png");
    Image purchaseRed = new Image("/newicon/purchase_selected.png");

    Image inventory = new Image("/newicon/inventory.png");
    Image inventoryRed = new Image("/newicon/inventory_selected.png");

    Image sell = new Image("/newicon/sell.png");
    Image sellRed = new Image("/newicon/sell_selected.png");


    Image setting = new Image("/newicon/settings.png");
    Image settingRed = new Image("/newicon/settings_selected.png");


    Image about = new Image("/newicon/about.png");
    Image aboutRed = new Image("/newicon/about_selected.png");


    public ApplicationController() {

    }


    public void initialize(URL url, ResourceBundle rb) {
//        this.acContent.getScene().getWindow().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        this.imgMenuBtn.setImage(this.menuImage);
        Image usrImg = new Image("/newicon/user.png");
        this.imgUsrTop.setFill(new ImagePattern(usrImg));
        this.circleImgUsr.setFill(new ImagePattern(usrImg));

    }

    @FXML
    private void sideMenuToogleBtnOnCLick(ActionEvent event) throws IOException {
        TranslateTransition sideMenu;
        if (this.sideMenuToogleBtn.isSelected()) {
            this.imgMenuBtn.setImage(this.menuImageRed);
            sideMenu = new TranslateTransition(Duration.millis(200.0D), this.acDashBord);
            sideMenu.setByX(-130.0D);
            sideMenu.play();
            this.acDashBord.getChildren().clear();
        } else {
            this.imgMenuBtn.setImage(this.menuImage);
            sideMenu = new TranslateTransition(Duration.millis(200.0D), this.acDashBord);
            sideMenu.setByX(130.0D);
            sideMenu.play();
            this.acDashBord.getChildren().add(this.leftSideBarScroolPan);
        }

    }

    @FXML
    private void btnLogOut(ActionEvent event) throws IOException {
        this.acContent.getChildren().clear();
        //this.acContent.getChildren().add(FXMLLoader.load(this.getClass().getResource("/fxml/Login.fxml")));
        this.acDashBord.getChildren().clear();
        this.acHead.getChildren().clear();
        this.acHead.setMaxHeight(0.0D);
        this.acMain.setVisible(false);
        Stage stage = (Stage) this.acMain.getScene().getWindow();
        stage.close();
        GlobalMethods login = new GlobalMethods();
        login.btnLogin();
        //Application.Main app = new Application.Main();
    }

    @FXML
    private void acMain(KeyEvent event) {
        if (event.getCode() == KeyCode.F11) {
            Stage stage = (Stage) this.acMain.getScene().getWindow();
            stage.setFullScreen(true);
        }

    }


    @FXML
    public void btnHomeOnClick(ActionEvent event) {
        this.homeActive();
        FXMLLoader fxmlLoader = new FXMLLoader();

        try {
            fxmlLoader.setControllerFactory(springContext::getBean);
            fxmlLoader.load(this.getClass().getResource("/fxml/home/Home.fxml").openStream());
        } catch (IOException var4) {
            log.error(var4.getMessage());
        }

        AnchorPane root = (AnchorPane) fxmlLoader.getRoot();
        this.acContent.getChildren().clear();
        this.acContent.getChildren().add(root);
        //System.out.println(this.lblUsrName.getText());
        // System.out.println(this.lblUserId.getText());
    }


    @FXML
    public void btnFarmDataOnClick(ActionEvent event) throws IOException {
        this.storeActive();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setControllerFactory(springContext::getBean);
        fXMLLoader.load(this.getClass().getResource("/fxml/data-gathering/PersonalData.fxml").openStream());
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.acContent.getChildren().clear();
        this.acContent.getChildren().add(acPane);
    }


    @FXML
    public void btnDisbursementOnClick(ActionEvent event) throws IOException {
        this.sellActive();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setControllerFactory(springContext::getBean);
        fXMLLoader.load(this.getClass().getResource("/fxml/data-gathering/PersonalData.fxml").openStream());
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.acContent.getChildren().clear();
        this.acContent.getChildren().add(acPane);
    }

    @FXML
    public void btnPersonalDataOnClick(ActionEvent event) throws IOException {
        this.posActive();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setControllerFactory(springContext::getBean);
        fXMLLoader.load(this.getClass().getResource("/fxml/data-gathering/PersonalData.fxml").openStream());
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.acContent.getChildren().clear();
        this.acContent.getChildren().add(acPane);
    }


    @FXML
    private void hlUpdateAccount(ActionEvent event) {
    }

    @FXML
    private void mbtnOnClick(ActionEvent event) {
    }

    @FXML
    private void acMainOnMouseMove(MouseEvent event) {
    }


    private void setAllButtons() {
        this.imgHomeBtn.setImage(this.homeRed);
        this.imgStoreBtn.setImage(this.shop);
        this.imgSellBtn.setImage(this.sell);
        this.imgPurchaseBtn.setImage(this.purchase);
        this.imgSettingsBtn.setImage(this.setting);
        this.imgAboutBtn.setImage(this.about);
        this.imgReportBtn.setImage(this.report);
        this.imgPosBtn.setImage(this.pos);
    }

    private void homeActive() {
        setAllButtons();
        this.btnHome.setStyle(this.activeStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.imgPurchaseBtn.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
        this.btnReport.setStyle(this.defultStyle);
        this.btnPos.setStyle(this.defultStyle);
    }

    private void storeActive() {
        setAllButtons();
        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.activeStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.imgPurchaseBtn.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
        this.btnReport.setStyle(this.defultStyle);
        this.btnPos.setStyle(this.defultStyle);
    }

    private void sellActive() {
        setAllButtons();
        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.activeStyle);
        this.imgPurchaseBtn.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
        this.btnReport.setStyle(this.defultStyle);
        this.btnPos.setStyle(this.defultStyle);
    }

    private void inventoryActive() {
        this.imgHomeBtn.setImage(this.home);
        this.imgStoreBtn.setImage(this.shop);
        this.imgSellBtn.setImage(this.sell);
        this.imgInventoryBtn.setImage(this.inventoryRed);
        this.imgPurchaseBtn.setImage(this.purchase);
        this.imgSettingsBtn.setImage(this.setting);
        this.imgAboutBtn.setImage(this.about);
        this.imgReportBtn.setImage(this.report);


        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.btnInventory.setStyle(this.activeStyle);
        this.imgPurchaseBtn.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
        this.btnReport.setStyle(this.defultStyle);
    }

    private void purchasesActive() {
        setAllButtons();
        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.imgPurchaseBtn.setStyle(this.activeStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
        this.btnReport.setStyle(this.defultStyle);
        this.btnPos.setStyle(this.defultStyle);
    }

    private void settingsActive() {
        setAllButtons();
        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.imgPurchaseBtn.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.activeStyle);
        this.btnAbout.setStyle(this.defultStyle);
        this.btnReport.setStyle(this.defultStyle);
        this.btnPos.setStyle(this.defultStyle);
    }

    private void aboutActive() {
        setAllButtons();
        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.imgPurchaseBtn.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.activeStyle);
        this.btnReport.setStyle(this.defultStyle);
        this.btnPos.setStyle(this.defultStyle);
    }


    private void reportActive() {
        setAllButtons();
        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.imgPurchaseBtn.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
        this.btnReport.setStyle(this.activeStyle);
        this.btnPos.setStyle(this.defultStyle);
    }


    private void posActive() {
        setAllButtons();
        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.imgPurchaseBtn.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
        this.btnReport.setStyle(this.defultStyle);
        this.btnPos.setStyle(this.activeStyle);
    }

    private void discountActive() {
        this.imgHomeBtn.setImage(this.home);
        this.imgStoreBtn.setImage(this.shop);
        this.imgSellBtn.setImage(this.sell);
        this.imgPurchaseBtn.setImage(this.purchase);
        this.imgSettingsBtn.setImage(this.setting);
        this.imgAboutBtn.setImage(this.about);


        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.imgPurchaseBtn.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
        this.btnReport.setStyle(this.defultStyle);
    }

    private void multiStoreActive() {
        this.imgHomeBtn.setImage(this.home);
        this.imgStoreBtn.setImage(this.shop);
        this.imgSellBtn.setImage(this.sell);
        this.imgPurchaseBtn.setImage(this.purchase);
        this.imgSettingsBtn.setImage(this.setting);
        this.imgAboutBtn.setImage(this.about);


        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.imgPurchaseBtn.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
        this.btnReport.setStyle(this.defultStyle);
    }

}