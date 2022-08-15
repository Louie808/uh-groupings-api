package edu.hawaii.its.api.type;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OptRequestTest {

    @Test
    public void optTypeInIn() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("yoda")
                .withGroupNameRoot("t:yoda:yoda-aux")
                .withPrivilege(PrivilegeType.IN)
                .withOptType(OptType.IN)
                .withOptValue(false)
                .build();

        assertEquals(optRequest, optRequest);
        assertThat(optRequest.getOptId(), equalTo(OptType.IN.value()));
        assertThat(optRequest.getGroupName(), equalTo("t:yoda:yoda-aux:include"));
        assertThat(optRequest.getGroupNameRoot(), equalTo("t:yoda:yoda-aux"));
    }

    @Test
    public void optTypeInOut() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("yoda")
                .withGroupNameRoot("t:yoda:yoda-aux")
                .withPrivilege(PrivilegeType.IN)
                .withOptType(OptType.OUT)
                .withOptValue(false)
                .build();

        assertEquals(optRequest, optRequest);
        assertEquals(optRequest.getOptId(), OptType.OUT.value());
        assertThat(optRequest.getOptId(), equalTo(OptType.OUT.value()));
        assertThat(optRequest.getGroupName(), equalTo("t:yoda:yoda-aux:exclude"));
        assertThat(optRequest.getGroupNameRoot(), equalTo("t:yoda:yoda-aux"));
        assertThat(optRequest.getOptValue(), notNullValue());
        assertThat(optRequest.getPrivilege(), notNullValue());
        assertThat(optRequest.getUsername(), notNullValue());
    }

    @Test
    public void optTypeOutIn() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("yoda")
                .withGroupNameRoot("t:yoda:yoda-aux")
                .withPrivilege(PrivilegeType.OUT)
                .withOptType(OptType.IN)
                .withOptValue(false)
                .build();

        assertEquals(optRequest, optRequest);
        assertThat(optRequest.getOptId(), equalTo(OptType.IN.value()));
        assertThat(optRequest.getGroupName(), equalTo("t:yoda:yoda-aux:exclude"));
        assertThat(optRequest.getGroupNameRoot(), equalTo("t:yoda:yoda-aux"));
    }

    @Test
    public void optTypeOutOut() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("yoda")
                .withGroupNameRoot("t:yoda:yoda-aux")
                .withPrivilege(PrivilegeType.OUT)
                .withOptType(OptType.OUT)
                .withOptValue(false)
                .build();

        assertEquals(optRequest, optRequest);
        assertThat(optRequest.getOptId(), equalTo(OptType.OUT.value()));
        assertThat(optRequest.getGroupName(), equalTo("t:yoda:yoda-aux:include"));
        assertThat(optRequest.getGroupNameRoot(), equalTo("t:yoda:yoda-aux"));
    }

    @Test
    public void build() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> new OptRequest.Builder()
                        .build());
        String expectedMessage = "optType cannot be null.";
        String actualMessage = exception.getMessage();
        assertThat(actualMessage, equalTo(expectedMessage));

        exception = assertThrows(NullPointerException.class,
                () -> new OptRequest.Builder()
                        .withOptType(OptType.OUT)
                        .build());
        expectedMessage = "optValue cannot be null.";
        actualMessage = exception.getMessage();
        assertThat(actualMessage, equalTo(expectedMessage));

        exception = assertThrows(NullPointerException.class,
                () -> new OptRequest.Builder()
                        .withOptType(OptType.OUT)
                        .withOptValue(Boolean.TRUE)
                        .build());
        expectedMessage = "groupNameRoot cannot be null.";
        actualMessage = exception.getMessage();
        assertThat(actualMessage, equalTo(expectedMessage));

        exception = assertThrows(NullPointerException.class,
                () -> new OptRequest.Builder()
                        .withOptType(OptType.OUT)
                        .withOptValue(Boolean.TRUE)
                        .withGroupNameRoot("some:root")
                        .build());
        expectedMessage = "username cannot be null.";
        actualMessage = exception.getMessage();
        assertThat(actualMessage, equalTo(expectedMessage));

        exception = assertThrows(NullPointerException.class,
                () -> new OptRequest.Builder()
                        .withOptType(OptType.OUT)
                        .withOptValue(Boolean.TRUE)
                        .withGroupNameRoot("some:root")
                        .withUsername("hansolo")
                        .build());
        expectedMessage = "privilege cannot be null.";
        actualMessage = exception.getMessage();
        assertThat(actualMessage, equalTo(expectedMessage));

        /*
        exception = assertThrows(IllegalArgumentException.class,
                () -> new OptRequest.Builder()
                        .withOptType(OptType.OUT)
                        .withOptValue(Boolean.TRUE)
                        .withGroupNameRoot("some:root")
                        .withUsername("hansolo")
                        .withPrivilege(PrivilegeType.find("no-way"))
                        .build());
        expectedMessage = "Invalid PrivilegeType: UNDEFINED";
        actualMessage = exception.getMessage();
        assertThat(actualMessage, equalTo(expectedMessage));
         */

        OptRequest optRequest = new OptRequest.Builder()
                .withOptType(OptType.OUT)
                .withOptValue(Boolean.TRUE)
                .withGroupNameRoot("some:root")
                .withUsername("hansolo")
                .withPrivilege(PrivilegeType.IN)
                .build();
        assertThat(optRequest, notNullValue());

        optRequest = new OptRequest.Builder()
                .withOptType(OptType.OUT)
                .withOptValue(Boolean.TRUE)
                .withGroupNameRoot("some:root")
                .withUsername("hansolo")
                .withPrivilege(PrivilegeType.OUT)
                .build();
        assertThat(optRequest, notNullValue());

        //        OptRequest optRequest = new OptRequest.Builder()
        //                .withOptType(OptType.OUT)
        //                .withOptValue(Boolean.TRUE)
        //                .withGroupNameRoot("some:root")
        //                .build();
        //        assertThat(optRequest, notNullValue());

        //        try {
        //            new OptRequest.Builder()
        //                    .withOptType(OptType.OUT)
        //                    .withOptValue(Boolean.TRUE)
        //                    .withGroupNameRoot("some:root")
        //                    .withUsername("hansolo")
        //                    .build();
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }

    }

    @Test
    public void testHashCode() {
        OptRequest optRequestOne = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withGroupNameRoot("some-path")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("username")
                .build();
        assertEquals(optRequestOne.hashCode(), optRequestOne.hashCode());

        OptRequest optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withGroupNameRoot("some-path")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("username")
                .build();
        assertEquals(optRequestTwo.hashCode(), optRequestTwo.hashCode());

        assertEquals(optRequestOne.hashCode(), optRequestTwo.hashCode());
    }

    @Test
    public void testToEquals() {
        OptRequest optRequestOne = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withGroupNameRoot("some-path")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("username")
                .build();
        assertEquals(optRequestOne, optRequestOne);
        assertTrue(optRequestOne.equals(optRequestOne));

        OptRequest optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withGroupNameRoot("some-path")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("username")
                .build();
        assertEquals(optRequestTwo, optRequestTwo);
        assertTrue(optRequestTwo.equals(optRequestTwo));

        assertEquals(optRequestOne, optRequestTwo);
        assertTrue(optRequestOne.equals(optRequestTwo));

        // Some falses.
        optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.OUT)
                .withOptValue(true)
                .withGroupNameRoot("some-path")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("username")
                .build();
        assertThat(optRequestOne, not(equalTo(optRequestTwo)));
        assertFalse(optRequestOne.equals(optRequestTwo));
        assertThat(optRequestOne.hashCode(), not(equalTo(optRequestTwo.hashCode())));

        optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(false)
                .withGroupNameRoot("some-path")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("username")
                .build();
        assertThat(optRequestOne, not(equalTo(optRequestTwo)));
        assertFalse(optRequestOne.equals(optRequestTwo));
        assertThat(optRequestOne.hashCode(), not(equalTo(optRequestTwo.hashCode())));

        optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withGroupNameRoot("some-pathX")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("username")
                .build();
        assertThat(optRequestOne, not(equalTo(optRequestTwo)));
        assertFalse(optRequestOne.equals(optRequestTwo));
        assertThat(optRequestOne.hashCode(), not(equalTo(optRequestTwo.hashCode())));

        optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withGroupNameRoot("some-path")
                .withPrivilege(PrivilegeType.OUT)
                .withUsername("username")
                .build();
        assertThat(optRequestOne, not(equalTo(optRequestTwo)));
        assertFalse(optRequestOne.equals(optRequestTwo));
        assertThat(optRequestOne.hashCode(), not(equalTo(optRequestTwo.hashCode())));

        optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withGroupNameRoot("some-path")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("usernameX")
                .build();
        assertThat(optRequestOne, not(equalTo(optRequestTwo)));
        assertFalse(optRequestOne.equals(optRequestTwo));
        assertThat(optRequestOne.hashCode(), not(equalTo(optRequestTwo.hashCode())));

        // Misc odd ball falses.
        assertFalse(optRequestOne.equals(null));
        assertFalse(optRequestOne.equals("no-way-jose"));
    }
}
