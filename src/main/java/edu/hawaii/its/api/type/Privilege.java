package edu.hawaii.its.api.type;

public enum Privilege {

    IN("optin"),
    OUT("optout");

    private final String value;

    Privilege(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Privilege find(String value) {
        for (Privilege type : Privilege.values()) {
            if (type.value().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
