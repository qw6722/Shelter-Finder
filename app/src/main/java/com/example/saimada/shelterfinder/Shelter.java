package com.example.saimada.shelterfinder;

/**
 * Represents by a Shelter
 * Rewritten by
 * @author Shishir
 * @since 3/22/18
 */
public class Shelter {

    private String address;
    private String capacity;
    private String initialCapacity;
    private double latitude;
    private double longitude;
    private String phoneNumber;
    private String restrictions;
    private String shelterName;
    private String specialNotes;
    private String uniqueKey;

    public Shelter() {

    }
    public Shelter(String address, String capacity, double latitude,
                   double longitude, String phoneNumber, String restrictions,
                   String shelterName, String specialNotes, String uniqueKey,
                   String intCapacity) {
        this.address = address;
        this.capacity = capacity;
        this.initialCapacity = intCapacity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
        this.restrictions = restrictions;
        this.shelterName = shelterName;
        this.specialNotes = specialNotes;
        this.uniqueKey = uniqueKey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getIntCapacity() {
        return initialCapacity;
    }

    public void setIntCapacity(String intCapNew) {
        this.initialCapacity = intCapNew;
    }

    @Override
    public String toString() {
        return String.format("Address %s%n Shelter Name %s",
                address, shelterName);
    }
}
