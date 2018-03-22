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

    private Shelter(String address, String capacity, double latitude,
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

    public interface Builder {
        Builder withAddress(String address);
        Builder withCapacity(String capacity);
        Builder atLongitude(double longitude);
        Builder atLatitude(double latitude);
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
            private double latitude, longitude;
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
            public Builder atLongitude(double longitude) {
                this.longitude = longitude;
                return this;
            }

            @Override
            public Builder atLatitude(double latitude) {
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
                if (latitude == 0.0 || longitude == 0.0) {
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
