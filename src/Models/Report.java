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
    
    
    /**
     * Blank Constructor
     */
    public Report(){

    }
    /**
     * Constructors for Report Showing Month and Type with total
     */
    public Report(String type, String month, int total){
        this.type = type;
        this.month = month;
        this.total = total;
    }
    
    /**
     * Constructors for Report Showing Month, Year, User, Total
     */
    public Report(LocalDate year, String month, String user, int total){
        this.year = year;
        this.month = month;
        this.user = user;
        this.total = total;
    }
   
    //Getters
    public String getType(){
        return type;
    }
    
    public String getMonth(){
        return month;
    }
    
    public int getTotal(){
        return total;
    }
    
    public String getUser(){
        return user;
    }
    
    public LocalDate getYear(){
        return year;
    }
    //Setters
    
    public void setType(String type){
        this.type = type;
    }
    
    public void setMonth(String month){
        this.type = type;
    }
    
    public void setTotal(int total){
        this.total = total;
    }
    
    public void setUser(String user){
        this.user = user;
    }
    
    public void setYear(LocalDate year){
        this.year = year;
    }
}
