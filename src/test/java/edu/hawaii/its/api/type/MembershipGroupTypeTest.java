package edu.hawaii.its.api.type;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class MembershipGroupTypeTest {

    @Test
    public void groupTypeInclude() {
        assertThat(MembershipGroupType.INCLUDE, equalTo(MembershipGroupType.INCLUDE));
        assertThat(MembershipGroupType.INCLUDE.value(), equalTo("INCLUDE"));
    }

    @Test
    public void groupTypeExclude() {
        assertThat(MembershipGroupType.EXCLUDE, equalTo(MembershipGroupType.EXCLUDE));
        assertThat(MembershipGroupType.EXCLUDE.value(), equalTo("EXCLUDE"));
    }

    @Test
    public void groupTypeOwners() {
        assertThat(MembershipGroupType.OWNERS, equalTo(MembershipGroupType.OWNERS));
        assertThat(MembershipGroupType.OWNERS.value(), equalTo("OWNERS"));
    }

    @Test
    public void groupTypeAdmin() {
        assertThat(MembershipGroupType.ADMIN, equalTo(MembershipGroupType.ADMIN));
        assertThat(MembershipGroupType.ADMIN.value(), equalTo("ADMIN"));
    }

    @Test
    public void find() {
        String includeValue = "INCLUDE";
        MembershipGroupType include = MembershipGroupType.find(includeValue);
        assertThat(include, equalTo(MembershipGroupType.INCLUDE));

        String excludeValue = "EXCLUDE";
        MembershipGroupType exclude = MembershipGroupType.find(excludeValue);
        assertThat(exclude, equalTo(MembershipGroupType.EXCLUDE));

        String ownersValue = "OWNERS";
        MembershipGroupType owners = MembershipGroupType.find(ownersValue);
        assertThat(owners, equalTo(MembershipGroupType.OWNERS));

        String adminValue = "ADMIN";
        MembershipGroupType admin = MembershipGroupType.find(adminValue);
        assertThat(admin, equalTo(MembershipGroupType.ADMIN));

        // Undefined find.
        String badValue = "pardon?";
        MembershipGroupType membershipGroupType = MembershipGroupType.find(badValue);
        assertThat(membershipGroupType, nullValue());
    }
}

