package com.example.saimada.shelterfinder;

/**
 * Created by arnabdey on 3/5/18.
 */

/**
 *
 * Enums filtering for gender
 */
public enum Gender {
    ANYONE("Anyone"),
    MALE("Male"),
    FEMALE("Female");

    private String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

}
