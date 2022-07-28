package edu.hawaii.its.api.type;

import java.util.Objects;

public final class Request {
    private final String optId;
    private final Boolean optValue;

    private Request(Builder builder) {
        this.optId = builder.optId;
        this.optValue = builder.optValue;
    }

    public static class Builder {
        private String optId;
        private Boolean optValue;

        public Builder() {

        }

        public Builder withOptId (String optId) {
            this.optId = optId;
            return this;
        }

        public Builder withOptValue (Boolean optValue) {
            this.optValue = optValue;
            return this;
        }

        public Request build() {
            Objects.requireNonNull(optId, "optType cannot be null.");
            Objects.requireNonNull(optValue, "optValue cannot be null.");
            return new Request(this);
        }
    }
}
