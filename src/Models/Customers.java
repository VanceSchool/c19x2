/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class Customers {

    private int customerID; //Auto incremented in database
    private String customerName;
    private String phone;
    private String address;
    private String country;
    private String state;
    private String postalCode;
    //private Date createDate;
    //private String createdBy;
    //private Date lastUpdate;
    //private String lastUpdateBy;

    //constructors
    public Customers(int customerID, String customerName, String address,String postalCode, String phone, String country, Date lastUpdate, String lastUpdateBy) {
        setCustomerID(customerID);
        setCustomerName(customerName);
        setCustomerAddress(address);
        setCustomerPostalCode(postalCode);
        setCustomerPhone(phone);
        setCustomerCountry(country);
       // setCustomerLastUpdate(lastUpdate);
       //setCustomerLastUpdateBy(lastUpdateBy);

    }
    public Customers(int customerID, String customerName, String address,String postalCode, String phone, String country, String state) {
        setCustomerID(customerID);
        setCustomerName(customerName);
        setCustomerAddress(address);
        setCustomerPostalCode(postalCode);
        setCustomerPhone(phone);
        setCustomerCountry(country);
        setCustomerState(state);

    }
    

    //getters
    
    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalcode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }
    public String getState() {
        return state;
    }
/*
    public Date getCustomerLastUpdate() {
        return lastUpdate;
    }

    public String getCustomerLastUpdateBy() {
        return lastUpdateBy;
    }
*/
    //setters
    
    public void setCustomerID(int customerID) {

        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String address) {
        this.address = address;
    }

    public void setCustomerPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCustomerPhone(String phone) {
        this.phone = phone;
    }

    public void setCustomerCountry(String country) {
        this.country = country;
    }
    
    public void setCustomerState(String state) {
        this.state = state;
    }
/*
    public void setCustomerLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setCustomerLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
*/
}
