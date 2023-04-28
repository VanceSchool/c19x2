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
     * getAllCustomers Method for SQL Statement for SELECT all FROM customers Table
     * @return cusListA
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
   
   /**
     * getFilteredCustomers Method for SQL Statement for SELECT All FROM customers Table WHERE Customer_ID = custID
     * @return cusListB
     * @param custID
     */ 
   public static ObservableList<Customers> getFilteredCustomers(int custID){
       ObservableList<Customers> cusListB = FXCollections.observableArrayList();
       String sql = "SELECT Customer_ID, Customer_Name FROM customers WHERE Customer_ID = ? ";
 
       try{
       PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
       ps.setInt(1, custID);
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
           int id = rs.getInt("Customer_ID");
           String cName = rs.getString("Customer_Name");
           Customers cust = new Customers(id, cName);
           cusListB.add(cust);
       }
       } catch(SQLException throwables){
          throwables.printStackTrace();
      } return cusListB;
}
     
   
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
     * getAllContacts Method for SQL Statement for SELECT all FROM contacts Table
     * @return contactsListA
     */ 
   public static ObservableList<Contacts> getAllContacts(){
       ObservableList<Contacts> contactsListA = FXCollections.observableArrayList();
       String sql = "SELECT * From contacts";
       try{
       PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            Contacts count = new Contacts(contactId, contactName);
            contactsListA.add(count);
       }
       }
       catch(SQLException throwables){
          throwables.printStackTrace();
       } return contactsListA;
}
   
   
   
     /**
     * getFilteredContacts Method for SQL Statement for SELECT all FROM contacts Table WHERE Contact_ID = cID
     * @return countryListA
     * @param cID
     */ 
   public static ObservableList<Contacts> getFilteredContacts(int cID){
       ObservableList<Contacts> contactsListB = FXCollections.observableArrayList();
       String sql = "SELECT * From contacts WHERE Contact_ID = ?";
       try{
       PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
       ps.setInt(1, cID);
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            Contacts count = new Contacts(contactId, contactName);
            contactsListB.add(count);
       }
       } catch(SQLException throwables){
          throwables.printStackTrace();
       }
       return contactsListB;
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
            int customerId = rs.getInt("User_ID");
            int userId = rs.getInt("Customer_ID");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            //make an object instance
            //System.out.println("Appointment ID Equials " + appointmentID);
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
       } return provListA;
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
            int customerId = rs.getInt("User_ID");
            int userId = rs.getInt("Customer_ID");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            Appointments appoint = new Appointments(appointmentID, title, type, description, location, contactId, customerId, userId, start, end);
            appointCustListA.add(appoint); 
       }
       }
       catch(SQLException throwables){
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
            int customerId = rs.getInt("User_ID");
            int userId = rs.getInt("Customer_ID");
            //Timestamps to Local Date Time
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            //make an object instance
            System.out.println("Appointment ID Equials " + appointmentID);
            Appointments appoint = new Appointments(appointmentID, title, type, description, location, contactId, customerId, userId, start, end);
            //add to list
            appointMonthListA.add(appoint); 
       }
       }
       catch(SQLException throwables){
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
            //Timestamps to Local Date Time
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            //make an object instance
            //System.out.println("Appointment ID Equials " + appointmentID);
            Appointments appoint = new Appointments(appointmentID, title, type, description, location, contactId, customerId, userId, start, end);
            //add to list
            appointWeekListA.add(appoint); 
       }
       }
       catch(SQLException throwables){
       throwables.printStackTrace();
       }
       //return the list
       return appointWeekListA;
       
   }
   }

