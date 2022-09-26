package edu.hawaii.its.api.type;

public enum MembershipGroupType {

    INCLUDE("INCLUDE"),
    EXCLUDE("EXCLUDE"),
    OWNERS("OWNERS"),
    ADMIN("ADMIN"),
    UNDEFINED("UNDEFINED");

    private final String value;

    MembershipGroupType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static MembershipGroupType find(String value) {
        for(MembershipGroupType membershipGroupType : MembershipGroupType.values()) {
            if (membershipGroupType.value.equals(value)) {
                return membershipGroupType;
            }
        }
        return UNDEFINED;
    }
}
