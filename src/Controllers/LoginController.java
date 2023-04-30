/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;


import static DAO.DAOAppointments.getAllAppointments;
import static Helper.Alerts.*;
import DAO.DAOLists;
import Models.User;
import Helper.JDBC;
import static Helper.TimeMethods.*;
import Main.Seanthompsonc195;
import Models.Appointments;
import Models.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.sql.ResultSet;
import java.net.URL;
import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.*;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**Class containing controls for login form.
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
    private Button LoginExitbt;
    @FXML
    public static String meUserID;
    @FXML
    private Text ZoneIdtxt;
    @FXML
    private Text LoginDatetxt;
    @FXML
    private Text LoginTimetxt;
    @FXML
    private Text loginHeaderlb;
    @FXML
    private Label LoginDateTimelb;
    @FXML
    private Label ZoneIdlb;
    ObservableList<Appointments> currentAppointments = FXCollections.observableArrayList();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    /**
     *
     */
    public User meUser;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginUsernametf.setText(null);
        LoginPasswordtf.setText(null);
        resourceBundleChange();
        setZoneIdField();  
    }    

    /**Method that handles when login button is press.
     * Currently calls on methods in class to check password and username.
     * Also starts method to create login attempt record.
     * Lambda function present was used to bypass making new method by streaming appointments to new list.
     * If list has any appointments an alert is created.
     * The lambda checks each value against presets of current time and time plus 15 minutes.
     * If an  appointment is within those two times it is collected into a new List.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void handleLoginbt(ActionEvent event) throws IOException, SQLException {
        String username = LoginUsernametf.getText();
        String password = LoginPasswordtf.getText();
        // Password Check when login button us pressed.
        if(isPasswordGood(setUserInformation(username), password)){
            loginRecordSuccess(setUpUserInfo(setUserInformation(username), password, username));
            meUserID = username;
            //Appointment Alert if appoint within 15 minutes of login time
            List<Appointments> reminderAppointments = new ArrayList<>(currentAppointments);
            ArrayList <Appointments> myAppointments = new ArrayList();
            for(Appointments a:getAllAppointments()){
                myAppointments.add(a);
            };
            java.sql.Timestamp nowPlusFifteen = java.sql.Timestamp.valueOf(LocalDateTime.now().plus(15, ChronoUnit.MINUTES));
            java.sql.Timestamp rightNow = java.sql.Timestamp.valueOf(LocalDateTime.now());
            //lambda expression used to efficiently identify any appointment starting within the next 15 minutes and add to list
            reminderAppointments = myAppointments.stream().filter(row -> (
                (row.getStart().after(rightNow) || row.getStart().equals(rightNow)) && row.getStart().before(nowPlusFifteen)
                )
            ).collect(Collectors.toList());
            if (reminderAppointments.isEmpty()) {
                System.out.println("No upcoming appointment alerts.");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You have No Upcoming Appointments");
                alert.setHeaderText("You have No appointments scheduled within 15 minutes");
                alert.setContentText("You have No appointments scheduled within 15 minutes");
                alert.showAndWait();
            }else{
                int appointment = reminderAppointments.get(0).getAppointmentID();
                String start = reminderAppointments.get(0).getStart().toString();
                DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("MM/dd/yy 'at' hh:mm a");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You have Upcoming Appointments");
                alert.setHeaderText("You have an appointment scheduled in 15 min");
                StringBuilder alertSB = new StringBuilder();
                for(Appointments apl:reminderAppointments){
                int appointID = apl.getAppointmentID();
                String Output = fomatter.format(apl.getStart().toLocalDateTime());
                    alert.setContentText("AppointmentID:" + appointID
                        + " starts at " + Output + ".\n");
                    alert.showAndWait();
                }
            }
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
    
    /**Method handles exit button action.
     * 
     * @param event 
     */
    @FXML
    private void handleExitbt(ActionEvent event) {
        exitAlert();
    }
    
    /** Method that sets ZoneID text to system Default Zone ID
    *Set Login Date Text Field to Local Login Date
    *Create Date Time Formatter
    *Set Login Time text to local time using Date Time Formatter
    */
    private void setZoneIdField(){
        ZoneIdtxt.setText(ZoneId.systemDefault().toString());
        LoginDatetxt.setText(LocalDate.now().toString());
        LoginTimetxt.setText(LocalTime.now().format(dtf).toString());
    }
    
    /**setUserInformation
    * create userID, set to -1
    * Create Statement Object
    * Create SQL Statement
    * designate result set, designate that executed statement is result set.while going through result set, if text matches provided info then return the new user ID
    *@param username
     * @return 
     * @throws java.sql.SQLException
    */  
    public int setUserInformation(String username) throws SQLException{
    int meUserID = -1;
    Statement st = (Statement) JDBC.connection.createStatement();
    String sqlStatement = "SELECT User_ID FROM users WHERE User_Name ='" + username + "'";
    ResultSet rs;
    rs = st.executeQuery(sqlStatement);
        while (rs.next()) {
        meUserID = rs.getInt("User_Id");
        }
        //System.out.println(meUserID);
        return meUserID;
    }
   
    /**isPasswordGood
    *Connect to database
    *Create SQL Statement
    *designate result set
    *designate that executed statement is result set.
    *while going through result set, if text matches provided info then return true
    * @param userID
    * @param password
    */
    private boolean isPasswordGood (int userID, String password) throws SQLException{
    //String sql = "SELECT Password FROM users WHERE User_ID ='" + userID + "' ";
    String sql ="SELECT * FROM users WHERE users.User_ID = '" + userID + "' AND users.Password = '" + password + "'";
    PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
    ResultSet rs = ps.executeQuery(sql);
        while(rs.next()){
        String pass = rs.getString("Password");
        //System.out.println(pass);
        return true;
        }return false;
    }
    
    /*
    private void appointmentAlert(){   
        //System.out.println("Appointment Alert");
        List<Appointments> reminderAppointments = new ArrayList<>(currentAppointments);
        ArrayList <Appointments> myAppointments = new ArrayList();
        for(Appointments a:getAllAppointments()){
             myAppointments.add(a);
        };
        java.sql.Timestamp nowPlusFifteen = java.sql.Timestamp.valueOf(LocalDateTime.now().plus(15, ChronoUnit.MINUTES));
        java.sql.Timestamp rightNow = java.sql.Timestamp.valueOf(LocalDateTime.now());
        //lambda expression used to efficiently identify any appointment starting within the next 15 minutes and add to list
        reminderAppointments = myAppointments.stream().filter(row -> (
            (row.getStart().after(rightNow) || row.getStart().equals(rightNow)) && row.getStart().before(nowPlusFifteen)
            )
        ).collect(Collectors.toList());
        //System.out.println(nowPlusFifteen);
        //System.out.println(rightNow);
        //System.out.println(reminderAppointments);
        //for(Appointments a:getAllAppointments()){
        //    System.out.println(a.getStart());
        //}
        if (reminderAppointments.isEmpty()) {
            System.out.println("No upcoming appointment alerts.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You have No Upcoming Appointments");
            alert.setHeaderText("You have No appointments scheduled within 15 minutes");
            alert.setContentText("You have No appointments scheduled within 15 minutes");
            alert.showAndWait();
        } else {
            int appointment = reminderAppointments.get(0).getAppointmentID();
            String start = reminderAppointments.get(0).getStart().toString(); //1
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You have Upcoming Appointments");
            alert.setHeaderText("You have an appointment scheduled in 15 min");
            alert.setContentText("AppointmentID:" + appointment
                    + " starts at " + start + ".");
            alert.showAndWait();
        }
    }
    */

    /** Method that creates a user object with given info from login page.
     * @param userID
     * @param password
     * @param username
     * @return user
     * @throws SQLException
     */
    public User setUpUserInfo(int userID, String password, String username) throws SQLException{
        User user = new User(-1, password, username);
        user.setUserId(setUserInformation(username));
        user.setUserName(username);
        user.setUserPassword(password);
        return user;
    }
    
    /**Method that updates login_activity.txt or creates it if it doesn't
     * exist.
     * This adds records off successful attempts.
    *
    * @param user
    */
    private void loginRecordSuccess(User user) throws IOException{
    //System.out.println("Login Record Updated Positive"); 
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(
                new File("login_activity.txt"),
                true));
            //out.txt will appear in the project's root directory under NetBeans projects
            //Note that Notepad will not display the following lines on separate lines
            pw.append("Succesful Login Attempt - User_ID: '" + user.getUserId() + "', Username:'"+ user.getUserName() +"' Password'"+ user.getUserPassword() +"'"
                    + "Time: '"+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "' Date: '"+ LocalDate.now() + "'\n");
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    /**Method that updates login_activity.txt or creates it if it doesn't
     * exist.
     * This adds records off failed attempts.
    *
    * @param user
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
                    + "Time: '"+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "' Date: '"+ LocalDate.now() + "'\n");
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**Method that handles changing on screen information based on resource bundle selected.
     * 
     */
    private void resourceBundleChange(){
        System.out.println(Locale.getDefault());
        try {
            ResourceBundle rb = ResourceBundle.getBundle("Language.lang", Locale.getDefault());
            ZoneIdlb.setText(rb.getString("timezone"));
            LoginDateTimelb.setText(rb.getString("date"));
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
