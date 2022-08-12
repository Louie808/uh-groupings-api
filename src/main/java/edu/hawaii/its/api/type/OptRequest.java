package edu.hawaii.its.api.type;

import java.util.List;
import java.util.Objects;

public final class OptRequest {

    private final Boolean optValue;
    private final String optId;
    private final GroupType groupType;
    private final String pathBase;
    private final PrivilegeType privilege;
    private final String username;

    private OptRequest(String optId,
            List<GroupType> groupTypes,
            GroupType groupType,
            Boolean optValue,
            String pathBase,
            String username,
            PrivilegeType privilege) {
        this.optId = optId;
        this.groupType = groupType;
        this.optValue = optValue;
        this.pathBase = pathBase;
        this.username = username;
        this.privilege = privilege;
    }

    public String getOptId() {
        return optId;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public Boolean getOptValue() {
        return optValue;
    }

    public String getPath() {
        return pathBase;
    }

    public String getPathFull() {
        return pathBase + groupType.value();
    }

    public PrivilegeType getPrivilege() {
        return privilege;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int hashCode() {
        return Objects.hash(optId, optValue, pathBase, privilege, username);
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
                && Objects.equals(pathBase, other.pathBase)
                && Objects.equals(privilege, other.privilege)
                && Objects.equals(username, other.username);
    }

    public static class Builder {

        private OptType optType;
        private List<GroupType> groupTypes;
        private GroupType groupType;
        private Boolean optValue;
        private String pathBase;
        private PrivilegeType privilege;
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
            this.pathBase = path;
            return this;
        }

        public Builder withPrivilege(PrivilegeType privilege) {
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
            Objects.requireNonNull(pathBase, "path cannot be null.");
            Objects.requireNonNull(privilege, "privilege cannot be null.");
            Objects.requireNonNull(username, "username cannot be null.");

            switch (privilege) {
                case IN:
                    groupType = optValue ? GroupType.EXCLUDE : GroupType.INCLUDE;
                    break;
                case OUT:
                    groupType = optValue ? GroupType.INCLUDE : GroupType.EXCLUDE;
                    break;
                default:
                    groupType = GroupType.EXCLUDE;
            }

            return new OptRequest(optType.value(), groupTypes, groupType, optValue, pathBase, username, privilege);
        }
    }
}
