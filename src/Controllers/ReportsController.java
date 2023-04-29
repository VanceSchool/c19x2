/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.DAOLists;
import Models.Appointments;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button deleteBt;
    @FXML
    private Button cancelBt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handleDeletebt(ActionEvent event) {
    }

    @FXML
    private void handleCancelbt(ActionEvent event) {
    }
   /* 
    private void populateTable(){
        ObservableList<Appointments> appointListB = DAOLists.getFilteredCustAppointments();
        //System.out.println("List Size Equals:" + appointMonthListB.size());
        byMonthTable.setItems(appointListB);
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
*/
    
}
