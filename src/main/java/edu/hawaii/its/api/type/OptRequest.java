package edu.hawaii.its.api.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class OptRequest {

    private final Boolean optValue;
    private final String optId;
    private final List<GroupType> groupTypes;
    private final GroupType groupType;
    private final String path;
    private final PrivilegeType privilege;
    private final String username;

    private OptRequest(String optId,
            List<GroupType> groupTypes,
            GroupType groupType,
            Boolean optValue,
            String path,
            String username,
            PrivilegeType privilege) {
        this.optId = optId;
        this.groupTypes = groupTypes;
        this.groupType = groupType;
        this.optValue = optValue;
        this.path = path;
        this.username = username;
        this.privilege = privilege;
    }

    public String getOptId() {
        return optId;
    }

    private List<GroupType> getGroupTypes() {
        return groupTypes;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public Boolean getOptValue() {
        return optValue;
    }

    public String getPath() {
        return path;
    }

    public PrivilegeType getPrivilege() {
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
        private GroupType groupType;
        private Boolean optValue;
        private String path;
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
            this.path = path;
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
            Objects.requireNonNull(path, "path cannot be null.");
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
                default:
                    // Emtpy.
                    break;
            }

            return new OptRequest(optType.value(), groupTypes, groupType, optValue, path, username, privilege);
        }
    }
}
