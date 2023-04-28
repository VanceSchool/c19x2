/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static Helper.Alerts.alertGroup2;
import static Helper.DAOLists.getFilteredContacts;
import Helper.TimeMethods;
import static Helper.TimeMethods.changeToEst;
import Helper.UserfulMethods;
import static Helper.UserfulMethods.addContacts;
import static Helper.UserfulMethods.addCustomers;
import static Helper.UserfulMethods.addTime;
import static Helper.UserfulMethods.validateHasDate;
import static Helper.UserfulMethods.validateHasSelection;
import static Helper.UserfulMethods.validateHasTime;
import static Helper.UserfulMethods.validateNonEmpty;
import Models.Appointments;
import Models.Contacts;
import Models.Customers;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private ComboBox<Contacts> contactdd;
    @FXML
    private ComboBox<LocalTime> startTimedd;
    @FXML
    private ComboBox<LocalTime> endTimedd;
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
    private TextField appointmentIDtxt;
    @FXML
    private Label startTimelb;
    @FXML
    private Label endTimelb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        appointmentIDtxt.setEditable(false);
        appointmentIDtxt.setDisable(true);
     addTime(startTimedd, endTimedd);
     addContacts(contactdd);
     addCustomers(AppointmentCustomercb);
    }    

    @FXML
    private void handleAppointmentSavebt(ActionEvent event) {
        validateNonEmpty(descriptiontxt, locationtxt, typetxt, titletxt);
        validateHasSelection(AppointmentCustomercb,contactdd, startTimedd, endTimedd);
        validateHasDate(startDatepick);
        //validateHasTime(startTimedd.getValue(),endTimedd.getValue(),startDatepick.getValue());
        alertGroup2(9);// Are you sure  you wish to save Changed appoinotment?
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
        
        
        appointmentIDtxt.setText(String.valueOf(appointment.getAppointmentID()));
        titletxt.setText(currentAppointment.getTitle());
        descriptiontxt.setText(String.valueOf(currentAppointment.getDescription()));
        locationtxt.setText(String.valueOf(currentAppointment.getLocation()));
        typetxt.setText(String.valueOf(currentAppointment.getType()));
        startTimedd.setValue(appointment.getStart().toLocalTime());
        endTimedd.setValue(appointment.getEnd().toLocalTime());
        startDatepick.setValue(appointment.getStart().toLocalDate());
        
        contactdd.setItems(getFilteredContacts(appointment.getContactId()));
              

    }

    @FXML
    private void handleAppointmentBackbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Appointments.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleDatePicker(ActionEvent event) {
    }

    @FXML
    private void handleStartTimedd(ActionEvent event) {
    }

    @FXML
    private void handleEndTimedd(ActionEvent event) {
                if(startDatepick.getValue() != null){
             LocalTime lt2 = endTimedd.getValue();
             LocalDate ld = startDatepick.getValue();
             LocalDateTime ldt2;
                ldt2 = changeToEst(ld, lt2);
             endTimelb.setText("Time EST " + ldt2.toLocalTime().toString());
        }else{
            endTimelb.setText("Please Choose Date");
        }
        
    }

    @FXML
    private void handleContactdd(ActionEvent event) {
        addContacts(contactdd);
    }

    @FXML
    private void handleCustomerdd(ActionEvent event) {
        addCustomers(AppointmentCustomercb);
    }
}
