/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public abstract class UserfulMethods {
    
        public static void validateNonEmpty(TextField... tf) {        
        for (TextField textField: tf){
                if (textField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.NONE);
            alert.setTitle("Validation of Fields");
            alert.setHeaderText("Customer Not Added");
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
            alert.setHeaderText("Customer Not Added");
            alert.setContentText("Please Fill All Fields");
            alert.show();
            return; 
        }
    }
}
}
