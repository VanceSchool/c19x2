/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import java.sql.Timestamp;

/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class Appointments{
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private int customerId;
    private int userId;
    private int contactId;
    private Timestamp start;
    private Timestamp end;
    private String customerName;
    private String contactName;
    
          
    public Appointments(int appointmentID, String title, String type, String description, String location,int contactId, int customerId, int userId, Timestamp start, Timestamp end){
    this.appointmentID =appointmentID;
    this.title = title;
    this.description = description;
    this.location = location;
    this.type = type;
    this.customerId = customerId;
    this.userId = userId; 
    this.contactId = contactId;
    this.start = start;
    this.end = end;
    
    }
    
        public Appointments(int appointmentID, String title, String type, String description, String location,String contactName, String customerName, Timestamp start, Timestamp end){
    this.appointmentID =appointmentID;
    this.title = title;
    this.description = description;
    this.location = location;
    this.type = type;
    this.customerName = customerName;
    this.contactName = contactName;
    this.start = start;
    this.end = end;
    
    }

    public Appointments() {
    
    }

    
    
    //Getters
    
    public int getAppointmentID(){
        return appointmentID;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getDescription(){
        return description;
    }
    public String getLocation(){
        return location;
    }
    
    public String getType(){
        return type;
    }
    
    public int getCustomerId(){
        return customerId;
    }
    
    public int getUserId(){
       return userId;
    }
    
    public int getContactId(){
        return contactId;
    }
    
    public Timestamp getStart(){
        return start;
    }
    
    public Timestamp getEnd(){
        return end;
    }
    
     public String getContactName(){
        return contactName;
    }
     
    public String getCustomerName(){
        return customerName;
    }
    

    //Setters
    
    public void setAppointmentID(int id){
      this.appointmentID = appointmentID;
    }
    
    public void setTitle(String title){
       this.title = title;
    }
    
    public void setDescription(String description){
       this.description = description;
    }
    
    public void setLocation(String location){
       this.location = location;
    }
    
    public void setType(String type){
       this.type = type;
    }
    
    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }
    
    public void setUserId(int userId){
        this.userId = userId;
    }
    
    public void setContactId(int contactId){
       this.contactId = contactId;
    }
    
    public void setStart(Timestamp start){
        this.start = start;
    }
    
    public void setEnd(Timestamp end){
        this.end = end;
    }

    public void setContactName(String contactName){
       this.type = type;
    }
    public void setCustomerName(String customerName){
       this.type = type;
    }
}
