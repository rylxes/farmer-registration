package com.register.farmerregistration.util;

import com.register.farmerregistration.local.entities.*;
import com.register.farmerregistration.local.managers.AgroInputCategoryManager;
import com.register.farmerregistration.local.managers.LocalGovtManager;
import com.register.farmerregistration.local.managers.StateManager;
import com.register.farmerregistration.local.managers.UserManager;
import com.register.farmerregistration.local.repository.LocalGovtRepository;
import com.register.farmerregistration.local.repository.UserRepository;
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

    ComboBox<ItemContent> titleCombo, gender, state, localGovt, userCombo = new ComboBox<>();
    ComboBox<ItemContent> categoryCombo, varietyCombo, unitCombo = new ComboBox<>();

    @Autowired
    StateManager stateManager;

    @Autowired
    AgroInputCategoryManager categoryManager;

    @Autowired
    LocalGovtManager localGovtManager;

    @Autowired
    UserManager userManager;


    @Autowired
    LocalGovtRepository localGovtRepository;

    @Autowired
    UserRepository userRepository;


    long ___value;
    int ___valueInt;
    String ___valueString;


    public void setUserCombo(ComboBox<ItemContent> __comboItem) {
        try {
            List<User> __List = userRepository.findAll();
            ObservableList<ItemContent> __ObsList = FXCollections.observableArrayList();

            for (User eachData : __List) {
                __ObsList.add(new ItemContent(eachData.getId(), eachData.getName()));
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
                    return new ItemContent(locCont.getId(), string);
                }
            });

        } catch (Exception e) {
            log.error("Exception caught", e);
        }
    }


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

    public void setCategoryCombo(ComboBox<ItemContent> __comboItem) {
        try {
            ObservableList<ItemContent> __ObsList = FXCollections.observableArrayList();
            for (AgroInputDisbursed.categoryEnum day : AgroInputDisbursed.categoryEnum.values()) {
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


    public void setVarietyCombo(ComboBox<ItemContent> __comboItem) {
        try {
            ObservableList<ItemContent> __ObsList = FXCollections.observableArrayList();
            for (AgroInputDisbursed.varietyEnum day : AgroInputDisbursed.varietyEnum.values()) {
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

    public void setUnitCombo(ComboBox<ItemContent> __comboItem) {
        try {
            ObservableList<ItemContent> __ObsList = FXCollections.observableArrayList();
            for (AgroInputDisbursed.unitEnum day : AgroInputDisbursed.unitEnum.values()) {
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


    public void loadUser(ComboBox<ItemContent> userCombo, Object titleID) {
        this.userCombo = userCombo;
        try {
            this.userCombo.getSelectionModel().select(0);
            if (((long) titleID) != 0) {
                User theLocalG = userManager.findById(((int) titleID));
                this.userCombo.getSelectionModel().select(new ItemContent(((int) theLocalG.getId()), theLocalG.getName()));
            } else {
                this.userCombo.getSelectionModel().select(0);
            }
        } catch (Exception ex) {
            log.error("Exception caught", ex);
        }
    }

    public void loadTitle(ComboBox<ItemContent> title, Integer titleID) {
        this.titleCombo = title;
        try {
            this.titleCombo.getSelectionModel().select(0);
            if (((long) titleID) != 0) {
                LocalGovt theLocalG = localGovtManager.findById(titleID);
                this.titleCombo.getSelectionModel().select(new ItemContent(((int) theLocalG.getId()), theLocalG.getName()));
            } else {
                this.titleCombo.getSelectionModel().select(0);
            }
        } catch (Exception ex) {
            log.error("Exception caught", ex);
        }
    }

    public void loadCombo(ComboBox<ItemContent> state, ComboBox<ItemContent> lga, Integer stateID, String lgaIDs) {
        Integer lgaID = Integer.valueOf(lgaIDs);
        this.localGovt = lga;
        this.state = state;

        try {
            this.state.getSelectionModel().select(0);
            if ((stateID != 0)) {
                State theLocalGs = stateManager.findById(stateID);
                this.state.getSelectionModel().select(new ItemContent(((int) theLocalGs.getId()), theLocalGs.getName()));
            } else {
                this.state.getSelectionModel().select(0);
            }
        } catch (Exception ex) {
            log.error("Exception caught", ex);
        }


        try {
            this.localGovt.getSelectionModel().select(0);
            if ((lgaID) != 0) {
                LocalGovt theLocalG = localGovtManager.findById(lgaID);
                this.localGovt.getSelectionModel().select(new ItemContent(((int) theLocalG.getId()), theLocalG.getName()));
            } else {
                this.localGovt.getSelectionModel().select(0);
            }
        } catch (Exception ex) {
            log.error("Exception caught", ex);
        }
        //changeComboBox();
    }


    public void loadDisbusementCombo(ComboBox<ItemContent> category, Integer categoryID) {
        this.categoryCombo = category;

        try {
            this.categoryCombo.getSelectionModel().select(0);
            if ((categoryID != 0)) {
                AgroInputCategory theCategories = categoryManager.findById(Long.valueOf(categoryID));
                this.categoryCombo.getSelectionModel().select(new ItemContent(((int) theCategories.getId()), theCategories.getCategory_name()));
            } else {
                this.categoryCombo.getSelectionModel().select(0);
            }
        } catch (Exception ex) {
            log.error("Exception caught", ex);
        }


//        try {
//            this.localGovt.getSelectionModel().select(0);
//            if ((lgaID) != 0) {
//                LocalGovt theLocalG = localGovtManager.findById(lgaID);
//                this.localGovt.getSelectionModel().select(new ItemContent(((int) theLocalG.getId()), theLocalG.getName()));
//            } else {
//                this.localGovt.getSelectionModel().select(0);
//            }
//        } catch (Exception ex) {
//            log.error("Exception caught", ex);
//        }
        //changeComboBox();
    }



    public String changeComboBoxValue(ComboBox<ItemContent> __changeCombo) {
        try {
            ItemContent __changeCont;

            __changeCont = __changeCombo.getSelectionModel().getSelectedItem();
            __changeCombo.valueProperty().addListener(new ChangeListener<ItemContent>() {
                @Override
                public void changed(ObservableValue<? extends ItemContent> observableValue, ItemContent oldChoice, ItemContent newChoice) {
                    ___valueString = newChoice.getName();
                }
            });
            ___valueString = __changeCont.getName();
            return ___valueString;
        } catch (Exception ex) {
            log.error("Exception caught", ex);
            return "";
        }
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

    public Integer changeComboBoxInt(ComboBox<ItemContent> __changeCombo) {
        try {
            ItemContent __changeCont;

            __changeCont = __changeCombo.getSelectionModel().getSelectedItem();
            __changeCombo.valueProperty().addListener(new ChangeListener<ItemContent>() {
                @Override
                public void changed(ObservableValue<? extends ItemContent> observableValue, ItemContent oldChoice, ItemContent newChoice) {
                    ___valueInt = newChoice.getIdInt();
                }
            });
            ___valueInt = __changeCont.getIdInt();
            return ___valueInt;
        } catch (Exception ex) {
            log.error("Exception caught", ex);
            return 0;
        }
    }


    private void handleLga() {
        try {
            List<LocalGovt> dbList;
            int longValue = changeComboBoxInt(state);
            dbList = localGovtRepository.findByStateId(longValue);
            ObservableList<ItemContent> __ObsList = FXCollections.observableArrayList();
            for (LocalGovt eachData : dbList) {
                __ObsList.add(new ItemContent((eachData.getId()), eachData.getName()));
            }
            localGovt.getItems().clear();
            localGovt.setItems(__ObsList);
            localGovt.getSelectionModel().selectFirst();
            localGovt.setEditable(true);
            new AutoCompleteComboBoxListener<>(localGovt);
            localGovt.setConverter(new StringConverter<ItemContent>() {

                @Override
                public String toString(ItemContent object) {
                    if (object == null) return null;
                    return object.toString();
                }

                @Override
                public ItemContent fromString(String string) {
                    // replace this with approquiate implementation of parsing function
                    // or lookup function
                    ItemContent locCont = localGovt.getSelectionModel().getSelectedItem();
                    return new ItemContent(locCont.getIdInt(), string);
                }
            });

        } catch (Exception e) {
            log.error("Exception caught", e);
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
            //
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
                    return new ItemContent(locCont.getIdInt(), string);
                }
            });

            handleLga();
            this.state.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    handleLga();
                }
            });

        } catch (Exception e) {
            log.error("Exception caught", e);
        }
    }
}
