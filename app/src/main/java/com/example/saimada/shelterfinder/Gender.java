package com.example.saimada.shelterfinder;

/**
 * Enum filtering for gender
 * @author arnabdey
 * @since 3/5/18
 */
public enum Gender {
    ANYONE("Anyone"),
    MALE("Male"),
    FEMALE("Female");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
