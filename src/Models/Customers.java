/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *@class Customers
 * Constructors, Setters and Getters For Class Customers
 * @param customerId
 * @param customerName
 * @param phone
 * @param address
 * @param postalCode
 * @param divisionId
 *@author sean thompson <stho292@wgu.edu>
 */
public class Customers {

    private int customerId; //Auto incremented in database
    private String customerName;
    private String phone;
    private String address;
    private String postalCode;
    private int divisionId;
    private int countryId;
    private String country;
    private String state;


    //constructors
    public Customers(int customerID, String customerName, String address,String postalCode, String phone,int divisionId ) {
        setCustomerID(customerID);
        setCustomerName(customerName);
        this.address = address;
        this.postalCode = postalCode;
        setCustomerPhone(phone);
        this.divisionId = divisionId;
    }
    
        public Customers(int customerID, String customerName, String address,String postalCode, String phone,String country, String state ) {
        setCustomerID(customerID);
        setCustomerName(customerName);
        this.address = address;
        setCustomerPostalCode(postalCode);
        setCustomerPhone(phone);
        this.state = state;
        this.country = country;
        this.divisionId = divisionId;
        this.countryId = countryId;
    }

    public Customers() {
        
    }

    public Customers(int id, String cName) {
      setCustomerID(id);
      setCustomerName(cName);
    }
    
    //getters
    public int getCustomerID() {
        return customerId;
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

    public int getDivisionId() {
        return divisionId;
    }
    
    public String getCountry(){
        return country;
    }
    public int getCountryId(){
        return countryId;
    }
    
    public String getState(){
        return state;
    }

    //setters
    
    public void setCustomerID(int customerId) {

        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCustomerPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCustomerPhone(String phone) {
        this.phone = phone;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    
    public void setCountry(String country){
        this.country = country;
    }
    
    public void setCountry(int countryId){
        this.countryId = countryId;
    }
    public void setState(String state){
        this.state = state;
    }
    
        @Override
    public String toString(){
        return customerName;
    }
}
