/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.sql.Connection;
import java.sql.DriverManager;

/** Class contains database connection methods.
 *
 *@author sean thompson stho292@wgu.edu
 */
public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password

    /** 
     *
     */
    public static Connection connection = null;  // Connection Interface

    /** Method to connect to database.
     * displays message if connection successful.
     * @return connection
     */
    public static Connection openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
        return connection;
    }
    
    /**
     *
     * @return
     */
    public static Connection getConnection(){
            return connection;
        }
    
    /** Method to close connection to database.
     *
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.exit(0);
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
           // System.out.println("Error:" + e.getMessage());
           // e.printStackTrace();
            //do nothing
        }
    }
}
