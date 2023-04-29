/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static DAO.DAOCustomers.modifyCustomer;
import static Helper.Alerts.*;
import DAO.DAOLists;
import Helper.TimeMethods;
import static Helper.UserfulMethods.*;
import Models.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;


/**
 *FXML Controller for ModifyCustomer Scene/view
 *@author sean thompson <stho292@wgu.edu>
 */
public class ModifyCustomerController implements Initializable {

    @FXML
    private TextField CustomerNametf;
    @FXML
    private TextField CustomerAddresstf;
    @FXML
    private TextField CustomerPhonetf;
    @FXML
    private TextField CustomerPostaltf;
    @FXML
    private TextField CustomerIdtf;
    private Customers currentCustomer;
    private Set<Customers> toBeModified = new HashSet();
    @FXML
    private ComboBox<Countries> CustomerCountrycb;
    @FXML
    private ComboBox<Provinces> CustomerStatecb;
    ObservableList<Countries> countListB = FXCollections.observableArrayList();
    @FXML
    private Button CustomerSavebt;
    @FXML
    private Button CustomerBackbt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        countListB = DAOLists.getAllCountries();
        CustomerCountrycb.setItems(countListB);
        CustomerIdtf.setEditable(false);
        CustomerIdtf.setDisable(true); 
    }
    

    @FXML
    private void handleCustomerSavebt(ActionEvent event) throws SQLException, IOException {
        validateNonEmpty(CustomerNametf, CustomerAddresstf, CustomerPhonetf, CustomerPostaltf);
        validateHasSelection(CustomerCountrycb, CustomerStatecb);
        if(alertGroupVerifyAction(4)){
            Customers custMod = new Customers();
            Provinces modProvCust = CustomerStatecb.getValue();
            custMod.setState(modProvCust.getDivName());
            custMod.setAddress(CustomerAddresstf.getText());
            int customerId = Integer.parseInt(CustomerIdtf.getText());
            custMod.setCustomerID(customerId);
            custMod.setCustomerName(CustomerNametf.getText());
            custMod.setCustomerPhone(CustomerPhonetf.getText());
            custMod.setCustomerPostalCode(CustomerPostaltf.getText());
            modifyCustomer(custMod);
            alertGroupDatabaseChange(2);
            Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Customer.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Customer Menu");
            stage.setScene(scene);
            stage.show();
        }return;
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

    
    public void setCurrentCustomer(Customers customer){
        currentCustomer = customer; 
        customerModify(customer); 
    }
    
    /**
    *
    * @param customer
    */
    public void customerModify(Customers customer){       
        //Sets TextFields based on passed Customer
        currentCustomer = customer;
        CustomerIdtf.setText(String.valueOf(currentCustomer.getCustomerID()));
        CustomerNametf.setText(currentCustomer.getCustomerName());
        Countries custCounry = new Countries(currentCustomer.getCountry());
        Provinces custProvince = new Provinces(currentCustomer.getState());
        CustomerCountrycb.setValue(custCounry);
        CustomerStatecb.setValue(custProvince);
        CustomerAddresstf.setText(String.valueOf(currentCustomer.getAddress()));
        CustomerPostaltf.setText(String.valueOf(currentCustomer.getPostalcode()));
        CustomerPhonetf.setText(String.valueOf(currentCustomer.getPhone()));
        Countries jam = CustomerCountrycb.getValue();
        int cId = 0;
        for(Countries c:countListB){
            if(c.getCountryName().equals(customer.getCountry())){
                cId = c.getCountryId();     
                break;
            }
        }
        ObservableList<Provinces> provListB = DAOLists.getFilteredProvinces(cId);
        CustomerStatecb.setItems(provListB);
    }

    @FXML
    private void handleCustomerCountrycb(ActionEvent event) {
        Countries jam = CustomerCountrycb.getValue();
        int cId = jam.getCountryId();
        System.out.println(jam);
        System.out.println(cId);
        ObservableList<Provinces> provListB = DAOLists.getFilteredProvinces(cId);
        System.out.println(provListB);
        CustomerStatecb.setItems(provListB);
    }

    @FXML
    private void handleCustomerStatecb(ActionEvent event) {
    }




    
}
