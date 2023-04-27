/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Controllers.LoginController;
import static Controllers.LoginController.meUserID;
import static Helper.Alerts.*;
import static Helper.Time.*;
import Models.Appointments;
import Models.Customers;
import Models.Provinces;
import Models.User;
import static java.lang.String.valueOf;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javafx.collections.ObservableList;


     /**
     * @class DAOUpdateData
     * Contains methods to INSERT INTO, UPDATE, DELETE MySQL Database info
     * Affects tables customers and appointments
     * @author sean thompson <stho292@wgu.edu>
     */ 
public class DAOUpdateData {
    
    public DAOUpdateData() {
    }
          
     /**
     * modifyCustomer Method for SQL Statement for UPDATE customers Table
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
        System.out.println(modCust.getState());
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
     *deleteCustomer Method for SQL Statement for DELETE customers Table
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
     * addNewCustomers Method for SQL Statement for INSERT INTO customers Table
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
     
        
        
        
        
        
        
        
        
     /**
     * addAppointment
     * @param newAppoint
     * @throws SQLException
     */
        public static void addAppointment(Appointments appointToAdd) throws SQLException{
            String sql ="UPDATE appointments SET\n" +
            "(Title, Description, Location, Type, Start, End, Create_Date, Created_By, \n"
            + "Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)\n" +
            "VALUES ( ? ,? ,? , ? , ? ,? , current_date(), ?, current_date(), ?, ?,\n"
            + " (SELECT User_ID FROM users WHERE User_Name = ?), ?); ";   
         try {   
        PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql);
            ps2.setString(1, appointToAdd.getTitle());
            ps2.setString(2, appointToAdd.getDescription());
            ps2.setString(3, appointToAdd.getLocation());
            ps2.setString(4, appointToAdd.getType());
            ps2.setString(5, appointToAdd.getStart().toString());
            ps2.setString(6, appointToAdd.getEnd().toString());
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
            ps2.setString(5, modAppoint.getStart().toString());
            ps2.setString(6, modAppoint.getEnd().toString());
            ps2.setString(7, LoginController.meUserID);
            ps2.setString(8, LoginController.meUserID);
            ps2.setInt(9, modAppoint.getCustomerId());
            ps2.setString(10, LoginController.meUserID);
            ps2.setInt(11, modAppoint.getContactId());
            System.out.println(ps2);
            ps2.executeUpdate();
             } catch(SQLException e) {
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
}
