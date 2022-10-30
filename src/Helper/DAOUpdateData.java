/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Models.Appointments;
import Models.Customers;
import java.sql.PreparedStatement;
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
        public static void modifyCustomer(Customers modCust){
            
        }
        
        
        public static void modifyAppointment(Appointments modAppoint){
            String sql;
            sql ="";
        }
}
