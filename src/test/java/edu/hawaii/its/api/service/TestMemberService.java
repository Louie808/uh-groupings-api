package edu.hawaii.its.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import edu.hawaii.its.api.configuration.SpringBootWebApplication;
import edu.hawaii.its.api.wrapper.AddMemberCommand;
import edu.hawaii.its.api.wrapper.RemoveMemberCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("integrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = { SpringBootWebApplication.class })
public class TestMemberService {

    @Value("${groupings.api.test.uh-usernames}")
    private List<String> TEST_UH_USERNAMES;

    @Value("${groupings.api.test.uh-numbers}")
    private List<String> TEST_UH_NUMBERS;

    @Value("${groupings.api.grouping_admins}")
    private String GROUPING_ADMINS;

    @Autowired
    MemberService memberService;
    @Test
    public void testIsAdmin() {
        String uid = TEST_UH_USERNAMES.get(0);
        String uhUuid = TEST_UH_NUMBERS.get(1);

        removeMember(GROUPING_ADMINS, uid);
        removeMember(GROUPING_ADMINS, uhUuid);
        assertFalse(memberService.isAdmin(uid));
        assertFalse(memberService.isAdmin(uhUuid));

        addMember(GROUPING_ADMINS, uid);
        addMember(GROUPING_ADMINS, uhUuid);
        assertTrue(memberService.isAdmin(uid));
        assertTrue(memberService.isAdmin(uhUuid));

        removeMember(GROUPING_ADMINS, uid);
        removeMember(GROUPING_ADMINS, uhUuid);
        assertFalse(memberService.isAdmin(uid));
        assertFalse(memberService.isAdmin(uhUuid));
    }

    @Test
    public void testIsMember() {
    }

    @Test
    public void testIsIncludeMember() {
    }

    @Test
    public void testIsExcludeMember() {
    }

    @Test
    public void testIsOwner() {
    }

    private void addMember(String groupPath, String uhIdentifier) {
        new AddMemberCommand(groupPath, uhIdentifier).execute();
    }

    private void removeMember(String groupPath, String uhIdentifier) {
        new RemoveMemberCommand(groupPath, uhIdentifier).execute();
    }

}
