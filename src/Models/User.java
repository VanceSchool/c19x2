/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.security.Timestamp;

/**
 *
 *@author sean thompson <stho292@wgu.edu>
 */
public class User{
    private static int userId;
    private static String userPassword;
    private static String userName;
    

/**Constructor User
 * @param userName id of user
 * @param userPassword of user
 * @param userId of user
 * This Constructor used to validate Login Attempts
 */ 
public User(int userId, String userPassword, String userName){
userId = this.userId;
userPassword = this.userPassword;
userName = this.userName;
}

    public User(int userId, String userPassword) {
       userId = this.userId;
        userPassword = this.userPassword;
    }

//Setters
public void setUserId(int userId){
    this.userId = userId;
}

public void setUserPassword(String userPassword){
    this.userPassword = userPassword;
}

public void setUserName(String userName){
    this.userName = userName;
}
//Getters

public int getUserId(){
    return userId;
}

public String getUserName(){
    return userName;
}

public String getUserPassword(){
    return userPassword;
}
}