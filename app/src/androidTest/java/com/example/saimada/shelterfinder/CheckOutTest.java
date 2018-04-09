package com.example.saimada.shelterfinder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by saimada on 4/9/18.
 */
public class CheckOutTest {

    User user1;
    User user2;
    User user3;
    User user4;
    User user5;

    Shelter shelter1;
    Shelter shelter2;
    Shelter shelter3;

    ReservationManager mayankKishore;
    @Before
    public void setUp() {

        mayankKishore = new ReservationManager();

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
        shelter2.setShelterName("Shelter 2");
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

        user1 = new User();
        user1.setUsername("Rohit");
        user1.setPassword("Mittapalli");
        user1.setAdmin(true);
        user1.setCheckedIn(true);

        user2 = new User();
        user2.setUsername("Sai");
        user2.setPassword("Mada");
        user2.setAdmin(false);
        user2.setCheckedIn(false);

        user3 = new User();
        user3.setUsername("Mayank");
        user3.setPassword("Kishore");
        user3.setAdmin(true);
        user3.setCheckedIn(true);

        user4 = new User();
        user4.setUsername("Sohan");
        user4.setPassword("Choudhury");
        user4.setAdmin(false);
        user4.setCheckedIn(true);

        user5 = new User("username", "paswword", false, false);
    }

    @Test
    public void multipleUsersInOneShelter() {
        //Checks If One Shelter Can remove Multiple Users
        assertTrue(mayankKishore.checkOut(user1));
        assertTrue(mayankKishore.checkOut(user2));
        assertTrue(mayankKishore.checkOut(user3));
        assertTrue(mayankKishore.checkOut(user4));
    }

    @Test
    public void sameUserSameShelter() {
        //Checks If the Program Blocks a User from being double removed from the same shelter
        mayankKishore.checkOut(user1);
        assertFalse(mayankKishore.checkOut(user1));
    }

    @Test
    public void userCountOneShelter() {
        //Checks If the Program Blocks a User from being double booked
        mayankKishore.checkIn(shelter1,user1);
        assertFalse(mayankKishore.checkIn(shelter2,user1));
        assertTrue(mayankKishore.checkIn(shelter2,user2));
    }

    @Test
    public void userCountOneUseRMShelter() {
        //Checks If One Shelter Can Hold Multiple Users AND check out multiple users for right count
        mayankKishore.checkIn(shelter1,user1);
        mayankKishore.checkIn(shelter1,user2);
        mayankKishore.checkIn(shelter1,user3);
        mayankKishore.checkIn(shelter1,user4);
        mayankKishore.checkOut(user1);
        mayankKishore.checkOut(user2);
        mayankKishore.checkOut(user3);
        mayankKishore.checkOut(user4);

        assertEquals(0,mayankKishore.getReservations().get(shelter1).size());
    }




}
