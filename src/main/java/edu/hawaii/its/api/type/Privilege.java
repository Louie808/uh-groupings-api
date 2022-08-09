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
}
