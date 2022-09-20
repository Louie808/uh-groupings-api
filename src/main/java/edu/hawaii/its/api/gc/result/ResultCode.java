package edu.hawaii.its.api.gc.result;

public enum ResultCode {

    EMPTY(""),
    FAILURE("FAILURE"),
    SUCCESS("SUCCESS"),
    SUBJECT_NOT_FOUND("SUBJECT_NOT_FOUND");

    private final String value;

    ResultCode(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
