package edu.hawaii.its.api.type;

public enum GroupType {

    INCLUDE(":include"),
    EXCLUDE(":exclude");

    private final String value;

    GroupType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
