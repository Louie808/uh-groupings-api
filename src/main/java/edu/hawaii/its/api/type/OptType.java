package edu.hawaii.its.api.type;

public enum OptType {

    IN("uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-in", "opt-in"),
    OUT("uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-out", "opt-out");

    private final String value;
    private final String valueFormatted;

    OptType(String value, String valueFormatted) {
        this.value = value;
        this.valueFormatted = valueFormatted;
    }

    public String value() {
        return value;
    }

    public String valueFormatted() {
        return valueFormatted;
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
