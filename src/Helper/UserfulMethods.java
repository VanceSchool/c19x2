/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import static Helper.Alerts.alertGroup3;
import Models.Customers;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

     /**
     * @class UsefulMethtods
     * Contains methods that can be  re-used for multiple pages
     * @author sean thompson <stho292@wgu.edu>
     */ 
public abstract class UserfulMethods {
    
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
     * Verifies End Date and Time do not overlap or come before Start Date and Time
     * @param cb1
     * @param cb2
     * @param ld1
     * @param ld2
     */
        public static void validateHasTime(LocalTime cb1, LocalTime cb2,LocalDate ld1, LocalDate ld2) {  
            LocalDateTime ldt1 = LocalDateTime.of(ld1, cb1);
            LocalDateTime ldt2 = LocalDateTime.of(ld2, cb2);
            if (ldt2.isBefore(ldt1)){
            alertGroup3(1);
        }else if(ldt1.equals(ldt2)){
            alertGroup3(2);
        }else if(ldt2.isBefore(LocalDateTime.now())){
            alertGroup3(3);
        }else if (ldt2.isBefore(LocalDateTime.now())){
            alertGroup3(3); 
        }
}
        /*
        public static ObservableList<Integer> displayMinutes(){
       
       ObservableList<Integer> minuteList = FXCollections.observableArrayList();
        minuteList.addAll(00, 15, 30, 45);
            return minuteList;
        }
        */
    }

