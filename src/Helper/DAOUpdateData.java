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
        Timestamp timmy = changeToTimeStamp(getNowLocalDateTime());
        String sql1 = "SELECT * FROM customers WHERE Customer_ID = ?";
        String sql3 = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";
        String sql2 = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = "
                + "(SELECT Division_ID FROM first_level_divisions WHERE Division = ?), Last_Update = ?, Last_Updated_By = ?"
                + "WHERE Customer_ID = ?";
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
            ps2.setTimestamp(6, timmy);
            ps2.setString(7, LoginController.meUserID);
            ps2.setInt(7, modCust.getCustomerID());
            ps2.executeUpdate();
            
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
        }
     
        
        
        
        
        
        
        
        
     /**
     * addAppointment
     * @param modAppoint
     * @throws SQLException
     */
        public static void addAppointment(Appointments appointToAdd){
            String sql ="INSERT INTO appointments \n" +
"(Title, Description, Location, Type, Start, END, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)\n" +
"VALUES ( ? ,? ,? , ? , ? ,? , current_date(), 'vance', current_date(), 'vance', '1', '1', '3'); ";
            System.out.println(sql);
       }
     /**
     * modifyAppointment
     * @param modAppoint
     * @throws SQLException
     */
        public static void modifyAppointment(Appointments modAppoint){
            String sql;
            sql ="";
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
