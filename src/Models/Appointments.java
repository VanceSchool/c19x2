/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    private LocalDateTime start;
    private LocalDateTime end;
          
    public Appointments(int appointmentID, String title, String type, String description, String location,int contactId, int customerId, int userId, LocalDateTime start, LocalDateTime end){
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
    
    public LocalDateTime getStart(){
        return start;
    }
    
    public LocalDateTime getEnd(){
        return end;
    }
    

    //Setters
    
    public void setAppointmentID(){
      this.appointmentID = appointmentID;
    }
    
    public void settTitle(){
       this.title = title;
    }
    
    public void setDescription(){
       this.description = description;
    }
    
    public void setLocation(){
       this.location = location;
    }
    
    public void setType(){
       this.type = type;
    }
    
    public void setCustomerId(){
        this.customerId = customerId;
    }
    
    public void setUserId(){
        this.userId = userId;
    }
    
    public void setContactId(){
       this.contactId = contactId;
    }
    
    public void setStart(){
        this.start = start;
    }
    
    public void setEnd(){
        this.end = end;
    }
    
}
