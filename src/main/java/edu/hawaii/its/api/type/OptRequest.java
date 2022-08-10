package edu.hawaii.its.api.type;

import java.util.Objects;

public final class OptRequest {

    private final String groupNameRoot;
    private final GroupType groupType;
    private final Boolean optValue;
    private final String optId;
    private final PrivilegeType privilege;
    private final String username;

    private OptRequest(OptType optType,
            GroupType groupType,
            Boolean optValue,
            String groupNameRoot,
            String username,
            PrivilegeType privilege) {
        this.optId = optType.value();
        this.groupType = groupType;
        this.optValue = optValue;
        this.groupNameRoot = groupNameRoot;
        this.username = username;
        this.privilege = privilege;
    }

    public String getOptId() {
        return optId;
    }

    public Boolean getOptValue() {
        return optValue;
    }

    public String getGroupNameRoot() {
        return groupNameRoot;
    }

    public String getGroupName() {
        return groupNameRoot + groupType.value();
    }

    public PrivilegeType getPrivilege() {
        return privilege;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int hashCode() {
        return Objects.hash(optId, optValue, groupNameRoot, privilege, username);
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
                && Objects.equals(groupNameRoot, other.groupNameRoot)
                && Objects.equals(privilege, other.privilege)
                && Objects.equals(username, other.username);
    }

    public static class Builder {

        private String groupNameRoot;
        private GroupType groupType;
        private OptType optType;
        private Boolean optValue;
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

        public Builder withGroupNameRoot(String groupNameRoot) {
            this.groupNameRoot = groupNameRoot;
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
            Objects.requireNonNull(groupTypes, "groupTypes cannot be null.");
            Objects.requireNonNull(optValue, "optValue cannot be null.");
            Objects.requireNonNull(groupNameRoot, "groupNameRoot cannot be null.");
            Objects.requireNonNull(username, "username cannot be null.");
            Objects.requireNonNull(privilege, "privilege cannot be null.");

            switch (privilege) {
                case IN:
                    groupType = optValue ? GroupType.EXCLUDE : GroupType.INCLUDE;
                    break;
                case OUT:
                    groupType = optValue ? GroupType.INCLUDE : GroupType.EXCLUDE;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid PrivilegeType: " + privilege);

            }

            return new OptRequest(optType, groupType, optValue, groupNameRoot, username, privilege);
        }
    }
}
