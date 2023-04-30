/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.DAOAppointments;
import DAO.DAOContacts;
import DAO.DAOLists;
import Models.Appointments;
import Models.Contacts;
import Models.Report;
import java.net.URL;
import java.security.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class ReportsController implements Initializable {

    @FXML
    private Label ModifyAppointsmentslb;
    @FXML
    private TableView<Appointments> byCustomerTable;
    @FXML
    private TableColumn<Appointments, Integer> AppointmentIDColm;
    @FXML
    private TableColumn<Appointments, String> AppointmentTitlecolm;
    @FXML
    private TableColumn<Appointments, String> AppointmentsDescriptioncolm;
    @FXML
    private TableColumn<Appointments, String> AppointmentsTypecolm;
    @FXML
    private TableColumn<Appointments, Timestamp> AppointmentsStartcolm;
    @FXML
    private TableColumn<Appointments, Timestamp> AppointmentEndColm;
    @FXML
    private TableColumn<Appointments, Integer> CustomerIDColm;
    @FXML
    private Button Backbt;
    @FXML
    private Tab custAppointTab;
    @FXML
    private Tab appointCountTab;
    @FXML
    private ComboBox<Contacts> Customerscd;
    @FXML
    private TableView<Report> appointmentCountTable;
    @FXML
    private TableColumn<Report, String> CountappointTypeCol;
    @FXML
    private TableColumn<Report, String> CountAppointMonth;
    @FXML
    private TableColumn<Report, String> CountAppointTotal;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addContacts(Customerscd);
    }    


    @FXML
    private void handleCancelbt(ActionEvent event) {
    }
   
    private void populateTableContactAppointment(String name){
        ObservableList<Appointments> appointListB = DAOAppointments.getFilteredContactsAppointments(name);
        byCustomerTable.setItems(appointListB);
        AppointmentIDColm.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        AppointmentTitlecolm.setCellValueFactory(new PropertyValueFactory<>("title"));
        AppointmentsDescriptioncolm.setCellValueFactory(new PropertyValueFactory<>("description"));
        AppointmentsTypecolm.setCellValueFactory(new PropertyValueFactory<>("type"));
        CustomerIDColm.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        AppointmentsStartcolm.setCellValueFactory(new PropertyValueFactory<>("start"));
        AppointmentEndColm.setCellValueFactory(new PropertyValueFactory<>("end"));
    }

    @FXML
    private void handleOnCustAppoint(Event event) {
    }

    @FXML
    private void handleOnCountSelection(Event event) {
    }

    @FXML
    private void handleOnCustomerSelection(Event event) {
        populateTableContactAppointment(Customerscd.getValue().toString());
    }
    
    public static void addContacts(ComboBox contactdd) {
    ObservableList<Contacts> contListB = DAOContacts.getAllContacts();
    contactdd.setItems(contListB);
    }
}
