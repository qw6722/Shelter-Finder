package com.example.saimada.shelterfinder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by arnabdey on 4/4/18.
 */

public class CheckingEquals {
    Shelter shelter1;
    Shelter shelter2;
    Shelter shelter3;
    User sampleUser;
    @Before
    public void setUp() {
        shelter1 = new Shelter();
        shelter1.setShelterName("Shelter 1");
        shelter1.setRestrictions("Anyone");
        shelter1.setLatitude("20");
        shelter1.setLongitude("20");
        shelter1.setCapacity("100");
        shelter1.setAddress("Shelter 1 Address");
        shelter1.setPhoneNumber("111-111-1111");
        shelter1.setSpecialNotes("N/A");
        shelter1.setIntCapacity("100");
        shelter1.setUniqueKey("2");


        shelter2 = new Shelter();
        shelter2.setShelterName("Shelter 1");
        shelter2.setRestrictions("Anyone");
        shelter2.setLatitude("20");
        shelter2.setLongitude("20");
        shelter2.setCapacity("100");
        shelter2.setAddress("Shelter 1 Address");
        shelter2.setPhoneNumber("111-111-1111");
        shelter2.setSpecialNotes("N/A");
        shelter2.setIntCapacity("100");
        shelter2.setUniqueKey("2");


        shelter3 = new Shelter();
        shelter3.setShelterName("Shelter 3");
        shelter3.setRestrictions("Men");
        shelter3.setLatitude("80");
        shelter3.setLongitude("80");
        shelter3.setCapacity("120");
        shelter3.setAddress("Shelter 3 Address");
        shelter3.setPhoneNumber("222-222-2222");
        shelter3.setSpecialNotes("N/A");
        shelter3.setIntCapacity("200");
        shelter3.setUniqueKey("3");


        sampleUser = new User("username", "paswword", false, false);
    }

    @Test
    public void againstNull() {
        assertFalse(shelter1.equals(null));
    }

    @Test
    public void sameType() {
        assertTrue(shelter1.equals(shelter1));
    }

    @Test
    public void differentClasses() {
        assertFalse(shelter1.equals(sampleUser));
    }

    @Test
    public void sameObject() {assertTrue(shelter1.equals(shelter1)); }

    @Test
    public void notEqual() { assertFalse(shelter1.equals(shelter3));}
}
