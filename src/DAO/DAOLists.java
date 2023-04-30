/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBC;
import static Helper.JDBC.getConnection;
import Models.Appointments;
import Models.Contacts;
import Models.Countries;
import Models.Customers;
import Models.Provinces;
import Models.Report;
import Models.User;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

     /** Class contains methods to SELECT MySQL Database info into ObservableLists,
     * 
     * Contains methods to SELECT MySQL Database info into ObservableLists.
     * Affects tables countries and first_level_divisions.
     * Contains general(obtain All) and filtered(obtain specific set) queries.
     * @author sean thompson stho292@wgu.edu
     */ 
public class DAOLists {
    
    /** Method for SQL Statement for SELECT all FROM countries Table.
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
   
    
    /** Method for SQL Statement for SELECT some FROM provinces Table filtered by country.Country_ID.
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
      
    
    
    /** Method for SQL Statement for SELECT some FROM appointments Table filtered by Type, MONTH(appointments.Start).
    * @return reportA
    * 
    */ 
    public static ObservableList<Report> getAssignmentReportTypeMonth(){
        ObservableList<Report> reportA = FXCollections.observableArrayList();
        String sql = "SELECT Type, MONTHNAME(appointments.Start) AS Month, count(*) AS Total\n" +
                "FROM appointments\n" +
                "GROUP BY Type, MONTH(appointments.Start)";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String month = rs.getString("Month");
                String type = rs.getString("Type");
                int total = rs.getInt("Total");
                Report rep = new Report(type, month, total);
                reportA.add(rep); 
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }return reportA;
    }

    
    /** Method for SQL Statement for SELECT some FROM Customers Table
    * firs_level_divisions table and countries Table, Then gives count of each region customers.
    * @return reportC
    * 
    */ 
    public static ObservableList<Report> getCustomersPerRegion(){
        ObservableList<Report> reportC = FXCollections.observableArrayList();
        String sql = "SELECT first_level_divisions.Division, countries.Country, count(*) AS Total FROM customers "
                + "JOIN first_level_divisions ON first_level_divisions.Division_ID = customers.Division_ID "
                + "JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID "
                + "GROUP BY first_level_divisions.Division, countries.Country;";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String country = rs.getString("Country");
                String region = rs.getString("Division");
                int total = rs.getInt("Total");
                Report rep = new Report(total, country, region );
                reportC.add(rep); 
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }return reportC;
    }
}

