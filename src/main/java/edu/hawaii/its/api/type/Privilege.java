package edu.hawaii.its.api.type;

import org.springframework.security.core.parameters.P;

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
        for (OptType type : OptType.values()) {
            if (type.value().equals(value)) {
                return Privilege.valueOf(type.name());
            }
        }
        return null;
    }
}
