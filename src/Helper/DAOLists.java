/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import static Helper.JDBC.getConnection;
import Models.Appointments;
import Models.Countries;
import Models.Customers;
import Models.Provinces;
import Models.User;
import java.sql.*;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class DAOLists {

 /**
  *
  */
   public static ObservableList<Customers> getAllCustomers(){
       //create a list to return
       ObservableList<Customers> cusListA = FXCollections.observableArrayList();
       //setup the sql
       String sql = "SELECT *, first_level_divisions.Division, countries.Country FROM customers "
               + "INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID "
               + "INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID ";
       try{
        //make the prepared statement
       PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
       //make the query ==> ResultSet
       ResultSet rs = ps.executeQuery();
       //Cycle through the result
       while(rs.next()){
           //pull out the data
           int id = rs.getInt("Customer_ID");
           String cName = rs.getString("Customer_Name");
           String cAddress = rs.getString("Address");
           String cPCode = rs.getString("Postal_Code");
           String phone = rs.getString("Phone");
           String country = rs.getString("Country");
           String state = rs.getString("Division");
           
           //make an object instance
           Customers cust = new Customers(id, cName, cAddress, cPCode, phone, country, state);
           
           //add to list
           cusListA.add(cust);
       }
            
       
       }
      catch(SQLException throwables){
          throwables.printStackTrace();
      }
      
       //return the list
       return cusListA;
       
   } 
   
   public static ObservableList<Countries> getAllCountries(){
       //create a list to return
       ObservableList<Countries> countryListA = FXCollections.observableArrayList();
       //setup the sql
       String sql = "SELECT * From countries";
       try{
       //make the prepared statement
       PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
       //make the query ==> ResultSet
       ResultSet rs = ps.executeQuery();
       //Cycle through the result
       while(rs.next()){
            //pull out the data
            int countryId = rs.getInt("Country_ID");
            String country = rs.getString("Country");
            //make an object instance
            Countries count = new Countries(countryId, country);
            //add to list
            countryListA.add(count);
       }
       }
       catch(SQLException throwables){
          throwables.printStackTrace();
       }
       //return the list
       return countryListA;
}

   
   public static ObservableList<Provinces> getFilteredProvinces(int cId){
       //create a list to return
       ObservableList<Provinces> provListA = FXCollections.observableArrayList();
       //setup the sql
       String sql = "SELECT * From first_level_divisions WHERE Country_ID =?";
       try{
       //make the prepared statement
       PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
       ps.setInt(1, cId);
       //make the query ==> ResultSet
       ResultSet rs = ps.executeQuery();
       //Cycle through the result
       while(rs.next()){
            //pull out the data
            int divID = rs.getInt("Division_ID");
            String divName = rs.getString("Division");
            int countryID = rs.getInt("Country_ID");
            //make an object instance
            Provinces province = new Provinces(divID, divName, countryID);
            //add to list
            provListA.add(province); 
       }
       }
       catch(SQLException throwables){
       throwables.printStackTrace();
       }
       //return the list
       return provListA;
   }
 
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
            int customerId = rs.getInt("User_ID");
            int userId = rs.getInt("Customer_ID");
            //Timestamps to Local Date Time
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            //make an object instance
            Appointments appoint = new Appointments(appointmentID, title, type, description, location, contactId, customerId, userId, start, end);
            //add to list
            appointListA.add(appoint); 
       }
       }
       catch(SQLException throwables){
       throwables.printStackTrace();
       }
       //return the list
       return appointListA;
   }
      
      
      
        public static ObservableList<User> getAllUsers(){
        //create a list to return
        ObservableList<User> userListA = FXCollections.observableArrayList();
       //setup the sql
       String sql = "SELECT * FROM users";
       try{
       //make the prepared statement
       PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
       //make the query ==> ResultSet
       ResultSet rs = ps.executeQuery();
       System.out.println(rs);
       //Cycle through the result
       while(rs.next()){
            //pull out the data
            int userId = rs.getInt("User_ID");
            String userPassword = rs.getString("Password");
            String userName = rs.getString("User_Name");
            //make an object instance
            User user = new User(userId, userPassword, userName);
            //add to list
            userListA.add(user); 
       }
       }
       catch(SQLException throwables){
       throwables.printStackTrace();
       }
       //return the list
       return userListA;
   }
       
   }

