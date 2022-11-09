package edu.hawaii.its.api.wrapper;

import org.junit.jupiter.api.Test;
import edu.hawaii.its.api.configuration.SpringBootWebApplication;
import edu.hawaii.its.api.util.JsonUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("integrationTest")
@SpringBootTest(classes = { SpringBootWebApplication.class })
public class TestHasMemberCommand {

    @Value("${groupings.api.test.grouping_many}")
    private String GROUPING;

    @Value("${groupings.api.test.grouping_many_basis}")
    private String GROUPING_BASIS;

    @Value("${groupings.api.test.grouping_many_include}")
    private String GROUPING_INCLUDE;

    @Value("${groupings.api.test.grouping_many_exclude}")
    private String GROUPING_EXCLUDE;

    @Value("${groupings.api.test.grouping_many_owners}")
    private String GROUPING_OWNERS;

    @Value("${groupings.api.grouping_admins}")
    private String GROUPING_ADMINS;

    @Value("${groupings.api.test.uh-usernames}")
    private List<String> TEST_USERNAMES;

    @Value("${groupings.api.test.uh-numbers}")
    private List<String> TEST_UH_NUMBERS;

    @Test
    public void constructorTest() {
        HasMemberCommand hasMemberCommand = new HasMemberCommand(GROUPING, TEST_USERNAMES.get(0));
        assertNotNull(hasMemberCommand);
    }

    @Test
    public void executeTest() {
//        HasMemberResult hasMemberResult = new HasMemberCommand(GROUPING_OWNERS, "testiwta").execute();
//        assertNotNull(hasMemberResult);
//        assertEquals("testiwta", hasMemberResult.getUid());
//        assertEquals("IS_NOT_MEMBER", hasMemberResult.getResultCode());
//        assertEquals("Testf-iwt-a TestIAM-staff", hasMemberResult.getName());
//        assertEquals("99997010", hasMemberResult.getUhUuid());
//        JsonUtil.printJson(hasMemberResult);

        HasMemberResult hasMemberResult = new HasMemberCommand(GROUPING_OWNERS, TEST_USERNAMES.get(0)).execute();
        assertNotNull(hasMemberResult);
        assertEquals(TEST_USERNAMES.get(0), hasMemberResult.getUid());
        assertEquals("IS_NOT_MEMBER", hasMemberResult.getResultCode());
        assertEquals("Testf-iwt-a TestIAM-staff", hasMemberResult.getName());
        assertEquals(TEST_UH_NUMBERS.get(0), hasMemberResult.getUhUuid());
        JsonUtil.printJson(hasMemberResult);
    }
}
