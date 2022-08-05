package edu.hawaii.its.api.type;

public enum OptType {

    IN("uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-in"),
    OUT("uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-out");

    private final String value;

    OptType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static OptType find(String value) {
        for (OptType type : OptType.values()) {
            if (type.value().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
