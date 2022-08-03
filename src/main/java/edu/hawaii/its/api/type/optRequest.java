package edu.hawaii.its.api.type;

import java.util.Objects;

public final class optRequest extends Request {
    private final String optId;
    private final Boolean optValue;
    private final String OPT_IN = "uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-in";
    private final String OPT_OUT = "uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-out";
    private final String EXCLUDE = ":exclude";
    private final String INCLUDE = ":include";
    private final String PRIVILEGE_OPT_OUT = "optout";
    private final String PRIVILEGE_OPT_IN = "optin";

    public optRequest(Builder builder) {
        super(builder);
        this.optId = builder.optId;
        this.optValue = builder.optValue;
    }

    public String getOptId () {
        return optId;
    }

    public Boolean getOptValue () {
        return optValue;
    }

    public String getPathExtension (String privilegeName) {
        if (PRIVILEGE_OPT_IN.equals(privilegeName) && OPT_IN.equals(optId) ) {
            return getPath() + INCLUDE;
        } else if (PRIVILEGE_OPT_IN.equals(privilegeName) && OPT_OUT.equals(optId)) {
            return getPath() + EXCLUDE;
        } else if (PRIVILEGE_OPT_OUT.equals(privilegeName) && OPT_IN.equals(optId)) {
            return getPath() + EXCLUDE;
        } else if (PRIVILEGE_OPT_OUT.equals(privilegeName) && OPT_OUT.equals(optId)) {
            return getPath() + INCLUDE;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public static class Builder extends Request.Builder<Builder> {
        private String optId;
        private Boolean optValue;

        public Builder(String currentUser) {
            super(currentUser);
        }

        public Builder withOptId (String optId) {
            this.optId = optId;
            return this;
        }

        public Builder withOptValue (Boolean optValue) {
            this.optValue = optValue;
            return this;
        }

        @Override
        public optRequest build() {
            Objects.requireNonNull(optId, "optId cannot be null.");
            Objects.requireNonNull(optValue, "optValue cannot be null.");
            return new optRequest(this);
        }
    }
}
