/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class Alerts {
    
    
    public static void exitAlert(){
        ResourceBundle rb = ResourceBundle.getBundle("Language.lang", Locale.getDefault());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle(rb.getString("validation"));
        alert.setHeaderText(rb.getString("confirm"));
        alert.setContentText(rb.getString("action"));
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            System.exit(0);
            JDBC.closeConnection();
        }
    }
   
    public static void passwordAlert(){
        ResourceBundle rb = ResourceBundle.getBundle("Language.lang", Locale.getDefault());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle(rb.getString("validation"));
        alert.setHeaderText(rb.getString("useandpass"));
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK);
    }
    
    
    public static void alertGroup2(int type){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Validation");
        alert.setHeaderText("Validation");
                switch (type) {
            case 1: {
                alert.setContentText("Are you sure you wish to delete this customer and all appointments associated?");
                break;
            }
            case 2: {
                alert.setContentText("Are you sure you wish to delete this Appointment?");
                break;
            }
            case 4: {
                alert.setContentText("Are You Sure You wish to update");
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
        alert.showAndWait();
    }
    
    
    public static void alertGroup1(int type){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation");
        alert.setHeaderText("Validation");
                switch (type) {
            case 3: {
                alert.setContentText("Customer Has Been Updated");
                break;
            }
            case 4: {
                alert.setContentText("Are You Sure You wish to update");
                break;
            }
            case 5: {
                alert.setContentText("Customer Updated");
                break;
            }
            case 7: {
                alert.setContentText("Please Fill All Fields");
                break;
            }
                        
            case 8: {
                alert.setContentText("New Customer Created!");
                break;
            }
        }
        alert.showAndWait();
    }
    
}
