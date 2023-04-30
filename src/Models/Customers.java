/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *@class Customers
 * Constructors, Setters and Getters For Class Customers
 * @param customerName
 * @param address
 * @param postalCode
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

    /**
     *
     * @param customerID
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     */
    public Customers(int customerID, String customerName, String address,String postalCode, String phone,int divisionId ) {
        setCustomerID(customerID);
        setCustomerName(customerName);
        this.address = address;
        this.postalCode = postalCode;
        setCustomerPhone(phone);
        this.divisionId = divisionId;
    }
    
    /**
     *
     * @param customerID
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param country
     * @param state
     */
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

    /**
     *
     */
    public Customers() {
        
    }

    /**
     *
     * @param id
     * @param cName
     */
    public Customers(int id, String cName) {
      setCustomerID(id);
      setCustomerName(cName);
    }
    
    //getters

    /**
     *
     * @return
     */
    public int getCustomerID() {
        return customerId;
    }

    /**
     *
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return
     */
    public String getPostalcode() {
        return postalCode;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @return
     */
    public int getDivisionId() {
        return divisionId;
    }
    
    /**
     *
     * @return
     */
    public String getCountry(){
        return country;
    }

    /**
     *
     * @return
     */
    public int getCountryId(){
        return countryId;
    }
    
    /**
     *
     * @return
     */
    public String getState(){
        return state;
    }

    //setters

    /**
     *
     * @param customerId
     */
    
    public void setCustomerID(int customerId) {

        this.customerId = customerId;
    }

    /**
     *
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @param postalCode
     */
    public void setCustomerPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @param phone
     */
    public void setCustomerPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    
    /**
     *
     * @param country
     */
    public void setCountry(String country){
        this.country = country;
    }
    
    /**
     *
     * @param countryId
     */
    public void setCountry(int countryId){
        this.countryId = countryId;
    }

    /**
     *
     * @param state
     */
    public void setState(String state){
        this.state = state;
    }
    
        @Override
    public String toString(){
        return customerName;
    }
}
