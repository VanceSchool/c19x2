/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Controllers.LoginController;
import static Helper.Alerts.appointmentTimeAlerts;
import Helper.JDBC;
import Models.Appointments;
import java.sql.PreparedStatement;
import Helper.TimeMethods;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Class contains methods to SELECT, INSERT, DELETE and UPDATE MySQL Database, affects tables Appointments. 
 *  
 * @author sean thompson stho292@wgu.edu
 */ 
public class DAOAppointments {
    
    //SELECT Methods
    /** Method for SQL Statement for SELECT all FROM appointments Table.
    * 
    * @return appointListA
    */ 
    public static ObservableList<Appointments> getAllAppointments(){
        //create a list to return
        ObservableList<Appointments> appointListA = FXCollections.observableArrayList();
        //setup the sql
        String sql = "SELECT * FROM appointments";
        try{
            //make the prepared statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            //make the query ==> ResultSet
            ResultSet rs = ps.executeQuery();
            //System.out.println(rs);
            //Cycle through the result
            while(rs.next()){
                //pull out the data
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                //make an object instance
                //System.out.println("Appointment ID Equials " + appointmentID);
                Appointments appoint = new Appointments(appointmentID, title, type, description, location, contactId, customerId, userId, start, end);
                //add to list
                appointListA.add(appoint); 
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        //return the list
        return appointListA;   
    }
    
    /**Method for SQL Statement for SELECT some FROM appointments Table filtered by MONTH(current_date).
    * @return appointMonthListA
    */ 
    public static ObservableList<Appointments> getFilteredMonthAppointments(){
        //create a list to return
        ObservableList<Appointments> appointMonthListA = FXCollections.observableArrayList();
        //setup the sql
        String sql = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(current_date()) AND YEAR(Start) = YEAR(current_date());";
        try{
            //make the prepared statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            //make the query ==> ResultSet
            ResultSet rs = ps.executeQuery();
            //System.out.println(rs);
            //Cycle through the result
            while(rs.next()){
                //pull out the data
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                //System.out.println("Appointment ID Equials " + appointmentID);
                Appointments appoint = new Appointments(appointmentID, title, type, description, location, contactId, customerId, userId, start, end);
                appointMonthListA.add(appoint); 
            }
            }catch(SQLException throwables){
                throwables.printStackTrace();
            }
        //return the list
        return appointMonthListA;  
    }
    
    /** Method for SQL Statement for SELECT some FROM appointments Table filtered by appointments start.
    * 
    * 
    * @return appointWeekListA
    */ 
    public static ObservableList<Appointments> getFilteredWeekAppointments(){
        //create a list to return
        ObservableList<Appointments> appointWeekListA = FXCollections.observableArrayList();
        //setup the sql
        String sql = "SELECT * FROM appointments WHERE WEEK(Start) = WEEK(current_date()) AND YEAR(Start) = YEAR(current_date());";
        try{
            //make the prepared statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            //make the query ==> ResultSet
            ResultSet rs = ps.executeQuery();
            //System.out.println(rs);
            //Cycle through the result
            while(rs.next()){
                //pull out the data
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                int customerId = rs.getInt("User_ID");
                int userId = rs.getInt("Customer_ID");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Appointments appoint = new Appointments(appointmentID, title, type, description, location, contactId, customerId, userId, start, end);
                appointWeekListA.add(appoint); 
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        //return the list
        return appointWeekListA;
    }
    
    /** Method that checks that a new appointment does not have overlapping appointments.
     * Checks for any combination of times that can cause either start or end time to be within bounds of another appointment.
     * @param start
     * @param end
     * @return
     * @throws SQLException
     */
    public static Boolean checkingOverLapNew(Timestamp start, Timestamp end) throws SQLException{
        ObservableList<Appointments> BadAppointmennt = FXCollections.observableArrayList();
        String sq1 = "SELECT * FROM appointments;";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sq1);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if((start.before(rs.getTimestamp("Start")) && (end.after(rs.getTimestamp("Start"))))){
                     return true;
                }else if((start.after(rs.getTimestamp("Start")) && (start.before(rs.getTimestamp("End"))))){
                   return true;
                }else if((start.before(rs.getTimestamp("Start")) && (end.after(rs.getTimestamp("End"))))){
                    return true;
                }else if((start.after(rs.getTimestamp("Start")) && (end.before(rs.getTimestamp("End"))))){
                    return true;
                }else if(start.equals(rs.getTimestamp("Start"))){
                    return true;
                }else if(end.equals(rs.getTimestamp("End"))){
                    return true;
                }
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return false;
        
    }
    
    /** Method that checks that all appointments except the one being currently modified does not have overlap.
     *Checks for any combination of times that can cause either start or end time to be within bounds of another appointment.
     * @param start
     * @param end
     * @param id
     * @return
     * @throws SQLException
     */
    public static Boolean checkingOverLapExisting(Timestamp start, Timestamp end, int id) throws SQLException{
        ObservableList<Appointments> BadAppointmennt = FXCollections.observableArrayList();
        String sq1 = "SELECT * FROM appointments WHERE Appointment_ID <> ?;";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sq1);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if((start.before(rs.getTimestamp("Start")) && (end.after(rs.getTimestamp("Start"))))){
                     return true;
                }else if((start.after(rs.getTimestamp("Start")) && (start.before(rs.getTimestamp("End"))))){
                   return true;
                }else if((start.before(rs.getTimestamp("Start")) && (end.after(rs.getTimestamp("End"))))){
                    return true;
                }else if((start.after(rs.getTimestamp("Start")) && (end.before(rs.getTimestamp("End"))))){
                    return true;
                }else if(start.equals(rs.getTimestamp("Start"))){
                    return true;
                }else if(end.equals(rs.getTimestamp("End"))){
                    return true;
                }
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return false;
        
    }
    
    /** Method for SQL Statement for SELECT some FROM appointments Table filtered by customer ID.
    * @return appointCustListA
    * @param CustID
    */ 
    public static ObservableList<Appointments> getFilteredCustAppointments(int CustID){
        ObservableList<Appointments> appointCustListA = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Customer_ID = (SELECT Customer_ID FROM customers WHERE Customer_ID = ?);";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, CustID);
            ResultSet rs = ps.executeQuery();
            //System.out.println(rs);
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Appointments appoint = new Appointments(appointmentID, title, type, description, location, contactId, customerId, userId, start, end);
                appointCustListA.add(appoint); 
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        //return the list
        return appointCustListA;
    }
    
    /**Method for SQL Statement for SELECT all FROM appointments Table iltered by Contact_ID.
    * @return appointConListA
    * @param CName
    */ 
    public static ObservableList<Appointments> getFilteredContactsAppointments(String CName){
        ObservableList<Appointments> appointConListA = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments "
                + "WHERE Contact_ID = (SELECT Contact_ID FROm contacts WHERE Contact_Name = ?) "
                + "GROUP BY Start ORDER BY Start DESC;";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, CName);
            ResultSet rs = ps.executeQuery();
            //System.out.println(rs);
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Appointments appoint = new Appointments(appointmentID, title, type, description, location, contactId, customerId, userId, start, end);
                appointConListA.add(appoint); 
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        //return the list
        return appointConListA;
    }
    
    /** Method for SQL Statement for SELECT all FROM appointments 
    * Table WHERE The timestamp .is before current time. 
    * 
    * @return lapsedAppointList
    * 
    */ 
    public static ObservableList<Appointments> getLapsedAppointments(){
        ObservableList<Appointments> lapsedAppointList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Start < current_timestamp();";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //System.out.println(rs);
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Appointments appoint = new Appointments(appointmentID, title, type, description, location, contactId, customerId, userId, start, end);
                lapsedAppointList.add(appoint); 
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        //return the list
        return lapsedAppointList;
    }
    
    //INSERT, UPDATE, DELETE methods
    /** Method that Inserts new Appointment into appointments table. 
    * addAppointment
    * @param appointToAdd
    * @throws SQLException
    */
        public static void addAppointment(Appointments appointToAdd) throws SQLException{
            String sq2="INSERT INTO appointments \n" +
            "(Title, Description, Location, Type, Start, End, Create_Date, Created_By, \n"
            + "Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)\n" +
            "VALUES ( ? ,? ,? , ? , ? ,? , current_timestamp(), ?, current_timestamp(), ?, ?,\n"
            + " (SELECT User_ID FROM users WHERE User_Name = ?), ?); ";   

            try { 
                PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sq2);
                ps2.setString(1, appointToAdd.getTitle());
                ps2.setString(2, appointToAdd.getDescription());
                ps2.setString(3, appointToAdd.getLocation());
                ps2.setString(4, appointToAdd.getType());
                ps2.setTimestamp(5, appointToAdd.getStart());
                ps2.setTimestamp(6, appointToAdd.getEnd());
                ps2.setString(7, LoginController.meUserID);
                ps2.setString(8, LoginController.meUserID);
                ps2.setInt(9, appointToAdd.getCustomerId());
                ps2.setString(10, LoginController.meUserID);
                ps2.setInt(11, appointToAdd.getContactId());
                System.out.println(ps2);
                ps2.executeUpdate();
            
            }catch(SQLException e){
            System.out.println("Issue with SQL");
            e.printStackTrace();
        }
    }
        
