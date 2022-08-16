package edu.hawaii.its.api.type;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class PrivilegeTypeTest {

    @Test
    public void privilegeTypeIn() {
        assertThat(PrivilegeType.IN, equalTo(PrivilegeType.IN));
        assertThat(PrivilegeType.IN.value(), equalTo("optin"));
    }

    @Test
    public void privilegeTypeOut() {
        assertThat(PrivilegeType.OUT, equalTo(PrivilegeType.OUT));
        assertThat(PrivilegeType.OUT.value(), equalTo("optout"));
    }
}
