/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class Contacts {
    int contactId;
    String contactName;
    String email;

    /**
     *
     * @param contactId
     * @param contactName
     */
    public Contacts(int contactId, String contactName) {
        this.contactId = contactId;
        this.contactName = contactName;
    }
    
    /**
     *
     * @param contactId
     * @param contactName
     * @param email
     */
    public Contacts(int contactId, String contactName, String email){
        contactId = this.contactId;
        contactName = this.contactName;
        email = this.email;
    }
    
    //Getters

    /**
     *
     * @return
     */
    public int getContactId(){
        return contactId;
    }
    
    /**
     *
     * @return
     */
    public String getContactName(){
        return contactName;
    }
    
    /**
     *
     * @return
     */
    public String getEmail(){
        return email;
    }
    
    //Setters

    /**
     *
     */
    public void setContactId(){
      this.contactId = contactId;
    }
    
    /**
     *
     */
    public void setContactName(){
      this.contactName = contactName;
    }
    
    /**
     *
     */
    public void setEmail(){
       this.email = email;
    }
    
    @Override
    public String toString(){
        return contactName;
    }
    
}
