/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDate;

/** This class contains parameters for unique class of report. 
 * This class was created to be used in unique report creation.
 * 
 * @author sean thompson <stho292@wgu.edu>
 */

public class Report {
    String type;
    String month;
    int total;
    String user;
    LocalDate year;
    String country;
    String region;
    
    
    
    /**
     * Blank Constructor
     */
    public Report(){

    }
    /**
     * Constructors for Report Showing Month and Type with total
     * @param type
     * @param total
     * @param month
     */
    public Report(String type, String month, int total){
        this.type = type;
        this.month = month;
        this.total = total;
    }
    
    /**
     * Constructors for Report Showing Month, Year, User, Total
     * @param year
     * @param user
     * @param month
     * @param total
     */
    public Report(LocalDate year, String month, String user, int total){
        this.year = year;
        this.month = month;
        this.user = user;
        this.total = total;
    }
    
    /**
    * Constructors for Report Showing Country, Region, Total
    * @param country
    * @param region
    * @param total
    */
    public Report(int total, String country, String region){
        this.country = country;
        this.region = region;
        this.total = total;
    }
    
   
    //Getters

    /** Getter for country name. 
     *
     *@return country
     */
    
    public String getCountry(){
        return country;
    }
    
    /** Getter for region A.K.A provinces.
     *
     * @return region
     */
    public String getRegion(){
        return region;
    }
        
    /** Getter for type.
     * Type comes from appointments.
     * @return type
     */
    public String getType(){
        return type;
    }
    
    /** Getter for month.
     *
     * @return month
     */
    public String getMonth(){
        return month;
    }
    
    /** Getter for total.
     * Total is used as an integer value that represents sum.
     * @return
     */
    public int getTotal(){
        return total;
    }
    
    /** Getter used for user, which is user name.
     *
     * @return user
     */
    public String getUser(){
        return user;
    }
    
    /** Getter for year.
     *
     * @return year
     */
    public LocalDate getYear(){
        return year;
    }
    //Setters
    
    /** Setter for type.
     *
     * @param type
     */
    public void setType(String type){
        this.type = type;
    }
    
    /** Setter for Month. 
     *
     * @param month
     */
    public void setMonth(String month){
        this.type = type;
    }
    
    /** Setter for total.
     *
     * @param total
     */
    public void setTotal(int total){
        this.total = total;
    }
    
    /** Setter for user.
     *
     * @param user
     */
    public void setUser(String user){
        this.user = user;
    }
    
    /** Setter for year.
     *
     * @param year
     */
    public void setYear(LocalDate year){
        this.year = year;
    }
    
    /** Setter for country.
     *
     * @param country
     */
    public void setCountry(String country){
        this.country = country;
    }
    
    /** Setter for region AKA provinces.
     *
     * @param region
     */
    public void setRegion(String region){
        this.region = region;
    }
    
}
