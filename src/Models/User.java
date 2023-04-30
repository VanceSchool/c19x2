/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.security.Timestamp;

/** This Class Contains User Information.
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class User{
    private static int userId;
    private static String userPassword;
    private static String userName;
    

    /**Constructor for User
     * @param i
     * @param string
     * @param string1
    */ 
    public User(int userId, String userPassword, String userName){
        userId = this.userId;
        userPassword = this.userPassword;
        userName = this.userName;
    }

    /**Constructor for User.
     * @param i
     * @param string
     * 
     */ 
    public User(int userId, String userPassword) {
       userId = this.userId;
       userPassword = this.userPassword;
    }
    
    /**Constructor for User.
     * @param string
     * @param i
     */ 
    public User(String userName, int userId) {
       userId = this.userId;
       userName = this.userName;
    }

    /**
     *
     */
    public User() {
        
    }

//Setters

    /** Setter for User ID.
     *
     * @param userId
     */
    public void setUserId(int userId){
        this.userId = userId;
    }

    /** Setter for user password.
     *
     * @param userPassword
     */
    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }

    /** setter for user name.
     *
     * @param String
     */
    public void setUserName(String userName){
        this.userName = userName;
    }
//Getters

    /** Getter for user ID.
     *
     * @return userId
     */
    public int getUserId(){
        return userId;
    }

    /**
     *
     * @return userName
     */
    public String getUserName(){
        return userName;
    }

    /**
     *
     * @return userPassword
     */
    public String getUserPassword(){
        return userPassword;
    }

        @Override
    public String toString(){
        return userName;
    }
}