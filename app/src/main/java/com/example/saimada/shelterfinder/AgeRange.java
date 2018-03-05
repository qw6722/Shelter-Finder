package com.example.saimada.shelterfinder;

/**
 * Created by arnabdey on 3/5/18.
 */

/**
 * Filtering for Age Range
 */
public enum AgeRange {
    ANYONE("Anyone"),
    YOUNGADULTS("YoungAdults"),
    ADULTS("Adults"),
    CHILDREN("Chilren"),
    FAMILYANDNEWBORN("FamilyAndNewborn");

    private String restrictions;

    private AgeRange(String restrictions) {
        this.restrictions = restrictions;
    }

}
