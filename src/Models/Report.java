/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDate;

/**
 *
 * @author LabUser
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

    /**
     *
     * @return
     */
    
    public String getCountry(){
        return country;
    }
    
    /**
     *
     * @return
     */
    public String getRegion(){
        return region;
    }
        
    /**
     *
     * @return
     */
    public String getType(){
        return type;
    }
    
    /**
     *
     * @return
     */
    public String getMonth(){
        return month;
    }
    
    /**
     *
     * @return
     */
    public int getTotal(){
        return total;
    }
    
    /**
     *
     * @return
     */
    public String getUser(){
        return user;
    }
    
    /**
     *
     * @return
     */
    public LocalDate getYear(){
        return year;
    }
    //Setters
    
    /**
     *
     * @param type
     */
    public void setType(String type){
        this.type = type;
    }
    
    /**
     *
     * @param month
     */
    public void setMonth(String month){
        this.type = type;
    }
    
    /**
     *
     * @param total
     */
    public void setTotal(int total){
        this.total = total;
    }
    
    /**
     *
     * @param user
     */
    public void setUser(String user){
        this.user = user;
    }
    
    /**
     *
     * @param year
     */
    public void setYear(LocalDate year){
        this.year = year;
    }
    
    /**
     *
     * @param country
     */
    public void setCountry(String country){
        this.country = country;
    }
    
    /**
     *
     * @param region
     */
    public void setRegion(String region){
        this.region = region;
    }
    
}
