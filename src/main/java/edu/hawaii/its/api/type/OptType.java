package edu.hawaii.its.api.type;

public enum OptType {

    IN("uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-in"),
    OUT("uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-out");

    private final String value;

    OptType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
