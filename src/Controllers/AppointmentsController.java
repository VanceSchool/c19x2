/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static Helper.Alerts.alertGroup;
import Helper.DAOLists;
import static Helper.DAOUpdateData.deleteAppointment;
import Models.Appointments;
import Models.Customers;
import java.io.IOException;
import java.net.URL;
import java.security.Timestamp;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *FXML Controller class
 * 
 *@author sean thompson <stho292@wgu.edu>
 */
public class AppointmentsController implements Initializable {

    @FXML
    private Button AppointmentsDeletebt;
    @FXML
    private Button AppointmentsMainMenubt;
    @FXML
    private Button AppointmentsExit;
    @FXML
    private Button AppointmentsAddbt;
    @FXML
    private Button AppointmentsModifybt;
    @FXML
    private Tab AppointmentsbyMonthTab;
    @FXML
    private Tab AppointmentsByWeekTab;
    @FXML
    private Tab AppointmentsAllTab;
    @FXML
    private TableView<Appointments> byMonthTable;
    @FXML
    private TableColumn<Appointments, Integer> AppointmentIDColm;
    @FXML
    private TableColumn<Appointments, String> AppointmentTitlecolm;
    @FXML
    private TableColumn<Appointments, String> AppointmentsDescriptioncolm;
    @FXML
    private TableColumn<Appointments, String> AppointmentsLoccolm;
    @FXML
    private TableColumn<Appointments, Integer> AppointmentsContactcolm;
    @FXML
    private TableColumn<Appointments, String> AppointmentsTypecolm;
    @FXML
    private TableColumn<Appointments, String> AppointmentsStartcolm;
    @FXML
    private TableColumn<Appointments, String> AppointmentEndColm;
    @FXML
    private TableColumn<Appointments, Integer> CustomerIDColm;
    @FXML
    private TableColumn<Appointments, Integer> UserIDColm;
    @FXML
    private TableView<Appointments> byWeekTable;
    @FXML
    private TableColumn<Appointments, Integer> AppointmentIDcolw;
    @FXML
    private TableColumn<Appointments, String> AppointmentTitlecolw;
    @FXML
    private TableColumn<Appointments, String> AppointmentsDescriptioncolw;
    @FXML
    private TableColumn<Appointments, String> AppointmentsLoccolw;
    @FXML
    private TableColumn<Appointments, Integer> AppointmentsContactcolw;
    @FXML
    private TableColumn<Appointments, String> AppointmentsTypecolw;
    @FXML
    private TableColumn<Appointments, LocalDateTime> AppointmentsStartcolw;
    @FXML
    private TableColumn<Appointments, LocalDateTime> AppointmentsEndcolw;
    @FXML
    private TableColumn<Appointments, Integer> CustomerIDColw;
    @FXML
    private TableColumn<Appointments, Integer> UserIDColw;
    @FXML
    private TableView<Appointments> allAptTable;
    @FXML
    private TableColumn<Appointments, LocalDateTime> AppointmentIDcol1a;
    @FXML
    private TableColumn<Appointments, LocalDateTime> AppointmentTitlecola;
    @FXML
    private TableColumn<Appointments, String> AppointmentsDescriptioncola;
    @FXML
    private TableColumn<Appointments, String> AppointmentsLoccola;
    @FXML
    private TableColumn<Appointments, Integer> AppointmentsContactcola;
    @FXML
    private TableColumn<Appointments, String> AppointmentsTypecola;
    @FXML
    private TableColumn<Appointments, LocalDateTime> AppointmentsStartcola;
    @FXML
    private TableColumn<Appointments, LocalDateTime> AppointmentEndCola;
    @FXML
    private TableColumn<Appointments, Integer> CustomerIDCola;
    @FXML
    private TableColumn<Appointments, Integer> UserIDCola;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateAllTable();
        populateWeekTable();
    }    
    /*
     *@FXML
    */
    @FXML
    public void handleCMainMenubt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Main.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customer Menu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleCExitbt(ActionEvent event) {
        
    }

    @FXML
    private void handleCAddbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/AddAppointment.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Menu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleAmodifybt(ActionEvent event) throws IOException {
        
        
        // Changes Scene
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/ModifyAppointment.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Menu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleADeletebt(ActionEvent event) throws SQLException {
     if (allAptTable.getSelectionModel().getSelectedItem() != null){
        alertGroup(2);
       Appointments delAppoint = allAptTable.getSelectionModel().getSelectedItem();
       deleteAppointment(delAppoint);
        ObservableList<Appointments> appointListC = DAOLists.getAllAppointments();
        allAptTable.setItems(appointListC);
       }else if(byWeekTable.getSelectionModel().getSelectedItem() != null){
               alertGroup(2);
       Appointments delAppoint = byWeekTable.getSelectionModel().getSelectedItem();
       deleteAppointment(delAppoint);
        ObservableList<Appointments> appointWeekListB = DAOLists.getFilteredWeekAppointments();
        byWeekTable.setItems(appointWeekListB);    
       }
    }
    
    
    private void populateAllTable(){
        ObservableList<Appointments> appointListB = DAOLists.getAllAppointments();
        System.out.println("List Size Equals:" + appointListB.size());
        allAptTable.setItems(appointListB);
        AppointmentIDcol1a.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        AppointmentTitlecola.setCellValueFactory(new PropertyValueFactory<>("title"));
        AppointmentsDescriptioncola.setCellValueFactory(new PropertyValueFactory<>("description"));
        AppointmentsLoccola.setCellValueFactory(new PropertyValueFactory<>("location"));
        AppointmentsTypecola.setCellValueFactory(new PropertyValueFactory<>("type"));
        AppointmentsContactcola.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        CustomerIDCola.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        UserIDCola.setCellValueFactory(new PropertyValueFactory<>("userId"));
        AppointmentsStartcola.setCellValueFactory(new PropertyValueFactory<>("start"));
        AppointmentEndCola.setCellValueFactory(new PropertyValueFactory<>("end"));
    }
    
        private void populateMonthTable(){
        ObservableList<Appointments> appointListB = DAOLists.getAllAppointments();
        System.out.println("List Size Equals:" + appointListB.size());
        allAptTable.setItems(appointListB);
        AppointmentIDcol1a.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        AppointmentTitlecola.setCellValueFactory(new PropertyValueFactory<>("title"));
        AppointmentsDescriptioncola.setCellValueFactory(new PropertyValueFactory<>("description"));
        AppointmentsLoccola.setCellValueFactory(new PropertyValueFactory<>("location"));
        AppointmentsTypecola.setCellValueFactory(new PropertyValueFactory<>("type"));
        AppointmentsContactcola.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        CustomerIDCola.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        UserIDCola.setCellValueFactory(new PropertyValueFactory<>("userId"));
        AppointmentsStartcola.setCellValueFactory(new PropertyValueFactory<>("start"));
        AppointmentEndCola.setCellValueFactory(new PropertyValueFactory<>("end"));
    }
        private void populateWeekTable(){
        ObservableList<Appointments> appointWeekListB = DAOLists.getFilteredWeekAppointments();
        System.out.println("List Size Equals:" + appointWeekListB.size());
        byWeekTable.setItems(appointWeekListB);
        AppointmentIDcolw.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        AppointmentTitlecolw.setCellValueFactory(new PropertyValueFactory<>("title"));
        AppointmentsDescriptioncolw.setCellValueFactory(new PropertyValueFactory<>("description"));
        AppointmentsLoccolw.setCellValueFactory(new PropertyValueFactory<>("location"));
        AppointmentsTypecolw.setCellValueFactory(new PropertyValueFactory<>("type"));
        AppointmentsContactcolw.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        CustomerIDColw.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        UserIDColw.setCellValueFactory(new PropertyValueFactory<>("userId"));
        AppointmentsStartcolw.setCellValueFactory(new PropertyValueFactory<>("start"));
        AppointmentsEndcolw.setCellValueFactory(new PropertyValueFactory<>("end"));
    }

    @FXML
    private void handleByMonthRefreshTab(Event event) {
        populateMonthTable();
    }

    @FXML
    private void handleByWeekRefreshTab(Event event) {
        populateWeekTable();
    }

    @FXML
    private void handleAllRefreshTab(Event event) {
        populateAllTable();
    }
}
