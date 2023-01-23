/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import java.security.Timestamp;

/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class Level extends Update {
    int levelId;
    String division;
    
    
    public Level(int levelId, String division,String lastUpdateBy, String createdBy, Timestamp createdTimeDate, Timestamp lastUpdatedTimeDate){
        super(lastUpdateBy, createdBy, createdTimeDate, lastUpdatedTimeDate);
        levelId = this.levelId;
        division = this.division;
    }
    
    //Getters
    public int getLevelID(){
        return levelId;
    }
    
    public String getDivision(){
        return division;
    }
    //Setters
    public void setLevelId(){
        this.levelId = levelId;
    }
    
    public void setDivision(){
        this.division = division;
    }
}
