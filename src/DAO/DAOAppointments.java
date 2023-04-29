/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Controllers.LoginController;
import Helper.JDBC;
import Models.Appointments;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @class DAOContacts
 * Contains methods to SELECT, INSERT, DELETE and UPDATE MySQL Database 
 * Affects tables Appointments
 * @author sean thompson <stho292@wgu.edu>
 */ 
public class DAOAppointments {
    //SELECT Methods
    /**
    * getAllAppointments Method for SQL Statement for SELECT all FROM appointments Table
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
            System.out.println(rs);
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
    
    /**
    * getFilteredMonthAppointments Method for SQL Statement for SELECT some FROM appointments Table
    * filtered by MONTH(current_date)
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
            System.out.println(rs);
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
                System.out.println("Appointment ID Equials " + appointmentID);
                Appointments appoint = new Appointments(appointmentID, title, type, description, location, contactId, customerId, userId, start, end);
                appointMonthListA.add(appoint); 
            }
            }catch(SQLException throwables){
                throwables.printStackTrace();
            }
        //return the list
        return appointMonthListA;  
    }
    
    /**
    * getFilteredWeekAppointments Method for SQL Statement for SELECT some FROM appointments Table
    * filtered by appointments.Start
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
            System.out.println(rs);
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
    
    /**
    * getFilteredCustAppointments Method for SQL Statement for SELECT some FROM provinces Table
    * filtered by country.Country_ID
    * @return appointMonthListA
    * @param deleteCustID
    */ 
    public static ObservableList<Appointments> getFilteredCustAppointments(int CustID){
        ObservableList<Appointments> appointCustListA = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Customer_ID = (SELECT Customer_ID FROM customers WHERE Customer_ID = ?);";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, CustID);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
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
    
    //INSERT, UPDATE, DELETE methods
    /**
    * addAppointment
    * Inserts new Appointment into appointments table
    * @param newAppoint
    * @throws SQLException
    */
        public static void addAppointment(Appointments appointToAdd) throws SQLException{
            String sql="INSERT INTO appointments \n" +
            "(Title, Description, Location, Type, Start, End, Create_Date, Created_By, \n"
            + "Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)\n" +
            "VALUES ( ? ,? ,? , ? , ? ,? , current_date(), ?, current_date(), ?, ?,\n"
            + " (SELECT User_ID FROM users WHERE User_Name = ?), ?); ";   
            //LocalDateTime startlds = changeToUST(appointToAdd.getStart());
            //LocalDateTime endslds = changeToUST(appointToAdd.getEnd());
            //Timestamp startts = Timestamp.valueOf(startlds);
            //Timestamp endts = Timestamp.valueOf(endslds);
         try {   
        PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql);
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
             } catch(SQLException e) {
            System.out.println("Issue with SQL");
            e.printStackTrace();
        }
       }
        
    /**
    * modifyAppointment
    * @param modAppoint
    * @throws SQLException
    */
    public static void modifyAppointment(Appointments modAppoint) throws SQLException{
        String sql1 = "SELECT * FROM appointments WHERE Appointment_ID = ?";
        String sql2="INSERT INTO appointments \n" +
        "(Title, Description, Location, Type, Start, End, Create_Date, Created_By, \n"
        + "Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)\n" +
        "VALUES ( ? ,? ,? , ? , ? ,? , current_date(), ?, current_date(), ?, ?,\n"
        + " (SELECT User_ID FROM users WHERE User_Name = ?), ?) WHERE Appointment_ID = ?; ";
        try {  //System.out.println(sql);
            PreparedStatement ps1 = JDBC.getConnection().prepareStatement(sql1);   
            //System.out.println(LoginController.meUserID);
            PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql2);
            ps2.setString(1, modAppoint.getTitle());
            ps2.setString(2, modAppoint.getDescription());
            ps2.setString(3, modAppoint.getLocation());
            ps2.setString(4, modAppoint.getType());
            ps2.setTimestamp(5, modAppoint.getStart());
            ps2.setTimestamp(6, modAppoint.getEnd());
            ps2.setString(7, LoginController.meUserID);
            ps2.setString(8, LoginController.meUserID);
            ps2.setInt(9, modAppoint.getCustomerId());
            ps2.setString(10, LoginController.meUserID);
            ps2.setInt(11, modAppoint.getContactId());
            System.out.println(ps2);
            ps2.executeUpdate();
            }catch(SQLException e) {
                System.out.println("Issue with SQL");
                e.printStackTrace();
            }
    }
        
        
    /**
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
        
    /**
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
