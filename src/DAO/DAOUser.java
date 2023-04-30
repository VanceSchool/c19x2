/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBC;
import Models.Customers;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Class containing Methods to obtain User info from MySQL Database.
 *
 * @author LabUser
 */
public class DAOUser {
    
    /** Method to get all users from MySQL database.
     *
     * @return userListtA
     */
    public static ObservableList<User> getAllUsers(){
        //create a list to return
        ObservableList<User> userListtA = FXCollections.observableArrayList();
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
                int id = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                //make an object instance
                User user = new User(userName, id);
                //add to list
                userListtA.add(user);
                }    
            }    
        catch(SQLException throwables){
            throwables.printStackTrace();
        }return userListtA;
    }
}
