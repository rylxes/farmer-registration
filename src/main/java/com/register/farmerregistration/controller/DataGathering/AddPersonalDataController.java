package com.register.farmerregistration.controller.DataGathering;


import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.ReaderCollection;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;
import fingerprint.scanner.*;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;


/**
 * Created by sherriff on 13/10/2016.
 */
@Slf4j
@Controller
public class AddPersonalDataController extends JPanel
        implements ActionListener, Initializable {

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
    @FXML
    private Button btnCapture;
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
                personalDataH.setLga((comboItems.changeComboBoxValue(lga)));
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
            personalDataH.setLga((comboItems.changeComboBoxValue(lga)));
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

            //comboItems.loadCombo(stateId, lga, theData.getState_id(), theData.getLga());
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

    public void start(){
        System.out.println("Started");
        createAndShowGUI();
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new ClassPathXmlApplicationContext( "UareUSampleJava.xml" ); // pay attention to context so that it is not left open
//                //createAndShowGUI();
//            }
//        });
    }

    private static final long serialVersionUID=1;

    private static final String ACT_SELECTION = "selection";
    private static final String ACT_CAPTURE = "capture";
    private static final String ACT_STREAMING = "streaming";
    private static final String ACT_VERIFICATION = "verification";
    private static final String ACT_IDENTIFICATION = "identification";
    private static final String ACT_ENROLLMENT = "enrollment";
    private static final String ACT_PHOTO = "Take Photo";
    private static final String ACT_EXIT = "exit";

    private JDialog   m_dlgParent;
    private JTextArea m_textReader;

    private ReaderCollection m_collection;
    private Reader m_reader;

    private AddPersonalDataController(){
        final int vgap = 5;
        final int width = 380;

        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(layout);

        add(Box.createVerticalStrut(vgap));
        JLabel lblReader = new JLabel("Selected reader:");
        add(lblReader);
        add(Box.createVerticalStrut(vgap));
        Dimension dm = lblReader.getPreferredSize();
        dm.width = width;
        lblReader.setPreferredSize(dm);


        m_textReader = new JTextArea(3, 1);
        m_textReader.setEditable(false);
        JScrollPane paneReader = new JScrollPane(m_textReader);
        add(paneReader);
        add(Box.createVerticalStrut(vgap));

        JButton btnSelect = new JButton("Select new reader");
        btnSelect.setActionCommand(ACT_SELECTION);
        btnSelect.addActionListener(this);
        add(btnSelect);
        add(Box.createVerticalStrut(vgap));

        JButton btnCapture = new JButton("Run capture");
        btnCapture.setActionCommand(ACT_CAPTURE);
        btnCapture.addActionListener(this);
        add(btnCapture);
        add(Box.createVerticalStrut(vgap));

        JButton btnStreaming = new JButton("Run streaming");
        btnStreaming.setActionCommand(ACT_STREAMING);
        btnStreaming.addActionListener(this);
        add(btnStreaming);
        add(Box.createVerticalStrut(vgap));

        JButton btnVerification = new JButton("Run verification");
        btnVerification.setActionCommand(ACT_VERIFICATION);
        btnVerification.addActionListener(this);
        add(btnVerification);
        add(Box.createVerticalStrut(vgap));

        JButton btnIdentification = new JButton("Run identification");
        btnIdentification.setActionCommand(ACT_IDENTIFICATION);
        btnIdentification.addActionListener(this);
        add(btnIdentification);
        add(Box.createVerticalStrut(vgap));

        JButton btnEnrollment = new JButton("Run enrollment");
        btnEnrollment.setActionCommand(ACT_ENROLLMENT);
        btnEnrollment.addActionListener(this);
        add(btnEnrollment);
        add(Box.createVerticalStrut(vgap));

        JButton btnPhoto = new JButton("Take Photo");
        btnPhoto.setActionCommand(ACT_PHOTO);
        btnPhoto.addActionListener(this);
        add(btnPhoto);
        add(Box.createVerticalStrut(vgap));


        add(Box.createVerticalStrut(vgap));
        JButton btnExit = new JButton("Close");
        btnExit.setActionCommand(ACT_EXIT);
        btnExit.addActionListener(this);
        add(btnExit);
        add(Box.createVerticalStrut(vgap));

        setOpaque(true);
    }

    public void actionPerformed(java.awt.event.ActionEvent e){
        if(e.getActionCommand().equals(ACT_SELECTION)){
            m_reader = Selection.Select(m_collection);
            if(null != m_reader){
                m_textReader.setText(m_reader.GetDescription().name);
            }
            else{
                m_textReader.setText("");
            }
        }
        else if(e.getActionCommand().equals(ACT_CAPTURE)){
            if(null == m_reader){
                MessageBox.Warning("Reader is not selected");
            }
            else{
                Capture.Run(m_reader, false);
            }
        }
        else if(e.getActionCommand().equals(ACT_STREAMING)){
            if(null == m_reader){
                MessageBox.Warning("Reader is not selected");
            }
            else{
                Capture.Run(m_reader, true);
            }
        }
        else if(e.getActionCommand().equals(ACT_VERIFICATION)){
            if(null == m_reader){
                MessageBox.Warning("Reader is not selected");
            }
            else{
                Verification.Run(m_reader);
            }
        }
        else if(e.getActionCommand().equals(ACT_IDENTIFICATION)){
            if(null == m_reader){
                MessageBox.Warning("Reader is not selected");
            }
            else{
                Identification.Run(m_reader);
            }
        }
        else if(e.getActionCommand().equals(ACT_ENROLLMENT)){
            if(null == m_reader){
                MessageBox.Warning("Reader is not selected");
            }
            else{
                Enrollment.Run(m_reader);
            }
        }
        else if(e.getActionCommand().equals(ACT_PHOTO)){
            TakePicture.Run();
        }
        else if(e.getActionCommand().equals(ACT_EXIT)){
            m_dlgParent.setVisible(false);
        }
    }

    private void doModal(JDialog dlgParent){
        m_dlgParent = dlgParent;
        m_dlgParent.setContentPane(this);
        m_dlgParent.pack();
        m_dlgParent.setLocationRelativeTo(null);
        m_dlgParent.setAutoRequestFocus(false);
        m_dlgParent.setVisible(true);
        m_dlgParent.dispose();
    }

    private static void createAndShowGUI() {
        AddPersonalDataController paneContent = new AddPersonalDataController();

        //initialize capture library by acquiring reader collection
        try{
            paneContent.m_collection = UareUGlobal.GetReaderCollection();
        }
        catch(UareUException e) {
            MessageBox.DpError("UareUGlobal.getReaderCollection()", e);
            return;
        }

        //run dialog
        JDialog dlg = new JDialog((JDialog)null, "Maisatech BioCapture", true);
        paneContent.doModal(dlg);


        //release capture library by destroying reader collection
        try{
            UareUGlobal.DestroyReaderCollection();
        }
        catch(UareUException e) {
            MessageBox.DpError("UareUGlobal.destroyReaderCollection()", e);
        }
    }
    @FXML
    private void btnCaptureOnAction(ActionEvent event){
        System.out.println("Finger scan starting..");
        start();
    }

}