/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static DAO.DAOAppointments.addAppointment;
import static DAO.DAOAppointments.modifyAppointment;
import static DAO.DAOContacts.findContactByID;
import static DAO.DAOCustomers.findCustomeresByID;
import static Helper.Alerts.alertGroupDatabaseChange;
import static Helper.Alerts.alertGroupVerifyAction;
import Helper.TimeMethods;
import static Helper.TimeMethods.changeToEst;
import static Helper.TimeMethods.changeUpLocaleDateTime;
import Helper.UserfulMethods;
import static Helper.UserfulMethods.*;
import Models.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
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
    private void handleAppointmentSavebt(ActionEvent event) throws IOException, SQLException {
        validateNonEmpty(descriptiontxt, locationtxt, typetxt, titletxt);
        validateHasSelection(AppointmentCustomercb,contactdd, startTimedd, endTimedd);
        validateHasDate(startDatepick);
        //validateHasTime(startTimedd.getValue(),endTimedd.getValue(),startDatepick.getValue());
        if(alertGroupVerifyAction(9)){
            Customers modAppointCust = AppointmentCustomercb.getValue();
            Contacts modAppointCon = contactdd.getValue();
            int appointmentID = Integer.parseInt(appointmentIDtxt.getText());
            //System.out.println(modAppointCon.getContactId());
            //System.out.println(modAppointCust.getCustomerID());
            Appointments changeAppointment = new Appointments();
            changeAppointment.setTitle(titletxt.getText());
            changeAppointment.setDescription(descriptiontxt.getText());
            changeAppointment.setLocation(locationtxt.getText());
            changeAppointment.setType(typetxt.getText());
            changeAppointment.setCustomerId(modAppointCust.getCustomerID());
            changeAppointment.setContactId(modAppointCon.getContactId());
            LocalDateTime starttime = changeUpLocaleDateTime(startDatepick.getValue(), startTimedd.getValue());
            LocalDateTime endtime = changeUpLocaleDateTime(startDatepick.getValue(), endTimedd.getValue());
            changeAppointment.setStart(Timestamp.valueOf(starttime));
            changeAppointment.setEnd(Timestamp.valueOf(endtime));
            System.out.println(appointmentID);
            changeAppointment.setAppointmentID(appointmentID);
            System.out.println(changeAppointment.getAppointmentID());
            modifyAppointment(changeAppointment, appointmentID);
       
            alertGroupDatabaseChange(3);
            Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Appointments.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();   
        }return;
    }

    private void handleCustomerBackbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Appointsments.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }
    
    public void appointmentModify(Appointments appointment){       
        currentAppointment = appointment;
        LocalDateTime startAppointment = currentAppointment.getStart().toLocalDateTime();
        LocalDateTime endAppointment = currentAppointment.getEnd().toLocalDateTime();
        LocalTime startlt = startAppointment.toLocalTime();
        LocalTime endlt = endAppointment.toLocalTime();
        LocalDate appointDate = startAppointment.toLocalDate();
        appointmentIDtxt.setText(String.valueOf(appointment.getAppointmentID()));
        titletxt.setText(currentAppointment.getTitle());
        descriptiontxt.setText(String.valueOf(currentAppointment.getDescription()));
        locationtxt.setText(String.valueOf(currentAppointment.getLocation()));
        typetxt.setText(String.valueOf(currentAppointment.getType()));
        startTimedd.setValue(startlt);
        endTimedd.setValue(endlt);
        startDatepick.setValue(appointDate);
        contactdd.setValue(findContactByID(currentAppointment.getContactId()));
        AppointmentCustomercb.setValue(findCustomeresByID(currentAppointment.getCustomerId()));
        
        updateTimeText();
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
    updateTimeText();
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
    private void handleContactdd(ActionEvent event) {
        
    }

    @FXML
    private void handleCustomerdd(ActionEvent event) {
        
    }
    
    private void updateTimeText(){
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
        }else if((startTimedd.getValue() != null) && (endTimedd.getValue() == null)){
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
        }else{
            startTimelb.setText("Please Choose Time");
            endTimelb.setText("Please Choose Time");
        }
    }
}
