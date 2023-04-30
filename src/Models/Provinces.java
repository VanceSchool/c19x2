/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/** This class contains the constructors, setters and getters for first level divisions.
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class Provinces {
    int divID;
    String divName;
    int countryID;

    /** This is a constructor for Provinces. 
     *
     * @param divName
     */
    public Provinces(String divName) {
        this.countryID = countryID;
        this.divName = divName;
        this.divID = divID;
    }
    
    @Override
    public String toString(){
        return(divName);
    }
    
    /** This is a constructor for provinces. 
     *
     * @param divID
     * @param divName
     * @param countryID
     */
    public Provinces(int divID, String divName, int countryID){
        this.countryID = countryID;
        this.divName = divName;
        this.divID = divID;
    }
    
    //Getters

    /** This method is a getter for provinces ID.
     *
     * @return divID
     */
    public int getdivID(){
        return divID;
    }
    
    /** This method is a getter for country ID.
     *
     * @return countryID
     */
    public int getCountryID(){
        return countryID;
    }
    
    /** This method is a getter for province name. 
     *
     * @return divName
     */
    public String getDivName(){
        return divName;
    }
    
    //Setters

    /** This method is a setter for province name. 
     *
     */
    public void setDivName(){
        this.divName = divName;
    }
    
    /** This method is a setter for province country ID. 
     *
     */
    public void setCountryID(){
        this.countryID = countryID;
    }
        
    /** This method is a setter for province ID.
     *
     */
    public void setdivID(){
        this.divID = divID;
    }
}
