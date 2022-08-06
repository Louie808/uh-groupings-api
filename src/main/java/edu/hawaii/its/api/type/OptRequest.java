package edu.hawaii.its.api.type;

import java.util.Objects;

public final class OptRequest {

    private final Boolean isOptEnable;
    private final String optId;
    private final String path;
    private final String optInPath;
    private final String optOutPath;
    private final String username;

    private OptRequest(String optId, Boolean isOptEnable, String path, String optInPath, String optOutPath, String username) {
        this.optId = optId;
        this.isOptEnable = isOptEnable;
        this.path = path;
        this.optInPath = optInPath;
        this.optOutPath = optOutPath;
        this.username = username;
    }

    public String getOptId() {
        return optId;
    }

    public Boolean getIsOptEnable() {
        return isOptEnable;
    }

    public String getPath() {
        return path;
    }

    public String getOptInPath() {
        return optInPath;
    }

    public String getOptOutPath() {
        return optOutPath;
    }

    public String getUsername() {
        return username;
    }

    public static class Builder {

        private OptType optType;
        private Boolean isOptEnable;
        private String path;
        private String optInPath;
        private String optOutPath;
        private String username;

        public Builder withOptType(OptType optType) {
            this.optType = optType;
            return this;
        }

        public Builder withOptType(String optId) {
            this.optType = OptType.find(optId);
            return this;
        }

        public Builder withIsOptEnable(Boolean isOptEnable) {
            this.isOptEnable = isOptEnable;
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
            Objects.requireNonNull(isOptEnable, "optValue cannot be null.");
            Objects.requireNonNull(path, "path cannot be null.");
            Objects.requireNonNull(username, "username cannot be null.");

            optInPath = path + GroupType.find(optType, OptType.IN);
            optOutPath = path + GroupType.find(optType, OptType.OUT);

            return new OptRequest(optType.value(), isOptEnable, path, optInPath, optOutPath, username);
        }
    }
}