/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.security.Timestamp;

/** This Class Contains User Information.
 *
 *@author sean thompson stho292@wgu.edu
 */
public class User{
    private static int userId;
    private static String userPassword;
    private static String userName;
    

    /**Constructor for User. 
     * @param userId
     * @param userPassword
     * @param userName
    */ 
    public User(int userId, String userPassword, String userName){
        userId = this.userId;
        userPassword = this.userPassword;
        userName = this.userName;
    }

    /**Constructor for User.
     * @param userId
     * @param userPassword
     * 
     */ 
    public User(int userId, String userPassword) {
       userId = this.userId;
       userPassword = this.userPassword;
    }
    
    /**Constructor for User.
     * @param userName
     * @param userId
     */ 
    public User(String userName, int userId) {
       userId = this.userId;
       userName = this.userName;
    }

    /** Empty constructor for User. 
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
     * @param userName - Name of user.
     */
    public void setUserName(String userName){
        this.userName = userName;
    }
//Getters

    /** Getter for user ID.
     *
     * @return {int} userId -- Id of User.
     */
    public int getUserId(){
        return userId;
    }

    /** Getter for user name. 
     *
     * @return {String} userName -- Name of user
     */
    public String getUserName(){
        return userName;
    }

    /** Getter for user password.
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