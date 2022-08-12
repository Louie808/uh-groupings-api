package edu.hawaii.its.api.type;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OptRequestTest {

    @Test
    public void optTypeInIn() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("jordanw4")
                .withPath("tmp:jordanw4:jordanw4-aux")
                .withPrivilege(PrivilegeType.IN)
                .withOptType(OptType.IN)
                .withOptValue(false)
                .build();

        assertEquals(optRequest, optRequest);
        assertEquals(optRequest.getOptId(), OptType.IN.value());
        assertThat(optRequest.getOptId(), equalTo(OptType.IN.value()));
        //        assertThat(optRequest.getPath(), equalTo("tmp:jordanw4:jordanw4-aux"));
        assertThat(optRequest.getGroupType(), notNullValue());
        assertThat(optRequest.getGroupType(), equalTo(GroupType.INCLUDE));
    }

    @Test
    public void optTypeInOut() {
        OptRequest optRequest = new OptRequest.Builder()
                .withUsername("jordanw4")
                .withPath("tmp:jordanw4:jordanw4-aux")
                .withPrivilege(PrivilegeType.IN)
                .withOptType(OptType.OUT)
                .withOptValue(false)
                .build();

        assertEquals(optRequest, optRequest);
        assertEquals(optRequest.getOptId(), OptType.OUT.value());
        assertThat(optRequest.getOptId(), equalTo(OptType.OUT.value()));
        //        assertThat(optRequest.getPath(), equalTo("tmp:jordanw4:jordanw4-aux"));
        assertThat(optRequest.getOptValue(), notNullValue());
        assertThat(optRequest.getPrivilege(), notNullValue());
        assertThat(optRequest.getUsername(), notNullValue());
        assertThat(optRequest.getGroupType(), notNullValue());
        assertThat(optRequest.getGroupType(), equalTo(GroupType.INCLUDE));
    }

    @Test
    public void testHashCode() {
        OptRequest optRequestOne = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withPath("some-path")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("username")
                .build();
        assertEquals(optRequestOne.hashCode(), optRequestOne.hashCode());

        OptRequest optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withPath("some-path")
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
                .withPath("some-path")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("username")
                .build();
        assertEquals(optRequestOne, optRequestOne);
        assertTrue(optRequestOne.equals(optRequestOne));

        OptRequest optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withPath("some-path")
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
                .withPath("some-path")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("username")
                .build();
        assertThat(optRequestOne, not(equalTo(optRequestTwo)));
        assertFalse(optRequestOne.equals(optRequestTwo));
        assertThat(optRequestOne.hashCode(), not(equalTo(optRequestTwo.hashCode())));

        optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(false)
                .withPath("some-path")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("username")
                .build();
        assertThat(optRequestOne, not(equalTo(optRequestTwo)));
        assertFalse(optRequestOne.equals(optRequestTwo));
        assertThat(optRequestOne.hashCode(), not(equalTo(optRequestTwo.hashCode())));

        optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withPath("some-pathX")
                .withPrivilege(PrivilegeType.IN)
                .withUsername("username")
                .build();
        assertThat(optRequestOne, not(equalTo(optRequestTwo)));
        assertFalse(optRequestOne.equals(optRequestTwo));
        assertThat(optRequestOne.hashCode(), not(equalTo(optRequestTwo.hashCode())));

        optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withPath("some-path")
                .withPrivilege(PrivilegeType.OUT)
                .withUsername("username")
                .build();
        assertThat(optRequestOne, not(equalTo(optRequestTwo)));
        assertFalse(optRequestOne.equals(optRequestTwo));
        assertThat(optRequestOne.hashCode(), not(equalTo(optRequestTwo.hashCode())));

        optRequestTwo = new OptRequest.Builder()
                .withOptType(OptType.IN)
                .withOptValue(true)
                .withPath("some-path")
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
