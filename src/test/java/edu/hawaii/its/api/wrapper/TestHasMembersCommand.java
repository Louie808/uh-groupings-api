package edu.hawaii.its.api.wrapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import edu.hawaii.its.api.configuration.SpringBootWebApplication;
import edu.hawaii.its.api.exception.AddMemberRequestRejectedException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

@ActiveProfiles("integrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = { SpringBootWebApplication.class })
public class TestHasMembersCommand {
    @Value("${groupings.api.test.uhuuids}")
    private List<String> TEST_UH_NUMBERS;

    @Value("${groupings.api.test.grouping_many_include}")
    private String GROUPING_INCLUDE;

    @Test
    public void executeTest() {
        String[] bogus = { "bogus-1", "bogus-2" };
        try {
            new AddMembersCommand(GROUPING_INCLUDE, Arrays.asList(bogus)).execute();
            fail("A list of all invalid identifiers should throw an exception.");
        } catch (AddMemberRequestRejectedException e) {
            assertNull(e.getCause());
        }

        bogus = new String[] { "bogus-1", TEST_UH_NUMBERS.get(0), "bogus-2" };
        try {
            new AddMembersCommand(GROUPING_INCLUDE, Arrays.asList(bogus)).execute();
            fail("A list of invalid and valid identifiers should throw an exception.");
        } catch (AddMemberRequestRejectedException e) {
            assertNull(e.getCause());
        }

        try {
            new AddMembersCommand("bogus-path", TEST_UH_NUMBERS).execute();
            fail("Passing and invalid group path should throw an exception.");
        } catch (AddMemberRequestRejectedException e) {
            assertNull(e.getCause());
        }

    }
}
