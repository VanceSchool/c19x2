/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author LabUser
 */

public class Report {
    String type;
    String month;
    int total;
    
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
}
