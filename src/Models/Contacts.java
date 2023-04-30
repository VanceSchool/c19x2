/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


/** This class contains setters, getters and constructors for contacts. 
 *
 *@author sean thompson stho292@wgu.edu
 */
public class Contacts {
    int contactId;
    String contactName;
    String email;

    /** Constructor for contacts.
     *
     * @param contactId
     * @param contactName
     */
    public Contacts(int contactId, String contactName) {
        this.contactId = contactId;
        this.contactName = contactName;
    }
    
    /** Constructor for contacts. 
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

    /** Getter for contact ID.
     *
     * @return contactId
     */
    public int getContactId(){
        return contactId;
    }
    
    /** Getter for contact name.
     *
     * @return contactName
     */
    public String getContactName(){
        return contactName;
    }
    
    /** Getter for email.
     *
     * @return email
     */
    public String getEmail(){
        return email;
    }
    
    //Setters

    /** Setter for contact ID.
     *
     */
    public void setContactId(){
      this.contactId = contactId;
    }
    
    /** Setter for contact name.
     *
     */
    public void setContactName(){
      this.contactName = contactName;
    }
    
    /** Setter for email.
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
