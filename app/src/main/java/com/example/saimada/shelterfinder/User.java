package com.example.saimada.shelterfinder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by saimada on 2/20/18.
 */

public class User {
    public static List<String> possibleGender = Arrays.asList("Anyone","Male", "Female");
    public static List<String> possibleAges = Arrays.asList("Anyone", "YoungAdults", "Adults", "Chilren", "FamilyAndNewborn");


    private String username;
    private String password;
    private boolean isAdmin;


    /**
     * Creates user object.
     * @param username user's username
     * @param password user's password
     * @param isAdmin user's admins status
     */
    public User(String username, String password, boolean isAdmin){

        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;

    }


    /**
     * @return password for user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password takes in a string to change the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return if the user is an admin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * @param admin changes admin status
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * @return if the username of the user
     */
    public String getUsername() {
        return username;
    }


    /**
     * @param username changes username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
