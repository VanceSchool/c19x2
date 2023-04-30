/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/** Class containing setters, getters, and constructors for customers.
 *. 
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

    /** Constructors for class customers. 
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
    
    /** Constructors for class customers.
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

    /** Constructors for class customers.
     *
     */
    public Customers() {
        
    }

    /** Constructors for class customers.
     *
     * @param id
     * @param cName
     */
    public Customers(int id, String cName) {
      setCustomerID(id);
      setCustomerName(cName);
    }
    
    //getters

    /** Getter for customer ID.
     *
     * @return customerId
     */
    public int getCustomerID() {
        return customerId;
    }

    /** Getter for customer name.
     *
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /** Getter for customer address.
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /** Getter for postal code.
     *
     * @return postalCode
     */
    public String getPostalcode() {
        return postalCode;
    }

    /** Getter for phone. 
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /** Getter for division ID.
     *
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }
    
    /** Getter for country name.
     *
     * @return country
     */
    public String getCountry(){
        return country;
    }

    /** Getter for country ID.
     *
     * @return countryId
     */
    public int getCountryId(){
        return countryId;
    }
    
    /** Getter for state or province name.
     *
     * @return state
     */
    public String getState(){
        return state;
    }

    //setters

    /** Setter for customer ID.
     *
     * @param customerId
     */
    
    public void setCustomerID(int customerId) {

        this.customerId = customerId;
    }

    /** Setter for customer name.
     *
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /** Setter for address.
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /** setter for postal code.
     *
     * @param postalCode
     */
    public void setCustomerPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /** Setter for phone.
     *
     * @param phone
     */
    public void setCustomerPhone(String phone) {
        this.phone = phone;
    }

    /** Setter for division ID AKA province ID.
     *
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    
    /** Setter for country name.
     *
     * @param country
     */
    public void setCountry(String country){
        this.country = country;
    }
    
    /** Setter for country ID.
     *
     * @param countryId
     */
    public void setCountry(int countryId){
        this.countryId = countryId;
    }

    /** Setter for state aka province name.
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
