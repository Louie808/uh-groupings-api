package edu.hawaii.its.api.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptRequestTest {
    @Test
    public void test1() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("jordanw4")
                .withPath("tmp:jordanw4:jordanw4-aux")
                .withOptType(OptType.IN)
                .withIsOptEnable(false)
                .build();

        assertEquals(optRequest.getPathExtension(OptType.IN), "tmp:jordanw4:jordanw4-aux:include");
    }
}