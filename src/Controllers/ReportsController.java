/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.DAOAppointments;
import DAO.DAOContacts;
import DAO.DAOLists;
import static Helper.UserfulMethods.addContacts;
import Models.Appointments;
import Models.Contacts;
import Models.Report;
import java.io.IOException;
import java.net.URL;
import java.security.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    @FXML
    private Tab elapsedTab;
    @FXML
    private TableView<Appointments> elapsedAppointmentsTable;
    @FXML
    private TableColumn<Appointments, Integer> AppointmentIDColm1;
    @FXML
    private TableColumn<Appointments, String> AppointmentTitlecolm1;
    @FXML
    private TableColumn<Appointments, String> AppointmentsDescriptioncolm1;
    @FXML
    private TableColumn<Appointments, String> AppointmentsTypecolm1;
    @FXML
    private TableColumn<Appointments, String> AppointmentsStartcolm1;
    @FXML
    private TableColumn<Appointments, String> AppointmentEndColm1;
    @FXML
    private TableColumn<Appointments, Integer> CustomerIDColm1;
    @FXML
    private Tab customerLocationTab;
    @FXML
    private TableView<Report> CustomerLocactionTablle;
    @FXML
    private TableColumn<Report, String> customerCountrycol;
    @FXML
    private TableColumn<Report, String> customerRegioncol;
    @FXML
    private TableColumn<Report, Integer> customerRegioinTotalcol;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addContacts(Customerscd);
        addTotalReport();
        populateLapsedTable();
        populateCustomersByRegionTable();
    }    

    /*
    *@FXML
    */
    @FXML
    private void handleBackbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Main.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customer Menu");
        stage.setScene(scene);
        stage.show();
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
    
    private void populateLapsedTable(){
        ObservableList<Appointments> oldAppointments = DAOAppointments.getLapsedAppointments();    
        elapsedAppointmentsTable.setItems(oldAppointments);
        AppointmentIDColm1.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        AppointmentTitlecolm1.setCellValueFactory(new PropertyValueFactory<>("title"));
        AppointmentsDescriptioncolm1.setCellValueFactory(new PropertyValueFactory<>("description"));
        AppointmentsTypecolm1.setCellValueFactory(new PropertyValueFactory<>("type"));
        AppointmentsStartcolm1.setCellValueFactory(new PropertyValueFactory<>("start"));
        AppointmentEndColm1.setCellValueFactory(new PropertyValueFactory<>("end"));
        CustomerIDColm1.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }
    
    private void populateCustomersByRegionTable(){
        ObservableList<Report> custByRegion = DAOLists.getCustomersPerRegion();
        CustomerLocactionTablle.setItems(custByRegion);
        customerCountrycol.setCellValueFactory(new PropertyValueFactory<>("country"));
        customerRegioncol.setCellValueFactory(new PropertyValueFactory<>("region"));
        customerRegioinTotalcol.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    @FXML
    private void handleOnCustAppoint(Event event) {
        
    }

    @FXML
    private void handleOnCountSelection(Event event) {
        addTotalReport();
    }

    @FXML
    private void handleOnCustomerSelection(Event event) {
        populateTableContactAppointment(Customerscd.getValue().toString());
    }
    
    
    public void addTotalReport(){
    ObservableList<Report> reportList = DAOLists.getAssignmentReportTypeMonth();
    appointmentCountTable.setItems(reportList);
    CountappointTypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
    CountAppointMonth.setCellValueFactory(new PropertyValueFactory<>("Month"));
    CountAppointTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }
        
    public static void addContacts(ComboBox contactdd) {
    ObservableList<Contacts> contListB = DAOContacts.getAllContacts();
    contactdd.setItems(contListB);
    }

    @FXML
    private void handleOnElapsedTab(Event event) {
        populateLapsedTable();
    }

    @FXML
    private void handleCustomerLocationTab(Event event) {
        populateCustomersByRegionTable();
    }
}
