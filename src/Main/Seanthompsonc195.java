/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Helper.JDBC;
import static Helper.JDBC.closeConnection;
import static Helper.JDBC.openConnection;
import Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/** This class contains the main method that initializes at program start. 
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class Seanthompsonc195 extends Application{


    public User currentMe;

    /** This is main method that starts at beginning of program.
     * As this method occurs at beginning of program it is used to open and close database connection.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
    
    openConnection();
    launch(args);
    closeConnection();
    
  
        
    }

    /** This method starts FXML of program and loads first scene/view which is Login.
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        
    
        // for some reason when i put the "Login.fxml under the "scenes" package I get an error
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Scenes/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
}