    /** Method that updates an existing appointment.
    * modifyAppointment
    * @param modAppoint
    * @throws SQLException
    */
    public static void modifyAppointment(Appointments modAppoint) throws SQLException{
        //String sql1 = "SELECT * FROM customers WHERE Customer_ID = ?";
        String sql2="UPDATE appointments SET "
                + "Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = current_timestamp(), "
                + "Last_Updated_By = ?, Customer_ID = ?, User_ID = (SELECT User_ID FROM users WHERE User_Name = ?), Contact_ID = ? "
                + "WHERE Appointment_ID = ?; "; 
        try {
            //PreparedStatement ps1 = JDBC.getConnection().prepareStatement(sql1);
            PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql2);
            //ps1.setInt(1, modAppoint.getAppointmentID());
            
            //ResultSet rs1 = ps1.executeQuery();
            //System.out.println(rs1);
                ps2.setString(1, modAppoint.getTitle());
                ps2.setString(2, modAppoint.getDescription());
                ps2.setString(3, modAppoint.getLocation());
                ps2.setString(4, modAppoint.getType());
                ps2.setTimestamp(5, modAppoint.getStart());
                ps2.setTimestamp(6, modAppoint.getEnd());
                ps2.setString(7, LoginController.meUserID);
                ps2.setInt(8, modAppoint.getCustomerId());
                ps2.setString(9, LoginController.meUserID);
                ps2.setInt(10, modAppoint.getContactId());
                ps2.setInt(11, modAppoint.getAppointmentID());
                System.out.println(ps2);
                ps2.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Issue with SQL");
            e.printStackTrace();
        }
    }
        
        
    /** Method that deletes an existing appointment.
    * deleteAppointment 
    * @param delAppoint
    * @throws SQLException
    */
    public static void deleteAppointment(Appointments delAppoint) throws SQLException{
        String sql = "DELETE appointments.* FROM appointments WHERE Appointment_ID =?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, delAppoint.getAppointmentID());
            ps.execute();
        } catch(SQLException e) {
            System.out.println("Issue with SQL");
            e.printStackTrace();
        }
    }
        
    /** Method that deletes an existing appointment, filtered by Customer_ID.
    * deleteCustomerAppoint 
    * @param custId
    * @throws SQLException
    */
    public static void deleteCustomerAppoint(int custId) throws SQLException{
        String sql = "DELETE appointments.* FROM appointments WHERE Customer_ID =?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, custId);
            ps.execute();
        }catch(SQLException e) {
            System.out.println("Issue with SQL");
            e.printStackTrace();
        }
    }
}
