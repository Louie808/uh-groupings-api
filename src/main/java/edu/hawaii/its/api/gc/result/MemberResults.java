package edu.hawaii.its.api.gc.result;

import java.util.List;

@FunctionalInterface
public interface MemberResults<T> {
    List<T> getResults();
}
