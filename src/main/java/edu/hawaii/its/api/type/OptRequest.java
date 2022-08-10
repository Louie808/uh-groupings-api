package edu.hawaii.its.api.type;

import java.util.List;
import java.util.Objects;

public final class OptRequest {

    private final Boolean optValue;
    private final String optId;
    public final List<GroupType> groupTypes;
    private final String path;
    private final String username;

    private OptRequest(String optId, List<GroupType> groupTypes, Boolean optValue, String path, String username) {
        this.optId = optId;
        this.groupTypes = groupTypes;
        this.optValue = optValue;
        this.path = path;
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
        private List<GroupType> groupTypes;
        private Boolean optValue;
        private String path;
        private String username;

        public Builder withOptType(OptType optType) {
            this.optType = optType;
            return this;
        }

        public Builder withGroupTypes(List<GroupType> groupTypes) {
            this.groupTypes = groupTypes;
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

            return new OptRequest(optType.value(), groupTypes, optValue, path, username);
        }
    }
}