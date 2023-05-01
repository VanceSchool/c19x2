/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Models.Appointments;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

/** This class contains various alerts that are used throughout application.
 *
 *@author sean thompson stho292@wgu.edu
 */
public class Alerts {
    
    /** Method created to alert of exiting program, upon confirmation connection to database will close 
    * and system will exit.
    * Lambda expression used to bypass if statement, methods to exit system and close connection were merged. 
    * 
    */
    public static void exitAlert(){
        ResourceBundle rb = ResourceBundle.getBundle("Language.lang", Locale.getDefault());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle(rb.getString("validation"));
        alert.setHeaderText(rb.getString("confirm"));
        alert.setContentText(rb.getString("action"));
        /*
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
            JDBC.closeConnection();
        }
        */
        alert.showAndWait()
        .filter(response -> response == ButtonType.OK)
        .ifPresent(response -> JDBC.closeConnection());
    }
   
    /** This method is used to alert if password is wrong at login.
     *
     */
    public static void passwordAlert(){
        ResourceBundle rb = ResourceBundle.getBundle("Language.lang", Locale.getDefault());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle(rb.getString("validation"));
        alert.setHeaderText(rb.getString("useandpass"));
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            return;
        };
    }
    
    
    /** Contains alerts having to do with appointment schedules.
     * Triggered if an appointment time is not correct.
    * 
    * @param type
    */
        public static void appointmentTimeAlerts(int type){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation");
        alert.setHeaderText("Validation");
                switch (type) {
            case 1: {
                alert.setContentText("End Time is Before Beginning Time, Please ammend!");
                break;
            }
            case 2: {
                alert.setContentText("End Time Is Same As Beginning Time, Please Ammend!");
                break;
            }
            case 3: {
                alert.setContentText("Meeting is Before Current Time, Please Ammend!");
                break;
            }
            case 4: {
                alert.setContentText("Appointment Start Time is Outside Normal Hours of Operation 8AM to 10PM EST, Please Ammend!");
                break;
            }
            case 5: {
                alert.setContentText("Appointment End Time is Outside Normal Hours of Operation 8AM to 10PM EST, Please Ammend!");
                break;
            }
            
            case 6: {
                alert.setContentText("Previous Appointment During That Scheduled Time");
                break;
            }
                        
        }
            alert.show();
            return;
    }
    

    /** Method used to verify choice after an action that may change date.
    *
    * 
    * @param type
     *@return true/false 
    */
    public static boolean alertGroupVerifyAction(int type){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Validation");
        alert.setHeaderText("Validation");
        switch(type){
            case 1:{
                alert.setContentText("Are you sure you wish to delete this customer and all appointments associated?");
                break;
            }
            case 2: {
                alert.setContentText("Are you sure you wish to delete this Appointment?");
                break;
            }
            case 4: {
                alert.setContentText("Are You Sure You wish to modify this Customer?");
                break;
            }
            case 6: {
                alert.setContentText("Are You Sure you wish to Save New Customer?");
                break;
            }
            case 9: {
                alert.setContentText("Are you sure you want to modify this Appoinment?");
                break;
            }
            case 10: {
                alert.setContentText("Are you sure you want to save this Appoinment?");
                break;
            }
            
        }
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }
    
    
    /** Method to alert record change or added to database.
    *
    * 
    * @param type
    */
    public static void alertGroupDatabaseChange(int type){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation");
        alert.setHeaderText("Validation");
        switch(type){
            case 1: {
                alert.setContentText("New Customer Created!");
                break;
            }
            case 2: {
                alert.setContentText("Customer Updated!");
                break;
            }
            case 3: {
                alert.setContentText("New Appointment Created!");
                break;
            }
            case 4: {
                alert.setContentText("Appointment Updated!");
                break;
            }          
        }
        alert.showAndWait();
        
    }
    
    /** Method to display alert showing details of appointment to be deleted.
     *
     * @param delappoint
     * @return
     */
    public static boolean deleteAppointmentAlert(Appointments delappoint){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Are you Sure you Want to Delete Appointment?");
        alert.setContentText("AppointmentID: " + delappoint.getAppointmentID()
                           + " Appointment Type: " + delappoint.getType() + ".\n"
                           + "Proceed With Delete?/");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }
    
}
