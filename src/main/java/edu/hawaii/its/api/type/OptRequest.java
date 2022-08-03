package edu.hawaii.its.api.type;

import java.util.Objects;

public final class OptRequest extends Request {
    private final String optId;
    private final Boolean isOptValue;
    private final String EXCLUDE = ":exclude";
    private final String INCLUDE = ":include";

    public OptRequest(Builder builder) {
        super(builder);
        this.optId = builder.optId;
        this.isOptValue = builder.isOptValue;
    }

    public String getOptId () {
        return optId;
    }

    public Boolean getIsOptValue() {
        return isOptValue;
    }

    public String getPathExtension (String givenOptId) {
        if (optId.equals(givenOptId)) {
            return getPath() + INCLUDE;
        } else {
            return getPath() + EXCLUDE;
        }
    }

    public static class Builder extends Request.Builder<Builder> {
        private String optId;
        private Boolean isOptValue;

        public Builder(String currentUser) {
            super(currentUser);
        }

        public Builder withOptId (String optId) {
            this.optId = optId;
            return this;
        }

        public Builder withIsOptValue(Boolean isOptValue) {
            this.isOptValue = isOptValue;
            return this;
        }

        @Override
        public OptRequest build() {
            Objects.requireNonNull(optId, "optId cannot be null.");
            Objects.requireNonNull(isOptValue, "optValue cannot be null.");
            return new OptRequest(this);
        }
    }
}
