package com.example.saimada.shelterfinder;

/**
 * Created by rohit on 2/28/2018.
 */

public class Shelter {

    private String Address;
    private String Capacity;
    private String InitialCapacity;
    private double Latitude;
    private double Longitude;
    private String PhoneNumber;
    private String Restrictions;
    private String ShelterName;
    private String SpecialNotes;
    private String UniqueKey;

    public Shelter() {

    }
    public Shelter(String address, String capacity, double latitude, double longitude,
                   String phoneNumber, String restrictions, String shelterName,
                   String specialNotes, String uniqueKey, String intCapacity) {
        Address = address;
        Capacity = capacity;
        InitialCapacity = intCapacity;
        Latitude = latitude;
        Longitude = longitude;
        PhoneNumber = phoneNumber;
        Restrictions = restrictions;
        ShelterName = shelterName;
        SpecialNotes = specialNotes;
        UniqueKey = uniqueKey;

    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getRestrictions() {
        return Restrictions;
    }

    public void setRestrictions(String restrictions) {
        Restrictions = restrictions;
    }

    public String getShelterName() {
        return ShelterName;
    }

    public void setShelterName(String shelterName) {
        ShelterName = shelterName;
    }

    public String getSpecialNotes() {
        return SpecialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        SpecialNotes = specialNotes;
    }

    public String getUniqueKey() {
        return UniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        UniqueKey = uniqueKey;
    }

    public String getIntCapacity() { return InitialCapacity; }

    public void setIntCapacity(String intCapNew) { InitialCapacity = intCapNew; }

    @Override
    public String toString() {
        return "Address " + this.getAddress() + "/n Shelter Name " + this.getShelterName();
    }
}
