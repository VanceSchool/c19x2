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
     
}
