package com.example.saimada.shelterfinder;

/**
 * Filtering for Age Range
 * @author arnabdey
 * @since 3/5/18
 */
public enum AgeRange {
    ANYONE("Anyone"),
    YOUNGADULTS("YoungAdults"),
    CHILDREN("Children"),
    FAMILYANDNEWBORN("FamilyAndNewborn");

    private String restrictions;

    AgeRange(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getRestrictions() {
        return restrictions;
    }
}
