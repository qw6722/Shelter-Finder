package com.example.saimada.shelterfinder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by vadini on 4/9/18.
 */
public class CheckingEqualsUser {
    User user1;
    User user2;
    User user3;
    User user4;
    User user5;
    Shelter shelter1;
    @Before
    public void setUp() {
        user1 = new User();
        user1.setUsername("Username");
        user1.setPassword("Password");
        user1.setAdmin(true);
        user1.setCheckedIn(true);

        user2 = new User();
        user2.setUsername("Bob Schmob");
        user2.setPassword("Password");
        user2.setAdmin(false);
        user2.setCheckedIn(false);

        user3 = new User();
        user3.setUsername("Username");
        user3.setPassword("Password");
        user3.setAdmin(true);
        user3.setCheckedIn(true);

        user4 = new User();
        user4.setUsername("Username");
        user4.setPassword("World");
        user4.setAdmin(false);
        user4.setCheckedIn(true);

        user5 = user1;

        shelter1 = new Shelter();
    }

    @Test
    public void differentClasses() {
        assertFalse(shelter1.equals(user1));
    }
    
    @Test
    public void sameObject() {
        assertTrue(user5.equals(user1));
    }

    @Test
    public void againstNull() {
        assertFalse(user4.equals(null));
    }

    @Test
    public void notEqual() {
        assertFalse(user1.equals(user2));
    }

    @Test
    public void sameUsername() {
        assertTrue(user1.equals(user4));
    }

    @Test
    public void samePassword() { assertFalse(user2.equals(user3)); }

}
