/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import static Helper.JDBC.getConnection;
import Models.Appointments;
import Models.Contacts;
import Models.Countries;
import Models.Customers;
import Models.Provinces;
import Models.User;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

     /**
     * @class DAOLists
     * Contains methods to SELECT MySQL Database info into ObservableLists,
     * Affects tables customers, appointments, countries, first_level_divisions, users
     * Contains general(obtain All) and filtered(obtain specific set) queries
     * @author sean thompson <stho292@wgu.edu>
     */ 
public class DAOLists {
    
    /**
    * getAllCountries Method for SQL Statement for SELECT all FROM countries Table
    * @return countryListA
    */ 
    public static ObservableList<Countries> getAllCountries(){
        ObservableList<Countries> countryListA = FXCollections.observableArrayList();
        String sql = "SELECT * From countries";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int countryId = rs.getInt("Country_ID");
                String country = rs.getString("Country");
                Countries count = new Countries(countryId, country);
                countryListA.add(count);
            }
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return countryListA;
    }
   
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
    * getFilteredProvinces Method for SQL Statement for SELECT some FROM provinces Table
    * filtered by country.Country_ID
    * @return provListA
    * @param cId
    */ 
    public static ObservableList<Provinces> getFilteredProvinces(int cId){
        ObservableList<Provinces> provListA = FXCollections.observableArrayList();
        String sql = "SELECT * From first_level_divisions WHERE Country_ID =?";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, cId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int divID = rs.getInt("Division_ID");
                String divName = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                Provinces province = new Provinces(divID, divName, countryID);
                provListA.add(province); 
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }return provListA;
    }
 
    /**
    * getFilteredCustAppointments Method for SQL Statement for SELECT some FROM provinces Table
    * filtered by country.Country_ID
    * @return appointMonthListA
    * @param deleteCustID
    */ 
    public static ObservableList<Appointments> getFilteredCustAppointments(int deleteCustID){
        ObservableList<Appointments> appointCustListA = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Customer_ID = (SELECT Customer_ID FROM customers WHERE Customer_ID = ?);";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, deleteCustID);
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
}

