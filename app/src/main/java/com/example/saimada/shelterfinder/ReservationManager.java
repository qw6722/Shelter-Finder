package com.example.saimada.shelterfinder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A class designed to keep track of which Shelters have which users
 * @author Shishir Bhat
 * @version 1.0
 * @since 3/22/18
 */
public class ReservationManager {
    // Uses two-way mapping for check-in and check-out to be O(1)
    // no matter how many people are using the app
    private Map<Shelter, List<User>> reservations;
    private Map<User, Shelter> usersCheckedIn;
    private static ReservationManager instance;

    public ReservationManager() {
        reservations = new HashMap<>();
        usersCheckedIn = new HashMap<>();
    }

    public static ReservationManager getInstance() {
        if (instance == null) {
            instance = new ReservationManager();
        }
        return instance;
    }

    public Map<Shelter, List<User>> getReservations() {
        return reservations;
    }

    public Map<User, Shelter> getUsersCheckedIn() {
        return usersCheckedIn;
    }

    /**
     * Checks a user into a shelter.
     * @param shelter The shelter to check the user into
     * @param user The user to check in
     * @return true if check in was successful, false if user is already checked in at another
     *         shelter
     */
    public boolean checkIn(Shelter shelter, User user) {
        if (usersCheckedIn.containsKey(user)) {
            return false;
        }
        usersCheckedIn.put(user, shelter);
        if (!reservations.containsKey(shelter)) {
            reservations.put(shelter, new LinkedList<User>());
        }
        reservations.get(shelter).add(user);
        return true;
    }

    /**
     * Checks a user out of the Shelter they are in
     * @param user The user to check out
     * @return true if the user was checked out, false if the user was never checked in to a shelter
     */
    public boolean checkOut(User user) {
        if (!usersCheckedIn.containsKey(user)) {
            return false;
        }
        Shelter s = usersCheckedIn.get(user);
        usersCheckedIn.remove(user);
        reservations.get(s).remove(user);
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ReservationManager)) {
            return false;
        }
        ReservationManager that = (ReservationManager) other;
        return usersCheckedIn.equals(that.usersCheckedIn) && reservations.equals(that.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersCheckedIn, reservations);
    }
}
