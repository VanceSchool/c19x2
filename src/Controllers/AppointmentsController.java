/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.DAOAppointments;
import static DAO.DAOAppointments.deleteAppointment;
import static Helper.Alerts.*;
import Models.Appointments;
import Models.Customers;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.fxml.JavaFXBuilderFactory;
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
 *@author sean thompson stho292@wgu.edu
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateAllTable();
        populateWeekTable();
        populateMonthTable();
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

    /* Method handles Main menu button behavior.
    * Sends users to previous screen by changing scenes.
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
        exitAlert();
    }

    @FXML
    private void handleAAddbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/AddAppointment.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Menu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleAmodifybt(ActionEvent event) throws IOException {
        if ((byWeekTable.getSelectionModel().getSelectedItem() != null) 
            || (byMonthTable.getSelectionModel().getSelectedItem() != null) 
            || (allAptTable.getSelectionModel().getSelectedItem() != null))
        {
            if(alertGroupVerifyAction(9)){;
            InputStream in = getClass().getResourceAsStream("/Scenes/ModifyAppointment.fxml"); 
            FXMLLoader loader = new FXMLLoader(); 
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(getClass().getResource("/Scenes/ModifyAppointment.fxml"));
            Parent ModifyAppointment = loader.load(in);
            ModifyAppointmentController controller = (ModifyAppointmentController) loader.getController();
            if(byMonthTable.getSelectionModel().getSelectedItem() != null){
            controller.appointmentModify(byMonthTable.getSelectionModel().getSelectedItem()); 
            }else if(allAptTable.getSelectionModel().getSelectedItem() != null){
                controller.appointmentModify(allAptTable.getSelectionModel().getSelectedItem());
            }else if(byWeekTable.getSelectionModel().getSelectedItem() != null){
                controller.appointmentModify(byWeekTable.getSelectionModel().getSelectedItem());
            }
            Scene modifyPartScene = new Scene(ModifyAppointment);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(modifyPartScene);
            stage.setResizable(false);
            stage.show();
            }
        } return;
    }

    @FXML
    private void handleADeletebt(ActionEvent event) throws SQLException {
        if (byMonthTable.getSelectionModel().getSelectedItem() != null){
            Appointments delAppoint = byMonthTable.getSelectionModel().getSelectedItem();
            if(deleteAppointmentAlert(delAppoint)){
            deleteAppointment(delAppoint);
            populateMonthTable();
            } return;
        }else if(byWeekTable.getSelectionModel().getSelectedItem() != null){
            Appointments delAppoint = byWeekTable.getSelectionModel().getSelectedItem();
            if(deleteAppointmentAlert(delAppoint)){;
            deleteAppointment(delAppoint);
            populateWeekTable();  
            }return;
        }else if(allAptTable.getSelectionModel().getSelectedItem() != null){
            Appointments delAppoint = allAptTable.getSelectionModel().getSelectedItem();
            if(deleteAppointmentAlert(delAppoint)){
            deleteAppointment(delAppoint);
            populateAllTable(); 
            }return;
            
       }
    }
    
    
    private void populateAllTable(){
        ObservableList<Appointments> appointListB = DAOAppointments.getAllAppointments();
        //System.out.println("List Size Equals:" + appointListB.size());
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
        ObservableList<Appointments> appointMonthListB = DAOAppointments.getFilteredMonthAppointments();
        //System.out.println("List Size Equals:" + appointMonthListB.size());
        byMonthTable.setItems(appointMonthListB);
        AppointmentIDColm.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        AppointmentTitlecolm.setCellValueFactory(new PropertyValueFactory<>("title"));
        AppointmentsDescriptioncolm.setCellValueFactory(new PropertyValueFactory<>("description"));
        AppointmentsLoccolm.setCellValueFactory(new PropertyValueFactory<>("location"));
        AppointmentsTypecolm.setCellValueFactory(new PropertyValueFactory<>("type"));
        AppointmentsContactcolm.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        CustomerIDColm.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        UserIDColm.setCellValueFactory(new PropertyValueFactory<>("userId"));
        AppointmentsStartcolm.setCellValueFactory(new PropertyValueFactory<>("start"));
        AppointmentEndColm.setCellValueFactory(new PropertyValueFactory<>("end"));
    }
        
        
        
        private void populateWeekTable(){
        ObservableList<Appointments> appointWeekListB = DAOAppointments.getFilteredWeekAppointments();
        //System.out.println("List Size Equals:" + appointWeekListB.size());
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

}
