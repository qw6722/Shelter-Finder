package com.example.saimada.shelterfinder;

import java.util.Objects;

/**
 * Represents by a Shelter
 * Rewritten by
 * @author Shishir
 * @since 3/22/18
 */
public class Shelter {

    private String Address;
    private String Capacity;
    private String InitialCapacity;
    private String Latitude;
    private String Longitude;
    private String PhoneNumber;
    private String Restrictions;
    private String ShelterName;
    private String SpecialNotes;
    private String UniqueKey;

    private Shelter() {

    }

    private Shelter(String address, String capacity, String latitude,
                   String longitude, String phoneNumber, String restrictions,
                   String shelterName, String specialNotes, String uniqueKey,
                   String intCapacity) {
        this.Address = address;
        this.Capacity = capacity;
        this.InitialCapacity = intCapacity;
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.PhoneNumber = phoneNumber;
        this.Restrictions = restrictions;
        this.ShelterName = shelterName;
        this.SpecialNotes = specialNotes;
        this.UniqueKey = uniqueKey;
    }

    public interface Builder {
        Builder withAddress(String address);
        Builder withCapacity(String capacity);
        Builder atLongitude(String longitude);
        Builder atLatitude(String latitude);
        Builder withPhoneNumber(String phoneNumber);
        Builder withRestrictions(String restrictions);
        Builder withName(String name);
        Builder withSpecialNotes(String specialNotes);
        Builder withUniqueKey(String uniqueKey);
        Builder withInitialCapacity(String initialCapacity);
        Shelter build();
    }

    public Builder builder() {
        return new Builder() {
            private String address, uniqueKey, phoneNumber, restrictions, shelterName, specialNotes;
            private String capacity, initialCapacity;
            private String latitude, longitude;
            private final String BUILD_FAILURE = "Failed to construct new Shelter. Not enough "
                                                    + "information provided.";

            @Override
            public Builder withAddress(String address) {
                this.address = address;
                return this;
            }

            @Override
            public Builder withCapacity(String capacity) {
                this.capacity = capacity;
                return this;
            }

            @Override
            public Builder atLongitude(String longitude) {
                this.longitude = longitude;
                return this;
            }

            @Override
            public Builder atLatitude(String latitude) {
                this.latitude = latitude;
                return this;
            }

            @Override
            public Builder withPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
                return this;
            }

            @Override
            public Builder withRestrictions(String restrictions) {
                this.restrictions = restrictions;
                return this;
            }

            @Override
            public Builder withName(String name) {
                this.shelterName = name;
                return this;
            }

            @Override
            public Builder withSpecialNotes(String specialNotes) {
                this.specialNotes = specialNotes;
                return this;
            }

            @Override
            public Builder withUniqueKey(String uniqueKey) {
                this.uniqueKey = uniqueKey;
                return this;
            }

            @Override
            public Builder withInitialCapacity(String initialCapacity) {
                this.initialCapacity = initialCapacity;
                return this;
            }

            @Override
            public Shelter build() {
                checkForNulls(address, capacity, phoneNumber, restrictions, shelterName,
                                    specialNotes, uniqueKey, initialCapacity);
                if (latitude.equals("0.0") || longitude.equals("0.0")) {
                    throw new IllegalStateException(BUILD_FAILURE);
                }
                return new Shelter(address, capacity, latitude, longitude, phoneNumber,
                                    restrictions, shelterName, specialNotes, uniqueKey,
                                    initialCapacity);
            }

            private void checkForNulls(Object ... args) {
                for (Object o : args) {
                    if (o == null) {
                        throw new IllegalStateException(BUILD_FAILURE);
                    }
                }
            }
        };
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        this.Capacity = capacity;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        this.Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        this.Longitude = longitude;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public String getRestrictions() {
        return Restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.Restrictions = restrictions;
    }

    public String getShelterName() {
        return ShelterName;
    }

    public void setShelterName(String shelterName) {
        this.ShelterName = shelterName;
    }

    public String getSpecialNotes() {
        return SpecialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.SpecialNotes = specialNotes;
    }

    public String getUniqueKey() {
        return UniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.UniqueKey = uniqueKey;
    }

    public String getIntCapacity() {
        return InitialCapacity;
    }

    public void setIntCapacity(String intCapNew) {
        this.InitialCapacity = intCapNew;
    }

    @Override
    public String toString() {
        return String.format("Address %s%n Shelter Name %s",
                Address, ShelterName);
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof Shelter
                && UniqueKey.equals(((Shelter) other).UniqueKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UniqueKey);
    }
}
