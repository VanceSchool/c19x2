/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;


import static Helper.Alerts.exitAlert;
import static Helper.Alerts.passwordAlert;
import Helper.DAOLists;
import Models.User;
import Helper.JDBC;
import static Helper.JDBC.connection;
import Models.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.sql.ResultSet;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.collections.ObservableList;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *FXML Controller class
 * 
 *@author sean thompson <stho292@wgu.edu>
 */
public class LoginController implements Initializable {

    @FXML
    private TextField LoginUsernametf;
    @FXML
    private TextField LoginPasswordtf;
    @FXML
    private Button Loginbt;
    @FXML
    private RadioButton LoginEnglishrb;
    @FXML
    private ToggleGroup LoginLanguage;
    @FXML
    private RadioButton LoginFrenchrb;
    private Text LoginDateTimetxt;
    @FXML
    private Button LoginExitbt;
    private int userID;
    @FXML
    private Text ZoneIdtxt;
    @FXML
    private Text LoginDatetxt;
    @FXML
    private Text LoginTimetxt;
    @FXML
    private Text loginHeaderlb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginUsernametf.setText(null);
        LoginPasswordtf.setText(null);
        resourceBundleChange();
        setZoneIdField();
    }    

    @FXML
    private void handleLoginbt(ActionEvent event) throws IOException, SQLException {
        String username = LoginUsernametf.getText();
        String password = LoginPasswordtf.getText();
        // general login button
        //if((username != null) && (password != null)){
        
        // Password Check when login button us pressed.
        if(isPasswordGood(setUserInformation(username), password)){
        loginRecordSuccess(setUpUserInfo(setUserInformation(username), password, username));
        appointmentAlert();
        // load widget hierarchy of next screen
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Main.fxml"));
        //Get a stage
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //create the new scene
        Scene scene = new Scene(root);
        //set title 
        stage.setTitle("Main Menu");
        //set the scene on the the stage
        stage.setScene(scene);
        // "raise the curtain" Show the scene
        stage.show();
        }else{
        loginRecordfailure(setUpUserInfo(setUserInformation(username), password, username));
        passwordAlert();
                }
    }

    @FXML
    private void handleEnglish(ActionEvent event) {
    }

    @FXML
    private void handleFrench(ActionEvent event) {
    }

    @FXML
    private void handleExitbt(ActionEvent event) {
    exitAlert();
    }
    
    /*
    Set ZoneID txt to system Default Zone ID
    Set Login Date Text Field to Local Login Date
    Create Date Time Formatter
    Set Login Time text to local time using Date Time Formatter
    */
    private void setZoneIdField(){
        ZoneIdtxt.setText(ZoneId.systemDefault().toString());
        LoginDatetxt.setText(LocalDate.now().toString());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LoginTimetxt.setText(LocalTime.now().format(dtf).toString());
    }
    /* Set userID
    *create userID, set to -1
    *Create Statement Object
    *Create SQL Statement
    *designate result set, designate that executed statement is result set.
    *while going through result set, if text matches provided info then return the new user ID
    */  
   public int setUserInformation(String username) throws SQLException{
        int userID = -1;
        Statement st = (Statement) JDBC.connection.createStatement();
        String sqlStatement = "SELECT User_ID FROM users WHERE User_Name ='" + username + "'";
        ResultSet rs;
        rs = st.executeQuery(sqlStatement);
        while (rs.next()) {
            userID = rs.getInt("User_Id");
        }
        System.out.println(userID);
        return userID;
    }
    /*Validate Password:
    *Connect to database
    *Create SQL Statement
    *designate result set
    *designate that executed statement is result set.
    *while going through result set, if text matches provided info then return true
    */
    private boolean isPasswordGood (int userID, String password) throws SQLException{
        Statement st = (Statement) JDBC.connection.createStatement();
        String sql = "SELECT Password FROM users WHERE User_ID ='" + userID + "' ";
        ResultSet rs;
        rs = st.executeQuery(sql);
        while(rs.next()){
        String pass = rs.getString("Password");
        System.out.println(pass);
        return true;
        }
        return false;
    }
    
    private void appointmentAlert(){
        System.out.println("Appointment Alert");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }
    
    private User setUpUserInfo(int userID, String password, String username) throws SQLException{
        User user = new User(-1, password, username);
        user.setUserId(setUserInformation(username));
        user.setUserName(username);
        user.setUserPassword(password);
        return user;
    }
    /* Login Record Update
   
    */
    private void loginRecordSuccess(User user) throws IOException{
    System.out.println("Login Record Updated Positive"); 
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(
                    new File("login_activity.txt.txt"),
                    true /* append = true */));
            //out.txt will appear in the project's root directory under NetBeans projects
            //Note that Notepad will not display the following lines on separate lines
            pw.append("Succesful Login Attempt - User_ID: '" + user.getUserId() + "', Username:'"+ user.getUserName() +"' Password'"+ user.getUserPassword() +"'"
                    + "Time: '"+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "'\n");
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    /* Login Record Update
    
    */
    private void loginRecordfailure(User user){
        System.out.println("Login Record Updated Negative");
        //Logger log = Logger.getLogger("login_activity.txt");
                try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(
                    new File("login_activity.txt.txt"),
                    true /* append = true */));
            //out.txt will appear in the project's root directory under NetBeans projects
            //Note that Notepad will not display the following lines on separate lines
            pw.append("Unsuccesful Login Attempt - User_ID: '" + user.getUserId() + "', Username:'"+ user.getUserName() +"' Password'"+ user.getUserPassword() +"'"
                    + "Time: '"+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "'\n");
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void resourceBundleChange(){
        System.out.println(Locale.getDefault());
        try {
            ResourceBundle rb = ResourceBundle.getBundle("Language.lang", Locale.getDefault());
            ZoneIdtxt.setText(rb.getString("timezone"));
            LoginDatetxt.setText(rb.getString("date"));
            LoginTimetxt.setText(rb.getString("timezone"));
            loginHeaderlb.setText(rb.getString("title"));
            LoginExitbt.setText(rb.getString("exit"));
            LoginUsernametf.setPromptText(rb.getString("username"));
            LoginPasswordtf.setPromptText(rb.getString("password"));
            
            System.out.println("rb Change Successful");
        } catch (MissingResourceException e) {
            System.out.println("Missing resource");
    }
}
}
