/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static Helper.Alerts.*;
import Helper.DAOLists;
import static Helper.DAOUpdateData.addAppointment;
import static Helper.Time.changeUpLocaleDateTime;
import Helper.UserfulMethods;
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
    private DatePicker endDatepicker;
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
     addTime();
     addContacts();
     addCustomers();
     
    }    

    @FXML
    private void handleCustomerSavebt(ActionEvent event) throws IOException, SQLException{
        
        validateNonEmpty(descriptiontxt, locationtxt, typetxt, titletxt);
        validateHasSelection(AppointmentCustomercb,contactdd, startTimedd, endTimedd);
        validateHasDate(startDatepick,endDatepicker );
        validateHasTime(startTimedd.getValue(),endTimedd.getValue(),startDatepick.getValue(),endDatepicker.getValue());
        alertGroup2(10);// Are you sure  you wish to save new appoinotment?
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
       newAppointment.setStart(changeUpLocaleDateTime(startDatepick.getValue(), startTimedd.getValue()));
       newAppointment.setEnd(changeUpLocaleDateTime(endDatepicker.getValue(), endTimedd.getValue()));
       addAppointment(newAppointment);
       
       alertGroup1(8);
       Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Customer.fxml"));
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       Scene scene = new Scene(root);
       stage.setTitle("Customer Menu");
       stage.setScene(scene);
       stage.show();
        
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
    
    private void handleContactdd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Appointments.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }
    
    
    private void addTime(){
    
            for(int i=0;i<24;i++){
            startTimedd.getItems().add(LocalTime.of(i, 0));
            endTimedd.getItems().add(LocalTime.of(i, 0));
        }

    }
    
    
    private void addContacts() {
        ObservableList<Contacts> contListB = DAOLists.getAllContacts();
        contactdd.setItems(contListB);
    }
    
    private void addCustomers() {
        ObservableList<Customers> custListB = DAOLists.getAllCustomers();
        AppointmentCustomercb.setItems(custListB);
    }
}
