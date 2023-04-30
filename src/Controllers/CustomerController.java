/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static Helper.Alerts.*;
import static Helper.Alerts.exitAlert;
import DAO.*;
import static DAO.DAOAppointments.deleteCustomerAppoint;
import static DAO.DAOCustomers.deleteCustomer;
import Models.Customers;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *FXML Controller class
 * 
 *@author sean thompson stho292@wgu.edu
 */
public class CustomerController implements Initializable {

    @FXML
    private Button CustomerMainMenubt;
    @FXML
    private Button CustomerExit;
    @FXML
    private Button CustomerAddbt;
    @FXML
    private Button CustomerModifybt;
    @FXML
    private Button CustomerDeletebt;
    @FXML
    private Label CustomerCustomerControlslb;
    @FXML
    private TableColumn<Customers, Integer> CustomerIDcol;
    @FXML
    private TableColumn<Customers, String> CustomerNamecol;
    @FXML
    private TableColumn<Customers, String> CustomerPhonecol;
    @FXML
    private TableColumn<Customers, String> CustomerAddresscol;
    @FXML
    private TableColumn<Customers, String> CustomerCountrycol;
    @FXML
    private TableColumn<Customers, String> CustomerCitycol;
    @FXML
    private TableColumn<Customers, String> CustomerZipCodecol;
    @FXML
    private Label CustomerCustomerlb;
    @FXML
    private TableView<Customers> customerTable;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Customers> cusListB = DAOCustomers.getAllCustomers();
        customerTable.setItems(cusListB);
        CustomerIDcol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        CustomerNamecol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        CustomerAddresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
        CustomerZipCodecol.setCellValueFactory(new PropertyValueFactory<>("postalcode"));
        CustomerPhonecol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        CustomerCountrycol.setCellValueFactory(new PropertyValueFactory<>("country"));
        CustomerCitycol.setCellValueFactory(new PropertyValueFactory<>("state"));
        
        customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
        System.out.println("I Can't Beleive Lambda is this easy");
                }
                }
                );
        
    }
    /* Method handles Main menu button behavior.
    * Sends users to previous screen by changing scenes.
    */    

    @FXML
    private void handleCMainMenubt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Main.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleCExitbt(ActionEvent event) {
        exitAlert();
    }
    
    
   @FXML
    private void handleCDeletebt(ActionEvent event) throws SQLException {
        if (customerTable.getSelectionModel().getSelectedItem() != null){
           if(alertGroupVerifyAction(1)){
           Customers delCust = customerTable.getSelectionModel().getSelectedItem();
           deleteCustomerAppoint(delCust.getCustomerID());
           deleteCustomer(delCust);
           ObservableList<Customers> cusListC = DAOCustomers.getAllCustomers();
           customerTable.setItems(cusListC);
           }return;
        }   
   }
    
    
    @FXML
    private void handleCAddbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/AddCustomer.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }
    /*Method to handle the modify button behavior.
    *Lambda function here creates listener for the table. This was a test of the lambda function
    *to be used on later pages that have multiple elements that can be selected same time.
    *As of right now it just cause the  listener to activate upon first press then take action during second press.
    *Mostly just an "annoy people" function.
    */
    @FXML
    private void handleCmodifybt(ActionEvent event) throws IOException {
        
        /*
        customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null) {
            InputStream in = getClass().getResourceAsStream("/Scenes/ModifyCustomer.fxml"); 
            FXMLLoader loader = new FXMLLoader(); 
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(getClass().getResource("/Scenes/ModifyCustomer.fxml"));
            Parent modifyPartParent = null; 
                try {
                    modifyPartParent = loader.load(in);
                } catch (IOException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            ModifyCustomerController controller = (ModifyCustomerController) loader.getController(); 
            controller.setCurrentCustomer(customerTable.getSelectionModel().getSelectedItem()); 
            Scene modifyPartScene = new Scene(modifyPartParent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(modifyPartScene);
            stage.setResizable(false);
            stage.show();
            }
            });
        */
        
        if (customerTable.getSelectionModel().getSelectedItem() != null){
            InputStream in = getClass().getResourceAsStream("/Scenes/ModifyCustomer.fxml"); 
            FXMLLoader loader = new FXMLLoader(); 
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(getClass().getResource("/Scenes/ModifyCustomer.fxml"));
            Parent modifyPartParent = loader.load(in); 
            ModifyCustomerController controller = (ModifyCustomerController) loader.getController(); 
            controller.setCurrentCustomer(customerTable.getSelectionModel().getSelectedItem()); 
            Scene modifyPartScene = new Scene(modifyPartParent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(modifyPartScene);
            stage.setResizable(false);
            stage.show();
            }
    }
}

