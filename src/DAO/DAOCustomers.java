/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Controllers.LoginController;
import Helper.JDBC;
import Models.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Contains methods to SELECT, INSERT, DELETE and UPDATE MySQL Database info into ObservableLists,
* Contains methods to SELECT, INSERT, DELETE and UPDATE MySQL Database info into ObservableLists,
* Affects tables customers, countries, first_level_divisions
* @author sean thompson stho292@wgu.edu
*/ 
public class DAOCustomers {
    //SELECT Methods
    
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
        }return cusListA;
    }


    /**
    *getFilteredCustomers Method for SQL Statement for SELECT All FROM customers Table WHERE Customer_ID = custID
    * This Method returns an ObservableList
    *@return cusListB
    *@param custID
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
        }catch(SQLException throwables){
            throwables.printStackTrace();
        } return cusListB;
    }

    /**
    *Method for SQL Statement for SELECT All FROM customers Table WHERE Customer_ID = id
    * This method returns a Customers
     * @param id
    *@return cusListB
    */ 
    public static Customers findCustomeresByID(int id){
        String sql = "SELECT * From customers WHERE Customer_ID = ?";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int custID = rs.getInt("Customer_ID");
                String cName = rs.getString("Customer_Name");
                Customers count = new Customers(custID, cName);
                return count;
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }return null;
        
    }
    
    //INSERT, UPDATE, DELETE methods
    /**
    * modifyCustomer 
    * Method for SQL Statement for UPDATE customers Table
    * @param modCust
    * @throws SQLException
    */ 
    public static void modifyCustomer(Customers modCust) throws SQLException{
        String sql1 = "SELECT * FROM customers WHERE Customer_ID = ?";
        String sql2 = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = "
                + "(SELECT Division_ID FROM first_level_divisions WHERE Division = ?), Last_Update = NOW(), Last_Updated_By = ?"
                + "WHERE Customer_ID = ?";
        try {
        PreparedStatement ps1 = JDBC.getConnection().prepareStatement(sql1);
        PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql2);
        ps1.setInt(1, modCust.getCustomerID());
        ResultSet rs1 = ps1.executeQuery();
        //System.out.println(modCust.getState());
        while(rs1.next()){
            ps2.setString(1, modCust.getCustomerName());
            ps2.setString(2, modCust.getAddress());
            ps2.setString(3, modCust.getPostalcode());
            ps2.setString(4, modCust.getPhone());
            ps2.setString(5,modCust.getState() );
            ps2.setString(6, LoginController.meUserID);
            ps2.setInt(7, modCust.getCustomerID());
            ps2.executeUpdate();
            
        }
        }catch(SQLException e) {
            System.out.println("Issue with SQL");
            e.printStackTrace();
            
        }
    }
            
    /**
    *deleteCustomer 
    * Method for SQL Statement for DELETE customers Table
    * @param delCust
    * @throws SQLException
    */
    public static void deleteCustomer(Customers delCust) throws SQLException{
        String sql1, sql2;
        sql1 = "DELETE appointments.* FROM appointments WHERE Customer_ID = ?";
        sql2 = "DELETE customers.* FROM customers WHERE Customer_ID = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql1);
            ps.setInt(1, delCust.getCustomerID());
            ps.execute();
            ps = JDBC.getConnection().prepareStatement(sql2);
            ps.setInt(1, delCust.getCustomerID());
            ps.execute();
        } catch(SQLException e) {
            System.out.println("Issue with SQL");
            e.printStackTrace();
        }
}
    
     /**
     * addNewCustomers 
     * Method for SQL Statement for INSERT INTO customers Table
     * @param newCust
     * @throws SQLException
     */ 
        public static void addNewCustomers(Customers newCust) throws SQLException{ 
        String sql2 = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date,"
                + "Created_By, Last_Update, Last_Updated_By, Division_ID) "
                + "VALUES(?, ?, ?, ?, NOW(), ?, NOW(), ?, "
                + "(SELECT Division_ID FROM first_level_divisions WHERE Division = ?))";
        //(SELECT User_Name FROM users WHERE User_ID = ?)
        try {
       System.out.println(LoginController.meUserID);
        PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql2);
            ps2.setString(1, newCust.getCustomerName());
            ps2.setString(2, newCust.getAddress());
            ps2.setString(3, newCust.getPostalcode());
            ps2.setString(4, newCust.getPhone());
            ps2.setString(5, LoginController.meUserID);
            ps2.setString(6, LoginController.meUserID);
            ps2.setString(7, newCust.getState());
            //System.out.println(sql2);
            ps2.executeUpdate();
             } catch(SQLException e) {
            System.out.println("Issue with SQL");
            e.printStackTrace();
        }
        }
       
}