/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Controllers.LoginController;
import static Controllers.LoginController.meUserID;
import static Helper.Alerts.*;
import static Helper.TimeMethods.*;
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
