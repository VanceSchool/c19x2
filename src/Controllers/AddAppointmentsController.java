/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Helper.DAOLists;
import Helper.UserfulMethods;
import static Helper.UserfulMethods.displayMinutes;
import Models.Contacts;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class AddAppointmentsController implements Initializable {

    @FXML
    private Button CustomerSavebt;
    @FXML
    private Button CustomerBackbt;
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
    private ComboBox<String> startTimedd;
    @FXML
    private ComboBox<String> endTimedd;
    @FXML
    private TextField locationtxt;
    @FXML
    private TextField typetxt;
    @FXML
    private TextField AppointmentIDtxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     addTime();
     addContacts();
        // TODO
        ObservableList<Integer> minuteList = displayMinutes();
        //startTimedd.setItems(minuteList);
        //endTimedd.setItems(minuteList);
    }    

    @FXML
    private void handleCustomerSavebt(ActionEvent event) {
    }

    @FXML
    private void handleContactdd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Appointments.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }
    
    private void addTime(){
        ObservableList<String> timeChoice = FXCollections.observableArrayList();
        int[] ti = IntStream.rangeClosed(1, 10).toArray();
        timeChoice.add(LocalTime.of(8, 0).format(DateTimeFormatter.ofPattern("h:mm")));

        
        
        
        
        
        
        
        
        
        
        
        
        
        startTimedd.setItems(timeChoice);
        endTimedd.setItems(timeChoice);
    }
    
        @FXML
    private void addContacts() {
        Contacts jam = contactdd.getValue();
        ObservableList<Contacts> contListB = DAOLists.getAllContacts();
        contactdd.setItems(contListB);
    }
}
