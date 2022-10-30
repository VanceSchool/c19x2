/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class Provinces {
    int divID;
    String divName;
    int countryID;

    public Provinces(String divName) {
        this.countryID = countryID;
        this.divName = divName;
        this.divID = divID;
    }
    
    @Override
    public String toString(){
        return(divName);
    }
    
    public Provinces(int divID, String divName, int countryID){
        this.countryID = countryID;
        this.divName = divName;
        this.divID = divID;
    }
    
    //Getters
    public int getdivID(){
        return divID;
    }
    
    public int getCountryID(){
        return countryID;
    }
    
    public String getDivName(){
        return divName;
    }
    
    //Setters
    public void setDivName(){
        this.divName = divName;
    }
    
        public void setCountryID(){
        this.countryID = countryID;
    }
        
            public void setdivID(){
        this.divID = divID;
    }
}
