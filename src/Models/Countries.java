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
public class Countries{
    int countryId;
    String countryName;
    
    
    @Override
    public String toString(){
        return(countryName);
    }
    
    /*
    public Countries(int countryId, String countryName,String lastUpdateBy, String createdBy, Timestamp createdTimeDate, Timestamp lastUpdatedTimeDate){
        super(lastUpdateBy, createdBy, createdTimeDate, lastUpdatedTimeDate);
        this.countryId = countryId;
        this.countryName = countryName;
    }
    */

    /**
     *
     * @param countryId
     * @param countryName
     */

    public Countries(int countryId, String countryName){
        this.countryId = countryId;
        this.countryName = countryName;
    }
    
    /**
     *
     * @param countryName
     */
    public Countries(String countryName){
        this.countryName = countryName;
    }
    
    
    //Getters

    /**
     *
     * @return
     */
    
    public int getCountryId(){
        return countryId;
    }
    
    /**
     *
     * @return
     */
    public String getCountryName(){
        return countryName;
    }
    //Setters
    
    /**
     *
     */
    public void setCountryId(){
        this.countryId = countryId;
    }
    
    /**
     *
     */
    public void setCountryName(){
        this.countryName = countryName;
    }
}
