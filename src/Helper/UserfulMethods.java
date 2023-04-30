/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import DAO.DAOLists;
import static Helper.Alerts.appointmentTimeAlerts;
import static Helper.TimeMethods.changeToEst;
import Models.Contacts;
import Models.Customers;
import DAO.*;
import Models.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

     /**
     * @class UsefulMethtods
     * Contains methods that can be  re-used for multiple pages
     * @author sean thompson <stho292@wgu.edu>
     */ 
public abstract class UserfulMethods {
    
    
    
     /**
     * validateNonEmpty
     * Verifies TextField is not empty
     * @param TextField
     */ 
        public static void validateNonEmpty(TextField... tf) {        
        for (TextField textField: tf){
                if (textField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.NONE);
            alert.setTitle("Validation of Fields");
            alert.setContentText("Please Fill All Fields");
            alert.show();
            return; 
        }
    }
}
        
    /**
    * validateHasSelection
    * Verifies ComboBox has a selection
    * @param ComboBox
    */ 
    public static void validateHasSelection(ComboBox... cb) {        
        for (ComboBox combobox: cb){
            if (combobox.selectionModelProperty() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.NONE);
            alert.setTitle("Validation of Fields");
            alert.setContentText("Please Fill All Fields");
            alert.show();
            return; 
            }
        }
    }
    
     /**
     * validateHasDate
     * Verifies Date Picker has a selection
     * @param DatePicker
     */ 
        public static void validateHasDate(DatePicker... dp) {        
        for (DatePicker datepicker: dp){
            if (datepicker.getValue().equals(null)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.NONE);
            alert.setTitle("Validation of Fields");
            alert.setContentText("Please Fill All Fields");
            alert.show();
            return; 
        }
    }
}
        
      /**
     * validateHasTime
     * Verifies End time or start time do not overlap, come in impossible sequence, or exceed hours of operation
     * @param cb1
     * @param cb2
     * @param ld1
     */
    public static void validateHasTime(LocalTime cb1, LocalTime cb2,LocalDate ld1){  
        boolean valid = true;
        LocalDateTime ldt1 = LocalDateTime.of(ld1, cb1);
        LocalDateTime ldt2 = LocalDateTime.of(ld1, cb2);
        LocalDateTime estldt1 = changeToEst(ld1, cb1);
        LocalDateTime estldt2 = changeToEst(ld1, cb2);
        LocalTime estlt1 = estldt1.toLocalTime();
        LocalTime estlt2 = estldt2.toLocalTime();
        LocalTime minTime = LocalTime.of(8, 00);
        LocalTime maxTime = LocalTime.of(22, 00);
        if (ldt2.isBefore(ldt1)){
            appointmentTimeAlerts(1);  
        }else if(ldt1.equals(ldt2)){
            appointmentTimeAlerts(2);
        }else if((estlt1.isBefore(minTime)) || (estlt1.isAfter(maxTime)) ){
            appointmentTimeAlerts(4);
        }else if((estlt2.isBefore(minTime)) || (estlt2.isAfter(maxTime))){
            appointmentTimeAlerts(5);
        }return;
    }   
        
        
    public static void addTime(ComboBox startTimedd, ComboBox endTimedd){
        for(int i=0;i<24;i++){
        startTimedd.getItems().add(LocalTime.of(i, 0));
        endTimedd.getItems().add(LocalTime.of(i, 0));
        }
    }
    
    
    public static void addContacts(ComboBox contactdd) {
    ObservableList<Contacts> contListB = DAOContacts.getAllContacts();
    contactdd.setItems(contListB);
    }
    
    public static void addCustomers(ComboBox AppointmentCustomercb) {
    ObservableList<Customers> custListB = DAOCustomers.getAllCustomers();
    AppointmentCustomercb.setItems(custListB);
    }
    
    public static void addUsers(ComboBox usercb) {
        ObservableList<User> userListB = DAOUser.getAllUsers();
        usercb.setItems(userListB);
    }
    
}

