/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Helper.Time;
import Helper.UserfulMethods;
import Models.Appointments;
import Models.Contacts;
import Models.Customers;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *FXML Controller class
 *@author sean thompson <stho292@wgu.edu>
 */
public class ModifyAppointmentController implements Initializable {

    @FXML
    private TextField titletxt;
    @FXML
    private TextField descriptiontxt;
    @FXML
    private DatePicker startDatepick;
    @FXML
    private DatePicker endDatepicker;
    @FXML
    private ComboBox<Contacts> contactdd;
    @FXML
    private ComboBox<Integer> startTimedd;
    @FXML
    private ComboBox<Integer> endTimedd;
    @FXML
    private TextField locationtxt;
    @FXML
    private TextField typetxt;
    private Appointments currentAppointment;
    @FXML
    private Button AppointmentSavebt;
    @FXML
    private Button AppointmentBackbt;
    @FXML
    private ComboBox<Customers> AppointmentCustomercb;
    @FXML
    private TextField AppointmentIDtxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AppointmentIDtxt.setEditable(false);
        AppointmentIDtxt.setDisable(true);
    }    

    @FXML
    private void handleAppointmentSavebt(ActionEvent event) {
    }

    private void handleCustomerBackbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Appointsments.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }
    public void setCurrentAppointment(Appointments appointment) {
       
    currentAppointment = appointment; 
    appointmentModify(appointment); 
    }
    
       public void appointmentModify(Appointments appointment){       
        //Sets TextFields based on passed Customer
        currentAppointment = appointment;
        AppointmentIDtxt.setText(String.valueOf(currentAppointment.getAppointmentID()));
        titletxt.setText(currentAppointment.getTitle());
        descriptiontxt.setText(String.valueOf(currentAppointment.getDescription()));
        locationtxt.setText(String.valueOf(currentAppointment.getLocation()));
        typetxt.setText(String.valueOf(currentAppointment.getType()));

    }

    @FXML
    private void handleAppointmentBackbt(ActionEvent event) {
    }
}
