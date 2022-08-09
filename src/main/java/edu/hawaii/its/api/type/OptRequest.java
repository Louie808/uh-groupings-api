package edu.hawaii.its.api.type;

import java.util.Objects;

public final class OptRequest {

    private final Boolean optValue;
    private final String optId;
    private final String privilegeId;

    public final String groupId;
    private final String path;
    private final String username;

    private OptRequest(String optId, String privilegeId, String groupId, Boolean optValue, String path, String username) {
        this.optId = optId;
        this.privilegeId = privilegeId;
        this.groupId = groupId;
        this.optValue = optValue;
        this.path = path;
        this.username = username;
    }

    public String getOptId() {
        return optId;
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public String getGroupId() {
        return groupId;
    }

    public Boolean getOptValue() {
        return optValue;
    }

    public String getPath() {
        return path;
    }

    public String getUsername() {
        return username;
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptRequest that = (OptRequest) o;
        return Objects.equals(optValue, that.optValue)
                && Objects.equals(optId, that.optId)
                && Objects.equals(path, that.path)
                && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optValue, optId, path, username);
    }

     */

    public static class Builder {

        private OptType optType;
        private Privilege privilege;
        private GroupType groupType;
        private Boolean optValue;
        private String path;
        private String username;

        public Builder() {
            this.privilege = Privilege.IN;
            this.groupType = GroupType.INCLUDE;
        }

        public Builder withOptType(OptType optType) {
            this.optType = optType;
            return this;
        }

        public Builder withPrivilege(Privilege privilege) {
            this.privilege = privilege;
            return this;
        }

        public Builder withGroupType(GroupType groupType) {
            this.groupType = groupType;
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

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public OptRequest build() {
            Objects.requireNonNull(optType, "optType cannot be null.");
            Objects.requireNonNull(optValue, "optValue cannot be null.");
            Objects.requireNonNull(path, "path cannot be null.");
            Objects.requireNonNull(username, "username cannot be null.");

            return new OptRequest(optType.value(), privilege.value(), groupType.value(), optValue, path, username);
        }
    }
}