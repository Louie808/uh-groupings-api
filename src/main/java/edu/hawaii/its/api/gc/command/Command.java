package edu.hawaii.its.api.gc.command;

@FunctionalInterface
public interface Command<T> {
    T execute();
}
