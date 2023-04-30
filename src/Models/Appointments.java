/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import java.sql.Timestamp;

/** This class contains setters, getters, and constructors for appointments.
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
    
    /** Constructor for appointment.
     *
     * @param appointmentID
     * @param title
     * @param type
     * @param description
     * @param location
     * @param contactId
     * @param customerId
     * @param userId
     * @param start
     * @param end
     */
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
    
    /**Constructor for appointment.
     *
     * @param appointmentID
     * @param title
     * @param type
     * @param description
     * @param location
     * @param contactName
     * @param customerName
     * @param start
     * @param end
     */
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
        
    /** Constructor for appointment.
     *
     */
    public Appointments() {
    
    }

    public Appointments(int appointmentID) {
        this.appointmentID =appointmentID;
    }

    
    
    //Getters

    /** Getter for appointment ID.
     *
     * @return appointmentID
     */
    
    public int getAppointmentID(){
        return appointmentID;
    }
    
    /**
     *
     * @return title
     */
    public String getTitle(){
        return title;
    }
    
    /**
     *
     * @return description
     */
    public String getDescription(){
        return description;
    }

    /**
     *
     * @return location
     */
    public String getLocation(){
        return location;
    }
    
    /**
     *
     * @return type
     */
    public String getType(){
        return type;
    }
    
    /**
     *
     * @return customerId
     */
    public int getCustomerId(){
        return customerId;
    }
    
    /**
     *
     * @return userId
     */
    public int getUserId(){
       return userId;
    }
    
    /**
     *
     * @return contactId
     */
    public int getContactId(){
        return contactId;
    }
    
    /**
     *
     * @return start
     */
    public Timestamp getStart(){
        return start;
    }
    
    /**
     *
     * @return end
     */
    public Timestamp getEnd(){
        return end;
    }
    
    /**
     *
     * @return contactName
     */
    public String getContactName(){
        return contactName;
    }
     
    /**
     *
     * @return customerName
     */
    public String getCustomerName(){
        return customerName;
    }
    

    //Setters

    /**
     *
     * @param appointmentID
     */
    
    public void setAppointmentID(int appointmentID){
      this.appointmentID = appointmentID;
    }
    
    /**
     *
     * @param title
     */
    public void setTitle(String title){
       this.title = title;
    }
    
    /**
     *
     * @param description
     */
    public void setDescription(String description){
       this.description = description;
    }
    
    /**
     *
     * @param location
     */
    public void setLocation(String location){
       this.location = location;
    }
    
    /**
     *
     * @param type
     */
    public void setType(String type){
       this.type = type;
    }
    
    /**
     *
     * @param customerId
     */
    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }
    
    /**
     *
     * @param userId
     */
    public void setUserId(int userId){
        this.userId = userId;
    }
    
    /**
     *
     * @param contactId
     */
    public void setContactId(int contactId){
       this.contactId = contactId;
    }
    
    /**
     *
     * @param start
     */
    public void setStart(Timestamp start){
        this.start = start;
    }
    
    /**
     *
     * @param end
     */
    public void setEnd(Timestamp end){
        this.end = end;
    }

    /**
     *
     * @param contactName
     */
    public void setContactName(String contactName){
       this.type = type;
    }

    /**
     *
     * @param customerName
     */
    public void setCustomerName(String customerName){
       this.type = type;
    }

    /**
     *
     * @param value
     */
    public void setUserName(User value) {
        
    }
}
