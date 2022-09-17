package edu.hawaii.its.api.gc.result;

@FunctionalInterface
public interface Resultable {
    <T> T getResultCode();
}
