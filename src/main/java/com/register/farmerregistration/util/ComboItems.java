package com.register.farmerregistration.util;

import com.register.farmerregistration.local.entities.LocalGovt;
import com.register.farmerregistration.local.entities.PersonalData;
import com.register.farmerregistration.local.entities.State;
import com.register.farmerregistration.local.managers.LocalGovtManager;
import com.register.farmerregistration.local.managers.StateManager;
import com.register.farmerregistration.local.repository.LocalGovtRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ComboItems {

    ComboBox<ItemContent> titleCombo, gender, state, localGovt = new ComboBox<>();

    @Autowired
    StateManager stateManager;

    @Autowired
    LocalGovtManager localGovtManager;

    @Autowired
    LocalGovtRepository localGovtRepository;

    long ___value;
    String ___valueString;


    public void setTitleCombo(ComboBox<ItemContent> __comboItem) {
        try {
            ObservableList<ItemContent> __ObsList = FXCollections.observableArrayList();
            for (PersonalData.titleEnum day : PersonalData.titleEnum.values()) {
                ItemContent theItem = new ItemContent(day.name(), day.name());
                __ObsList.add(theItem);
            }
            __comboItem.getItems().clear();
            __comboItem.setItems(__ObsList);
            __comboItem.getSelectionModel().selectFirst();
            __comboItem.setEditable(true);
            new AutoCompleteComboBoxListener<>(__comboItem);
            __comboItem.setConverter(new StringConverter<ItemContent>() {

                @Override
                public String toString(ItemContent object) {
                    if (object == null) return null;
                    return object.toString();
                }

                @Override
                public ItemContent fromString(String string) {
                    // replace this with approquiate implementation of parsing function
                    // or lookup function
                    ItemContent locCont = __comboItem.getSelectionModel().getSelectedItem();
                    return new ItemContent(locCont.getIdString(), string);
                }
            });

        } catch (Exception e) {
            log.error("Exception caught", e);
        }
    }

    public void setGenderCombo(ComboBox<ItemContent> __comboItem) {
        try {
            ObservableList<ItemContent> __ObsList = FXCollections.observableArrayList();
            for (PersonalData.genderEnum day : PersonalData.genderEnum.values()) {
                ItemContent theItem = new ItemContent(day.name(), day.name());
                __ObsList.add(theItem);
            }
            __comboItem.getItems().clear();
            __comboItem.setItems(__ObsList);
            __comboItem.getSelectionModel().selectFirst();
            __comboItem.setEditable(true);
            new AutoCompleteComboBoxListener<>(__comboItem);
            __comboItem.setConverter(new StringConverter<ItemContent>() {

                @Override
                public String toString(ItemContent object) {
                    if (object == null) return null;
                    return object.toString();
                }

                @Override
                public ItemContent fromString(String string) {
                    // replace this with approquiate implementation of parsing function
                    // or lookup function
                    ItemContent locCont = __comboItem.getSelectionModel().getSelectedItem();
                    return new ItemContent(locCont.getIdString(), string);
                }
            });

        } catch (Exception e) {
            log.error("Exception caught", e);
        }
    }

    public long changeComboBoxLong(ComboBox<ItemContent> __changeCombo) {
        try {
            ItemContent __changeCont;

            __changeCont = __changeCombo.getSelectionModel().getSelectedItem();
            __changeCombo.valueProperty().addListener(new ChangeListener<ItemContent>() {
                @Override
                public void changed(ObservableValue<? extends ItemContent> observableValue, ItemContent oldChoice, ItemContent newChoice) {
                    ___value = newChoice.getId();
                }
            });
            ___value = __changeCont.getId();
            return ___value;
        } catch (Exception ex) {
            log.error("Exception caught", ex);
            return 0;
        }
    }


    public  void loadTitle(ComboBox<ItemContent> title, Object titleID){
        this.titleCombo = title;
        try {
            this.titleCombo.getSelectionModel().select(0);
            if (((long) titleID) != 0) {
                LocalGovt theLocalG = localGovtManager.findById(((long) titleID));
                this.titleCombo.getSelectionModel().select(new ItemContent(((int) theLocalG.getId()), theLocalG.getName()));
            } else {
                this.titleCombo.getSelectionModel().select(0);
            }
        } catch (Exception ex) {
            log.error("Exception caught", ex);
        }
    }

    public void loadCombo(ComboBox<ItemContent> state, ComboBox<ItemContent> lga, Object stateID, Object lgaID) {
        this.localGovt = lga;
        this.state = state;
        
        try {
            this.state.getSelectionModel().select(0);
            if (((long) stateID != 0)) {
                State theLocalGs = stateManager.findById(((long) stateID));
                this.state.getSelectionModel().select(new ItemContent(((int) theLocalGs.getId()), theLocalGs.getName()));
            } else {
                this.state.getSelectionModel().select(0);
            }
        } catch (Exception ex) {
            log.error("Exception caught", ex);
        }


        try {
            this.localGovt.getSelectionModel().select(0);
            if (((long) lgaID) != 0) {
                LocalGovt theLocalG = localGovtManager.findById(((long) lgaID));
                this.localGovt.getSelectionModel().select(new ItemContent(((int) theLocalG.getId()), theLocalG.getName()));
            } else {
                this.localGovt.getSelectionModel().select(0);
            }
        } catch (Exception ex) {
            log.error("Exception caught", ex);
        }
        //changeComboBox();
    }


    public String changeComboBox(ComboBox<ItemContent> __changeCombo) {
        try {
            ItemContent __changeCont;

            __changeCont = __changeCombo.getSelectionModel().getSelectedItem();
            __changeCombo.valueProperty().addListener(new ChangeListener<ItemContent>() {
                @Override
                public void changed(ObservableValue<? extends ItemContent> observableValue, ItemContent oldChoice, ItemContent newChoice) {
                    ___valueString = newChoice.getIdString();
                }
            });
            ___valueString = __changeCont.getIdString();
            return ___valueString;
        } catch (Exception ex) {
            log.error("Exception caught", ex);
            return "";
        }
    }

    public void setStateLgaCombo(ComboBox<ItemContent> __comboItem, ComboBox<ItemContent> localGovtCombo) {

        this.localGovt = localGovtCombo;
        this.state = __comboItem;

        try {
            List<State> __List = stateManager.findAll();
            ObservableList<ItemContent> __ObsList = FXCollections.observableArrayList();

            for (State eachData : __List) {
                __ObsList.add(new ItemContent((eachData.getId()), eachData.getName()));
            }
            __comboItem.getItems().clear();
            __comboItem.setItems(__ObsList);
            __comboItem.getSelectionModel().selectFirst();
            __comboItem.setEditable(true);
            new AutoCompleteComboBoxListener<>(__comboItem);
            __comboItem.setConverter(new StringConverter<ItemContent>() {

                @Override
                public String toString(ItemContent object) {
                    if (object == null) return null;
                    return object.toString();
                }

                @Override
                public ItemContent fromString(String string) {
                    // replace this with approquiate implementation of parsing function
                    // or lookup function
                    ItemContent locCont = __comboItem.getSelectionModel().getSelectedItem();
                    return new ItemContent(locCont.getIdString(), string);
                }
            });

            this.state.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        List<LocalGovt> dbList;
                        long longValue = changeComboBoxLong(state);
                        dbList = localGovtRepository.findByStateId(String.valueOf(longValue));
                        ObservableList<ItemContent> __ObsList = FXCollections.observableArrayList();
                        for (LocalGovt eachData : dbList) {
                            __ObsList.add(new ItemContent((eachData.getId()), eachData.getName()));
                        }
                        localGovtCombo.getItems().clear();
                        localGovtCombo.setItems(__ObsList);
                        localGovtCombo.getSelectionModel().selectFirst();
                        localGovtCombo.setEditable(true);
                        new AutoCompleteComboBoxListener<>(localGovtCombo);
                        localGovtCombo.setConverter(new StringConverter<ItemContent>() {

                            @Override
                            public String toString(ItemContent object) {
                                if (object == null) return null;
                                return object.toString();
                            }

                            @Override
                            public ItemContent fromString(String string) {
                                // replace this with approquiate implementation of parsing function
                                // or lookup function
                                ItemContent locCont = localGovtCombo.getSelectionModel().getSelectedItem();
                                return new ItemContent(locCont.getIdString(), string);
                            }
                        });
                        
                    } catch (Exception e) {
                        log.error("Exception caught", e);
                    }
                }
            });

        } catch (Exception e) {
            log.error("Exception caught", e);
        }
    }
}
