package com.example.saimada.shelterfinder;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

public class ReservationManagerEqualsTest {
    @Rule
    public Timeout globalTimeout = Timeout.builder().withTimeout(3, TimeUnit.MINUTES).build();

    private ReservationManager a, b, c, d;
    private Object o;

    @Before
    public void setUp() {
        a = new ReservationManager();
        b = new ReservationManager();
        d = new ReservationManager();

        Shelter shelter1 = new Shelter();
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


        Shelter shelter2 = new Shelter();
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


        Shelter shelter3 = new Shelter();
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

        User user1 = new User();
        user1.setUsername("Rohit");
        user1.setPassword("Mittapalli");
        user1.setAdmin(true);
        user1.setCheckedIn(true);

        User user2 = new User();
        user2.setUsername("Sai");
        user2.setPassword("Mada");
        user2.setAdmin(false);
        user2.setCheckedIn(false);

        User user3 = new User();
        user3.setUsername("Mayank");
        user3.setPassword("Kishore");
        user3.setAdmin(true);
        user3.setCheckedIn(true);

        User user4 = new User();
        user4.setUsername("Sohan");
        user4.setPassword("Choudhury");
        user4.setAdmin(false);
        user4.setCheckedIn(true);

        a.checkIn(shelter1, user1);
        b.checkIn(shelter1, user1);
        c = a;
        d.checkIn(shelter1, user2);
        o = new Object();
    }

    @Test
    public void sameReference() {
        assertThat(a, is(equalTo(c)));
    }

    @Test
    public void sameValue() {
        assertThat(a, is(equalTo(b)));
    }

    @Test
    public void differentValue() {
        assertThat(a, is(not(equalTo(d))));
    }

    @Test
    public void nullArg() {
        assertThat(a, is(not(equalTo(null))));
    }

    @Test
    public void differentClass() {
        assertThat(a, is(not(equalTo(o))));
    }
}
