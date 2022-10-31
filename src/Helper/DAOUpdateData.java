/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import static Helper.Alerts.alertGroup;
import Models.Appointments;
import Models.Customers;
import Models.Provinces;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author LabUser
 */
public class DAOUpdateData {

    public DAOUpdateData() {
    }
    
    /**
     *
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
     *
     * @param delAppoint
     * @throws SQLException
     */
        public static void deleteAppointment(Appointments delAppoint) throws SQLException{
        String sql;
        sql = "DELETE appointments.* FROM appointments WHERE Customer_ID = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, delAppoint.getAppointmentID());
            ps.execute();
        } catch(SQLException e) {
            System.out.println("Issue with SQL");
            e.printStackTrace();
        }
}
        public static void modifyCustomer(Customers modCust) throws SQLException{
        String sql1 = "SELECT * FROM customers WHERE Customer_ID = ?";
        String sql3 = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";
        String sql2 = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = "
                + "(SELECT Division_ID FROM first_level_divisions WHERE Division = ?)"
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
            ps2.setInt(6, modCust.getCustomerID());
            ps2.executeUpdate();
            
        }
        }
        
        
        public static void modifyAppointment(Appointments modAppoint){
            String sql;
            sql ="";
        }
}
