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
import static DAO.DAOAppointments.getAllAppointments;
import Models.User;
import java.sql.Timestamp;
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

     /** Class contains methods that can be  re-used for multiple pages.
     * 
     * .
     * @author sean thompson stho292@wgu.edu
     */ 
public abstract class UserfulMethods {
    
    
    
     /** Method verifies TextField is not empty.
     * If text field is empty it returns true and runs error alerts.
     * @param tf
     */ 
    public static boolean validateNonEmpty(TextField... tf) { 
        for (TextField textField: tf){
            if (textField.getText().isEmpty()){

            return true; 
            }
        }return false;
    }
        
    /** Method that validates ComboBox has a selection, can be used for multiple ComboBox.
    * If combo box has no selection this statement returns true and runs alert.
    * @param cb
    */ 
    public static boolean validateHasSelection(ComboBox... cb) {  
        
        for (ComboBox combobox: cb){
            if (combobox.getValue()==null){
            
            return true; 
            }
        }return false;
    }
    
    /** Method verifies DatePicker has a selection.
    * Verifies Date Picker has a selection returns false if it does, returns true if  it doesn't.
    * @param dp
    */ 
    public static boolean validateHasDate(DatePicker... dp) { 
        for (DatePicker datepicker: dp){
            if (datepicker.getValue()==null){

            return true; 
            }
        }return false;
    }
        
    /** Method Verifies End time or start time do not overlap, come in impossible sequence, or exceed hours of operation.
    * @param cb1
    * @param cb2
    * @param ld1
    */
    public static boolean validateHasTime(LocalTime cb1, LocalTime cb2,LocalDate ld1){  
        
        LocalDateTime estldt1 = changeToEst(ld1, cb1);
        LocalDateTime estldt2 = changeToEst(ld1, cb2);
        LocalTime estlt1 = estldt1.toLocalTime();
        LocalTime estlt2 = estldt2.toLocalTime();
        LocalTime minTime = LocalTime.of(8, 00);
        LocalTime maxTime = LocalTime.of(22, 00);
        if(cb1.isAfter(cb2) || cb2.isBefore(cb1)){
            appointmentTimeAlerts(1);
            return true;
        }else if(cb2.isBefore(cb1)){   
            appointmentTimeAlerts(1);
            return true;
        }else if(cb1.equals(cb2)){
            appointmentTimeAlerts(2);
            return true;
        }else if((estlt1.isBefore(minTime)) || (estlt1.isAfter(maxTime)) ){
            appointmentTimeAlerts(4);
            return true;
        }else if((estlt2.isBefore(minTime)) || (estlt2.isAfter(maxTime))){
            appointmentTimeAlerts(5);
            return true;      
        }
        return false;
    }
        
    /** Method used to add time to ComboBox, adds from 0 to 23.
     *
     * @param startTimedd
     * @param endTimedd
     */
    public static void addTime(ComboBox startTimedd, ComboBox endTimedd){
        for(int i=0;i<24;i++){
        startTimedd.getItems().add(LocalTime.of(i, 0));
        endTimedd.getItems().add(LocalTime.of(i, 0));
        }
    }
    
    /** Method adds all contacts to ComboBox.
     *
     * @param contactdd
     */
    public static void addContacts(ComboBox contactdd) {
    ObservableList<Contacts> contListB = DAOContacts.getAllContacts();
    contactdd.setItems(contListB);
    }
    
    /** Method adds all customers to ComboBox.
     *
     * @param AppointmentCustomercb
     */
    public static void addCustomers(ComboBox AppointmentCustomercb) {
    ObservableList<Customers> custListB = DAOCustomers.getAllCustomers();
    AppointmentCustomercb.setItems(custListB);
    }
    
    /** Method can be used to add all Users to ComboBox.
     *
     * @param usercb
     */
    public static void addUsers(ComboBox usercb) {
        ObservableList<User> userListB = DAOUser.getAllUsers();
        usercb.setItems(userListB);
    }
    
}

