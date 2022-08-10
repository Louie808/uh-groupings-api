package edu.hawaii.its.api.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class OptRequest {

    private final Boolean optValue;
    private final String optId;
    public final List<GroupType> groupTypes;
    private final String path;
    private final Privilege privilege;
    private final String username;

    private OptRequest(String optId, List<GroupType> groupTypes, Boolean optValue, String path, String username, Privilege privilege) {
        this.optId = optId;
        this.groupTypes = groupTypes;
        this.optValue = optValue;
        this.path = path;
        this.privilege = privilege;
        this.username = username;
    }

    public String getOptId() {
        return optId;
    }

    public List<GroupType> getGroupTypes() {
        return groupTypes;
    }

    public Boolean getOptValue() {
        return optValue;
    }

    public String getPath() {
        return path;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int hashCode() {
        return Objects.hash(optId, optValue, path, privilege, username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OptRequest other = (OptRequest) obj;
        return Objects.equals(optId, other.optId)
                && Objects.equals(optValue, other.optValue)
                && Objects.equals(path, other.path)
                && Objects.equals(privilege, other.privilege)
                && Objects.equals(username, other.username);
    }

    public static class Builder {

        private OptType optType;
        private List<GroupType> groupTypes;
        private Boolean optValue;
        private String path;
        private Privilege privilege;
        private String username;

        public Builder withOptType(OptType optType) {
            this.optType = optType;
            return this;
        }

        public Builder withOptValue(Boolean optValue) {
            this.optValue = optValue;
            return this;
        }

        public Builder withPath(String path) {
            this.path = path;
            return this;
        }

        public Builder withPrivilege(Privilege privilege) {
            this.privilege = privilege;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public OptRequest build() {
            Objects.requireNonNull(optType, "optType cannot be null.");
            Objects.requireNonNull(optValue, "optValue cannot be null.");
            Objects.requireNonNull(path, "path cannot be null.");
            Objects.requireNonNull(privilege, "privilege cannot be null.");
            Objects.requireNonNull(username, "username cannot be null.");

            groupTypes = new ArrayList<>();
            switch (optType) {
                case IN:
                    groupTypes.add(GroupType.INCLUDE);
                    groupTypes.add(GroupType.EXCLUDE);
                    break;

                case OUT:
                    groupTypes.add(GroupType.EXCLUDE);
                    groupTypes.add(GroupType.INCLUDE);
                    break;
            }

            return new OptRequest(optType.value(), groupTypes, optValue, path, username, privilege);
        }
    }
}