package edu.hawaii.its.api.type;

import java.util.Objects;


public class Request {
    private final String currentUser;
    private final String path;

    public Request(Builder builder) {
        this.currentUser = builder.currentUser;
        this.path = builder.path;
    }

    public String getCurrentUser () {
        return currentUser;
    }
    public String getPath () {
        return path;
    }

    public static class Builder<B extends Builder> {
        protected String currentUser;
        protected String path;

        public Builder(String currentUser) {
            this.currentUser = currentUser;
        }

        public B withPath (String path) {
            this.path = path;
            return (B) this;
        }

        public Request build() {
            Objects.requireNonNull(currentUser, "currentUser cannot be null.");
            return new Request(this);
        }
    }
}
