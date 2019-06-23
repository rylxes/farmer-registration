package com.register.farmerregistration.util;

import com.register.farmerregistration.FarmerRegistrationApplication;
import com.register.farmerregistration.controller.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by sherriff on 21/09/2016.
 */
public class GlobalMethods {


    public void search(String oldVal, String newVal, ListView<ItemContent> theListViewID, ObservableList<ItemContent> theObservableListItem) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        if (oldVal != null && (newVal.length() < oldVal.length())) {
            // Restore the lists original set of entries
            // and start from the beginning
            theListViewID.setItems(theObservableListItem);
        }

        // Break out all of the parts of the search text
        // by splitting on white space
        String[] parts = newVal.toUpperCase().split(" ");

        // Filter out the entries that don't contain the entered text
        ObservableList<ItemContent> subentries = FXCollections.observableArrayList();
        for (ItemContent entry : theListViewID.getItems()) {

            boolean match = true;
            String entryText = entry.getName();
            long entryint = entry.getId();
            for (String part : parts) {
                // The entry needs to contain all portions of the
                // search string *but* in any order
                if (!entryText.toUpperCase().contains(part)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                subentries.add(new ItemContent(entryint, entryText));
            }
        }
        theListViewID.setItems(subentries);
    }


    void onModelSave() {
    }

    private void guuidGenerator(){

    }


    public void btnLogin() throws IOException {
        Group groupLayout;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FarmerRegistrationApplication.class.getResource("/fxml/Login.fxml"));
        groupLayout = loader.load();
        LoginController __controller = (LoginController) loader.getController();
        Scene scene = new Scene(groupLayout);
        scene.setFill(Color.GREEN);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(true);
        __controller.addLoginStage(stage);
        stage.show();
    }
}
