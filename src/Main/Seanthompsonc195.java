/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Helper.JDBC;
import static Helper.JDBC.closeConnection;
import static Helper.JDBC.openConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class Seanthompsonc195 extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
    
    openConnection();
    launch(args);
    closeConnection();
    
 
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        // for some reason when i put the "Login.fxml under the "scenes" package I get an error
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Scenes/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
}
