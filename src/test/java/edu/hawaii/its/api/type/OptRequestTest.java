package edu.hawaii.its.api.type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptRequestTest {

    private OptRequest optRequest;

    @BeforeEach
    public void setup() {


    }
    @Test
    public void test1() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("jordanw4")
                .withPath("tmp:jordanw4:jordanw4-aux")
                .withOptType(OptType.find("uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-in"))
                .withOptValue(false)
                .build();

        assertEquals(optRequest.getOptId(),"uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-in");
        assertEquals(optRequest.getPath(), "tmp:jordanw4:jordanw4-aux");
        assertEquals(OptType.IN.value(), "uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-in");
    }

    @Test
    public void test2() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("jordanw4")
                .withPath("tmp:jordanw4:jordanw4-aux")
                .withOptType(OptType.IN)
                .withPrivilege(Privilege.IN)
                .withOptValue(false)
                .build();

        assertEquals(optRequest.getOptId(),"uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-in");
        assertEquals(optRequest.getPath(), "tmp:jordanw4:jordanw4-aux:include");
        assertEquals(OptType.IN.value(), "uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-in");
    }

    @Test
    public void test3() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("jordanw4")
                .withPath("tmp:jordanw4:jordanw4-aux")
                .withOptType(OptType.IN)
                .withPrivilege(Privilege.OUT)
                .withOptValue(false)
                .build();

        assertEquals(optRequest.getOptId(),"uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-in");
        assertEquals(optRequest.getPath(), "tmp:jordanw4:jordanw4-aux:exclude");
        assertEquals(OptType.IN.value(), "uh-settings:attributes:for-groups:uh-grouping:anyone-can:opt-in");
    }
}