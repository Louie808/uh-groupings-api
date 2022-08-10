package edu.hawaii.its.api.type;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OptRequestTest {

    @Test
    public void test1() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("jordanw4")
                .withPath("tmp:jordanw4:jordanw4-aux")
                .withOptType(OptType.IN)
                .withOptValue(false)
                .build();

        assertEquals(optRequest.getOptId(), OptType.IN.value());
        assertEquals(optRequest.getPath(), "tmp:jordanw4:jordanw4-aux");
    }

    @Test
    public void test2() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("jordanw4")
                .withPath("tmp:jordanw4:jordanw4-aux")
                .withOptType(OptType.IN)
                .withOptValue(false)
                .build();

        assertEquals(optRequest.getOptId(), OptType.IN.value());
        //        assertEquals(optRequest.getPath(), "tmp:jordanw4:jordanw4-aux:include");
    }

    @Test
    public void test3() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("jordanw4")
                .withPath("tmp:jordanw4:jordanw4-aux")
                .withOptType(OptType.IN)
                .withOptValue(false)
                .build();

        assertEquals(optRequest.getOptId(), OptType.IN.value());
        //        assertEquals(optRequest.getPath(), "tmp:jordanw4:jordanw4-aux:exclude");
    }
}