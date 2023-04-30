/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static Helper.Alerts.exitAlert;
import Helper.JDBC;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**Class that contains the controls for the main menu.
 *FXML Controller class
 * 
 *@author sean thompson <stho292@wgu.edu>
 */
public class MainController implements Initializable {

    @FXML
    private Button MainCustoersbt;
    @FXML
    private Button MainAppointmentsbt;
    @FXML
    private Button MainReportsbt;
    @FXML
    private Button MainLogoutbt;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
/**Method that moves user to customer page.
 * 
 * @param event
 * @throws IOException 
 */
    @FXML
    private void handleCustomersbt(ActionEvent event) throws IOException {
            // load widget hierarchy of next screen
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Customer.fxml"));
        //Get a stage
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //create the new scene
        Scene scene = new Scene(root);
        //set title
        stage.setTitle("Customers");
        //set the scene on the the stage
        stage.setScene(scene);
        // "raise the curtain" Show the scene
        stage.show();
    }

    /**Method that moves user to appointments page.
    * 
    * @param event
    * @throws IOException 
    */
    @FXML
    private void handleAppointmentsbt(ActionEvent event) throws IOException {
            // load widget hierarchy of next screen
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Appointments.fxml"));
        //Get a stage
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //create the new scene
        Scene scene = new Scene(root);
        //set title
        stage.setTitle("Appointments");
        //set the scene on the the stage
        stage.setScene(scene);
        // "raise the curtain" Show the scene
        stage.show();
    }
    
    /**Method that moves user to reports page.
    * 
    * @param event
    * @throws IOException 
    */
    @FXML
    private void handleReportsbt(ActionEvent event) throws IOException {
            // load widget hierarchy of next screen
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Reports.fxml"));
        //Get a stage
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //create the new scene
        Scene scene = new Scene(root);
        //set title
        stage.setTitle("Reports");
        //set the scene on the the stage
        stage.setScene(scene);
        // "raise the curtain" Show the scene
        stage.show();
    }
    /**Method that logs user out of application.
    * 
    * @param event
    * @throws IOException 
    */
    @FXML
    private void handleLogoutbt(ActionEvent event) {
    exitAlert();
        }
    }
   
