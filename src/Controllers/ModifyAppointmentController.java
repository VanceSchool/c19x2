/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Helper.Time;
import Models.Contacts;
import Models.Customers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private Label ModifyAppointsmentslb;
    @FXML
    private Button CustomerSavebt;
    @FXML
    private Button CustomerBackbt;
    @FXML
    private TableView<Customers> customerContactTable;
    @FXML
    private TableColumn<Customers, Integer> customerIdcol;
    @FXML
    private TableColumn<Customers, String> CustomerNameCol;
    @FXML
    private TextField customerNametxt;
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
    private ComboBox<Time> startTimedd;
    @FXML
    private ComboBox<Time> endTimedd;
    @FXML
    private TextField locationtxt;
    @FXML
    private TextField typetxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleCustomerSavebt(ActionEvent event) {
    }

    @FXML
    private void handleCustomerBackbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Appointsments.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }
    
}
