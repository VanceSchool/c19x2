/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static DAO.DAOAppointments.addAppointment;
import static Helper.Alerts.*;
import DAO.DAOLists;
import static Helper.TimeMethods.*;
import Helper.UserfulMethods.*;
import static Helper.UserfulMethods.addContacts;
import static Helper.UserfulMethods.addCustomers;
import static Helper.UserfulMethods.addTime;
import static Helper.UserfulMethods.validateHasSelection;
import static Helper.UserfulMethods.validateNonEmpty;
import static Helper.UserfulMethods.validateHasDate;
import static Helper.UserfulMethods.validateHasTime;
import Models.Appointments;
import Models.Contacts;
import Models.Customers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
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
 * FXML Controller class
 *
 * @author LabUser
 */
public class AddAppointmentsController implements Initializable {

    @FXML
    private Button CustomerSavebt;
    @FXML
    private Button CustomerBackbt;
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
    @FXML
    private ComboBox<Customers> AppointmentCustomercb;
    @FXML
    private Label startTimelb;
    @FXML
    private Label endTimelb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     addTime(startTimedd, endTimedd);
     addContacts(contactdd);
     addCustomers(AppointmentCustomercb);
     
    }    

    @FXML
    private void handleCustomerSavebt(ActionEvent event) throws IOException, SQLException{
        
        validateNonEmpty(descriptiontxt, locationtxt, typetxt, titletxt);
        validateHasSelection(AppointmentCustomercb,contactdd, startTimedd, endTimedd);
        validateHasDate(startDatepick);
        validateHasTime(startTimedd.getValue(),endTimedd.getValue(),startDatepick.getValue());
        if(alertGroupVerifyAction(10)){;// Are you sure  you wish to save new appoinotment?
        Customers modAppointCust = AppointmentCustomercb.getValue();
        Contacts modAppointCon = contactdd.getValue();
        System.out.println(modAppointCon.getContactId());
        System.out.println(modAppointCust.getCustomerID());
       Appointments newAppointment = new Appointments();
       newAppointment.setTitle(titletxt.getText());
       newAppointment.setDescription(descriptiontxt.getText());
       newAppointment.setLocation(locationtxt.getText());
       newAppointment.setType(typetxt.getText());
       newAppointment.setCustomerId(modAppointCust.getCustomerID());
       newAppointment.setContactId(modAppointCon.getContactId());
       LocalDateTime starttime = changeUpLocaleDateTime(startDatepick.getValue(), startTimedd.getValue());
       LocalDateTime endtime = changeUpLocaleDateTime(startDatepick.getValue(), endTimedd.getValue());
       newAppointment.setStart(Timestamp.valueOf(starttime));
       newAppointment.setEnd(Timestamp.valueOf(endtime));
       addAppointment(newAppointment);
       
       alertGroup1(8);
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Appointments.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
        }return;
        
    }
    
        @FXML
    private void handleDatePicker(ActionEvent event) {
        if((startTimedd.getValue() != null) && (endTimedd.getValue() != null)){
             LocalTime lt1 = startTimedd.getValue();
             LocalTime lt2 = endTimedd.getValue();
             LocalDateTime ldt1;
             LocalDateTime ldt2;
             LocalDate ld = startDatepick.getValue();
             ldt1 = changeToEst(ld, lt1);
             startTimelb.setText("Time EST " + ldt1.toLocalTime().toString());
              ldt2 = changeToEst(ld, lt2);
             endTimelb.setText("Time EST " + ldt2.toLocalTime().toString());
        } else if((startTimedd.getValue() != null) && (endTimedd.getValue() == null)){
               LocalTime lt1 = endTimedd.getValue();
             LocalDate ld = startDatepick.getValue();
             LocalDateTime ldt1;
              ldt1 = changeToEst(ld, lt1);
             startTimelb.setText("Time EST " + ldt1.toLocalTime().toString());
             endTimelb.setText("Please Choose Time");
        } else if((startTimedd.getValue() == null) && (endTimedd.getValue() != null)){
             LocalTime lt2 = endTimedd.getValue();
             LocalDate ld = startDatepick.getValue();
             LocalDateTime ldt2;
              ldt2 = changeToEst(ld, lt2);
             endTimelb.setText("Time EST " + ldt2.toLocalTime().toString());
             startTimelb.setText("Please Choose Time");
        }else {
            startTimelb.setText("Please Choose Time");
            endTimelb.setText("Please Choose Time");
        }
    }
    
       @FXML
    private void handleStartTimedd(ActionEvent event) {
        if(startDatepick.getValue() != null){
             LocalTime lt1 = startTimedd.getValue();
             LocalDate ld = startDatepick.getValue();
             LocalDateTime ldt1;
             ldt1 = changeToEst(ld, lt1);
             startTimelb.setText("Time EST " + ldt1.toLocalTime().toString());
        }else{

             startTimelb.setText("Please Choose Date");
        }
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
    private void handleCustomerBackbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Appointments.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }
   
}
