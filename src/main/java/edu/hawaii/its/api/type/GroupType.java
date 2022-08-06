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

    public static String find(OptType optType, OptType givenOptType) {
        if (optType.equals(givenOptType)) {
            return INCLUDE.value();
        } else {
            return EXCLUDE.value();
        }
    }
}
