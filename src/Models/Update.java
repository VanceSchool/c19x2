/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.security.Timestamp;
import java.util.Date;

/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class Update {
  private  static String lastUpdateBy;
  private  static String createdBy;
  private  static Timestamp createdTimeDate;
  private  static Timestamp lastUpdatedTimeDate;
    
    /**
     *
     * @param lastUpdateBy
     * @param createdBy
     * @param createdTimeDate
     * @param lastUpdatedTimeDate
     */
    public Update(String lastUpdateBy, String createdBy, Timestamp createdTimeDate,Timestamp lastUpdatedTimeDate ){
lastUpdateBy = this.lastUpdateBy;
createdBy = this.createdBy;
createdTimeDate = this.createdTimeDate;
lastUpdatedTimeDate = this.lastUpdatedTimeDate;

}
    
    //Getters

    /**
     *
     * @return
     */
    public Timestamp getLastUpdate(){
        return lastUpdatedTimeDate;
    }
    
    /**
     *
     * @return
     */
    public Timestamp getCreated(){
        return createdTimeDate;
    }
    
    /**
     *
     * @return
     */
    public String getLastUpdateBy(){
        return lastUpdateBy;
    }
    
    /**
     *
     * @return
     */
    public String getCreatedBy(){
        return createdBy;
    }
    //Setters

    /**
     *
     */
     public void setLastUpdate(){
        lastUpdatedTimeDate = this.lastUpdatedTimeDate;
    }
    
    /**
     *
     */
    public void setCreated(){
        
        createdTimeDate = this.createdTimeDate;
    }
    
    /**
     *
     */
    public void setLastUpdateBy(){
        
        lastUpdateBy = this.lastUpdateBy;
    }
    
    /**
     *
     */
    public void setCreatedBy(){
        createdBy = this.createdBy;
    }
    
}
