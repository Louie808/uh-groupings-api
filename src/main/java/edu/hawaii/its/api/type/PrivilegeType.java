package edu.hawaii.its.api.type;

public enum PrivilegeType {

    IN("optin", "opt-in"),
    OUT("optout", "opt-out"),
    UNDEFINED("", "");

    private final String value;
    private final String valueFormatted;

    PrivilegeType(String value, String valueFormatted) {
        this.value = value;
        this.valueFormatted = valueFormatted;
    }

    public String value() {
        return value;
    }

    public String valueFormatted() {
        return valueFormatted;
    }

    public static PrivilegeType find(String value) {
        for (PrivilegeType type : PrivilegeType.values()) {
            if (type.value().equals(value)) {
                return type;
            }
        }
        return UNDEFINED;
    }
}
