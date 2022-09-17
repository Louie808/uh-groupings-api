package edu.hawaii.its.api.gc.result;

public abstract class Results implements Resultable {
    protected boolean isEmpty(Object[] o) {
        return o == null || o.length == 0;
    }
}
