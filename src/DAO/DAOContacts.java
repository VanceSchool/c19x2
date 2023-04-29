/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBC;
import Models.Contacts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
* @class DAOContacts
* Contains methods to SELECT, INSERT, DELETE and UPDATE MySQL Database 
* Affects tables contacts, countries, first_level_divisions
* @author sean thompson <stho292@wgu.edu>
*/ 
public class DAOContacts {
    //SELECT Methods
    
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
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }return contactsListA;
    }
    
    /**
    *findContactByID Method for SQL Statement for SELECT all FROM 
    *contacts Table WHERE Contact_ID = id contacts Table
    *@param id
    *@return count
    */
    public static Contacts findContactByID(int id){
        String sql = "SELECT * From contacts WHERE Contact_ID = ?";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                Contacts count = new Contacts(contactId, contactName);
                return count;
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }return null;
        
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
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return contactsListB;
    }
    
    //INSERT, UPDATE, DELETE methods
    //NONE Currently
}
