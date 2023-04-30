/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import java.security.Timestamp;

/** This class contains setters, getters and constructors for countries.
 *
 *@author sean thompson stho292@wgu.edu
 */
public class Countries{
    int countryId;
    String countryName;
    
    
    @Override
    public String toString(){
        return(countryName);
    }

    /** Constructor for country.
     *
     * @param countryId
     * @param countryName
     */

    public Countries(int countryId, String countryName){
        this.countryId = countryId;
        this.countryName = countryName;
    }
    
    /** Constructor for country.
     *
     * @param countryName
     */
    public Countries(String countryName){
        this.countryName = countryName;
    }
    
    
    //Getters

    /** Getter for country ID.
     *
     * @return countryId
     */
    
    public int getCountryId(){
        return countryId;
    }
    
    /** Getter for country name.
     *
     * @return countryName
     */
    public String getCountryName(){
        return countryName;
    }
    //Setters
    
    /** Setter for country ID.
     *
     */
    public void setCountryId(){
        this.countryId = countryId;
    }
    
    /** Setter for country name.
     *
     */
    public void setCountryName(){
        this.countryName = countryName;
    }
}
