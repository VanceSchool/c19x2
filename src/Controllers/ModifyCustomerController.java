/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static Helper.Alerts.*;
import Helper.DAOLists;
import static Helper.DAOUpdateData.modifyCustomer;
import Helper.Time;
import static Helper.UserfulMethods.validateHasSelection;
import static Helper.UserfulMethods.validateNonEmpty;
import Models.Contacts;
import Models.Countries;
import Models.Customers;
import Models.Provinces;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
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
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 *FXML Controller class
 *@author sean thompson <stho292@wgu.edu>
 */
public class ModifyCustomerController implements Initializable {

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
    private TextField CustomerIdtf;
    private Customers currentCustomer;
    private Set<Customers> toBeModified = new HashSet();
    @FXML
    private ComboBox<Countries> CustomerCountrycb;
    @FXML
    private ComboBox<Provinces> CustomerStatecb;
    ObservableList<Countries> countListB = FXCollections.observableArrayList();
    @FXML
    private Label ModifyCustomerlb;
    @FXML
    private Label CustomerNamelb;
    @FXML
    private Label CustomerAddreslb;
    @FXML
    private Label CustomerNumberlb;
    @FXML
    private Label CustomerPostallb;
    @FXML
    private Label CustomerIDlb;
    @FXML
    private Label CustomerCountrylb;
    @FXML
    private Label CustomerStatelb;
    @FXML
    private TextField CustomerCitytf;
    @FXML
    private Label CustomerCitylb;
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
     //if (allAptTable.getSelectionModel().getSelectedItem() != null){
        alertGroup2(4);
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
       
       alertGroup1(5);
       Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Customer.fxml"));
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       Scene scene = new Scene(root);
       stage.setTitle("Customer Menu");
       stage.setScene(scene);
       stage.show();
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

    
    



    public void setCurrentCustomer(Customers customer) {
       
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
