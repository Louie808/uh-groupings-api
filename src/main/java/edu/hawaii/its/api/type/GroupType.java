package edu.hawaii.its.api.type;

import java.util.ArrayList;
import java.util.List;

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

    public static List<GroupType> find(String value) {
        List<GroupType> groupTypes = new ArrayList<>();

        if (OptType.find(value).equals(OptType.IN)) {
            groupTypes.add(INCLUDE);
            groupTypes.add(EXCLUDE);
        } else if (OptType.find(value).equals(OptType.OUT)){
            groupTypes.add(EXCLUDE);
            groupTypes.add(INCLUDE);
        }

        return groupTypes;
    }
}
