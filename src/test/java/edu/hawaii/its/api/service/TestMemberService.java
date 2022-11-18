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

    @Value("${groupings.api.test.admin_user}")
    private String ADMIN;

    @Value("${groupings.api.test.uh-usernames}")
    private List<String> TEST_UH_USERNAMES;

    @Value("${groupings.api.test.uh-numbers}")
    private List<String> TEST_UH_NUMBERS;

    @Value("${groupings.api.test.grouping_many}")
    private String GROUPING;

    @Value("${groupings.api.test.grouping_many_basis}")
    private String GROUPING_BASIS;

    @Value("${groupings.api.test.grouping_many_include}")
    private String GROUPING_INCLUDE;

    @Value("${groupings.api.test.grouping_many_exclude}")
    private String GROUPING_EXCLUDE;

    @Value("${groupings.api.grouping_admins}")
    private String GROUPING_ADMINS;

    @Autowired
    MemberService memberService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private MemberAttributeService memberAttributeService;
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
        String uid = TEST_UH_USERNAMES.get(0);
        String uhUuid = TEST_UH_NUMBERS.get(1);

        removeMember(GROUPING_BASIS, uid);
        removeMember(GROUPING_BASIS, uhUuid);
        assertFalse(memberService.isMember(GROUPING, uid));
        assertFalse(memberService.isMember(GROUPING, uhUuid));

        addMember(GROUPING_BASIS, uid);
        addMember(GROUPING_BASIS, uhUuid);
        assertTrue(memberService.isMember(GROUPING, uid));
        assertTrue(memberService.isMember(GROUPING, uhUuid));

        removeMember(GROUPING_BASIS, uid);
        removeMember(GROUPING_BASIS, uhUuid);
        assertFalse(memberService.isMember(GROUPING, uid));
        assertFalse(memberService.isMember(GROUPING, uhUuid));
    }

    @Test
    public void testIsIncludeMember() {
        String uid = TEST_UH_USERNAMES.get(0);
        String uhUuid = TEST_UH_NUMBERS.get(1);

        removeMember(GROUPING_INCLUDE, uid);
        removeMember(GROUPING_INCLUDE, uhUuid);
        assertFalse(memberService.isIncludeMember(GROUPING_INCLUDE, uid));
        assertFalse(memberService.isIncludeMember(GROUPING_INCLUDE, uhUuid));

        addMember(GROUPING_INCLUDE, uid);
        addMember(GROUPING_INCLUDE, uhUuid);
        assertTrue(memberService.isIncludeMember(GROUPING_INCLUDE, uid));
        assertTrue(memberService.isIncludeMember(GROUPING_INCLUDE, uhUuid));

        removeMember(GROUPING_INCLUDE, uid);
        removeMember(GROUPING_INCLUDE, uhUuid);
        assertFalse(memberService.isIncludeMember(GROUPING_INCLUDE, uid));
        assertFalse(memberService.isIncludeMember(GROUPING_INCLUDE, uhUuid));
    }

    @Test
    public void testIsExcludeMember() {
        String uid = TEST_UH_USERNAMES.get(0);
        String uhUuid = TEST_UH_NUMBERS.get(1);

        removeMember(GROUPING_EXCLUDE, uid);
        removeMember(GROUPING_EXCLUDE, uhUuid);
        assertFalse(memberService.isExcludeMember(GROUPING_EXCLUDE, uid));
        assertFalse(memberService.isExcludeMember(GROUPING_EXCLUDE, uhUuid));

        addMember(GROUPING_EXCLUDE, uid);
        addMember(GROUPING_EXCLUDE, uhUuid);
        assertTrue(memberService.isExcludeMember(GROUPING_EXCLUDE, uid));
        assertTrue(memberService.isExcludeMember(GROUPING_EXCLUDE, uhUuid));

        removeMember(GROUPING_EXCLUDE, uid);
        removeMember(GROUPING_EXCLUDE, uhUuid);
        assertFalse(memberService.isExcludeMember(GROUPING_EXCLUDE, uid));
        assertFalse(memberService.isExcludeMember(GROUPING_EXCLUDE, uhUuid));
    }

    @Test
    public void testIsOwner() {
        membershipService.removeOwnerships(GROUPING, ADMIN, TEST_UH_USERNAMES);
        membershipService.removeOwnerships(GROUPING, ADMIN, TEST_UH_NUMBERS);

        TEST_UH_USERNAMES.forEach(testUsername -> {
            assertFalse(memberAttributeService.isOwner(GROUPING, testUsername));
        });
        TEST_UH_NUMBERS.forEach(testUsername -> {
            assertFalse(memberAttributeService.isOwner(GROUPING, testUsername));
        });

        membershipService.addOwnerships(GROUPING, ADMIN, TEST_UH_USERNAMES);
        membershipService.addOwnerships(GROUPING, ADMIN, TEST_UH_NUMBERS);

        TEST_UH_USERNAMES.forEach(testUsername -> {
            assertTrue(memberAttributeService.isOwner(GROUPING, testUsername));
        });
        TEST_UH_NUMBERS.forEach(testUsername -> {
            assertTrue(memberAttributeService.isOwner(GROUPING, testUsername));
        });
        membershipService.removeOwnerships(GROUPING, ADMIN, TEST_UH_USERNAMES);
        membershipService.removeOwnerships(GROUPING, ADMIN, TEST_UH_NUMBERS);
    }

    private void addMember(String groupPath, String uhIdentifier) {
        new AddMemberCommand(groupPath, uhIdentifier).execute();
    }

    private void removeMember(String groupPath, String uhIdentifier) {
        new RemoveMemberCommand(groupPath, uhIdentifier).execute();
    }

}
