package edu.hawaii.its.api.type;

public enum PrivilegeType {

    IN("optin"),
    OUT("optout");

    private final String value;

    PrivilegeType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static PrivilegeType find(String value) {
        for (PrivilegeType type : PrivilegeType.values()) {
            if (type.value().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
