package edu.hawaii.its.api.type;

import java.util.Objects;


public final class Request {
    private final String currentUser;
    private final String path;
    private final String optId;
    private final Boolean optValue;

    private Request(Builder builder) {
        this.currentUser = builder.currentUser;
        this.path = builder.path;
        this.optId = builder.optId;
        this.optValue = builder.optValue;
    }

    public static class Builder {
        private String currentUser;
        private String path;
        private String optId;
        private Boolean optValue;

        private String OPT_IN = "uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-in";

        private String OPT_OUT = "uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-out";

        private String EXCLUDE = ":exclude";

        private String INCLUDE = ":include";

        private String PRIVILEGE_OPT_OUT = "optout";

        private String PRIVILEGE_OPT_IN = "optin";

        public Builder(String currentUser, String path) {
            this.currentUser = currentUser;
            this.path = path;
        }

        public Builder withOptId (String optId) {
            this.optId = optId;
            return this;
        }

        public Builder withOptValue (Boolean optValue) {
            this.optValue = optValue;
            return this;
        }

        public String getCurrentUser () {
            return currentUser;
        }

        public String getPath () {
            return path;
        }
        public String getOptId () {
            return optId;
        }

        public Boolean getOptValue () {
            return optValue;
        }

        public String getPathExtension (String privilegeName) {
            if (PRIVILEGE_OPT_IN.equals(privilegeName) && OPT_IN.equals(optId) ) {
                return path + INCLUDE;
            } else if (PRIVILEGE_OPT_IN.equals(privilegeName) && OPT_OUT.equals(optId)) {
                return  path + EXCLUDE;
            } else if (PRIVILEGE_OPT_OUT.equals(privilegeName) && OPT_IN.equals(optId)) {
                return  path + EXCLUDE;
            } else if (PRIVILEGE_OPT_OUT.equals(privilegeName) && OPT_OUT.equals(optId)) {
                return  path + INCLUDE;
            } else {
                throw new UnsupportedOperationException();
            }
        }

        public Request build() {
            Objects.requireNonNull(currentUser, "currentUser cannot be null.");
            Objects.requireNonNull(path, "path cannot be null.");
            Objects.requireNonNull(optId, "optId cannot be null.");
            Objects.requireNonNull(optValue, "optValue cannot be null.");
            return new Request(this);
        }
    }
}
