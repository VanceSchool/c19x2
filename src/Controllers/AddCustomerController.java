/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static DAO.DAOCustomers.addNewCustomers;
import static Helper.Alerts.*;
import Helper.UserfulMethods;
import DAO.DAOLists;
import static Helper.UserfulMethods.validateHasSelection;
import static Helper.UserfulMethods.validateNonEmpty;
import Models.Countries;
import Models.Customers;
import Models.Provinces;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.util.StringConverter;

/**
 *FXML Controller class
 * 
 *@author sean thompson <stho292@wgu.edu>
 */
public class AddCustomerController implements Initializable {

    @FXML
    private Button CustomerSavebt;
    @FXML
    private Button CustomerBackbt;
    @FXML
    private TextField CustomerNametf;
    @FXML
    private TextField CustomerAddresstf;
    @FXML
    private TextField CustomerPhonetf;
    @FXML
    private TextField CustomerPostaltf;
    @FXML
    private ComboBox<Countries> CustomerCountrycb;
    @FXML
    private ComboBox<Provinces> CustomerStatecb;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       addCountries();
        }  

    @FXML
    private void handleCustomerSavebt(ActionEvent event) throws IOException, SQLException {
        validateNonEmpty(CustomerNametf, CustomerAddresstf, CustomerPhonetf, CustomerPostaltf);
        validateHasSelection(CustomerCountrycb, CustomerStatecb);
             //if (allAptTable.getSelectionModel().getSelectedItem() != null){
        if(alertGroupVerifyAction(6)){
       Customers custMod = new Customers();
       Provinces modProvCust = CustomerStatecb.getValue();
       custMod.setState(modProvCust.getDivName());
       custMod.setAddress(CustomerAddresstf.getText());
       custMod.setCustomerName(CustomerNametf.getText());
       custMod.setCustomerPhone(CustomerPhonetf.getText());
       custMod.setCustomerPostalCode(CustomerPostaltf.getText());
       addNewCustomers(custMod);
       
       alertGroupDatabaseChange(1);
       Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Customer.fxml"));
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       Scene scene = new Scene(root);
       stage.setTitle("Customer Menu");
       stage.setScene(scene);
       stage.show();
        }
        return;
    }

    @FXML
    private void handleCustomerBackbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Customer.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customer Menu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleCustomerCountrycb(ActionEvent event) {
        Countries jam = CustomerCountrycb.getValue();
        int cId = jam.getCountryId();
        ObservableList<Provinces> provListB = DAOLists.getFilteredProvinces(cId);
        //System.out.println(provListB);
        CustomerStatecb.setItems(provListB);
    }

    @FXML
    private void handleCustomerStatecb(ActionEvent event) {
    }

   
    private void addCountries() {

         ObservableList<Countries> countListB = DAOLists.getAllCountries();
        CustomerCountrycb.setItems(countListB);
    }
    
    
}
