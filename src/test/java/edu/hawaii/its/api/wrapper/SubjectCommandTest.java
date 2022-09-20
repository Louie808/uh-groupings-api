package edu.hawaii.its.api.wrapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.hawaii.its.api.configuration.SpringBootWebApplication;
import edu.hawaii.its.api.gc.command.SubjectCommand;
import edu.hawaii.its.api.gc.result.ResultCode;
import edu.hawaii.its.api.gc.result.SubjectResult;

@SpringBootTest(classes = { SpringBootWebApplication.class })
public class SubjectCommandTest {

    @Test
    public void constructor() {
        SubjectCommand command = new SubjectCommand("12345678");
        assertNotNull(command);
        command = new SubjectCommand(null);
        assertNotNull(command);

    }

    @Test
    public void executeTest() {
        SubjectCommand command = new SubjectCommand("12345678");
        SubjectResult result = command.execute();

        System.out.println(" >>>>  result: " + result.getResultCode());

        assertThat(result.getResultCode(),
                equalTo(ResultCode.SUBJECT_NOT_FOUND.value()));

        command = new SubjectCommand("bogus-subject");
        result = command.execute();

        assertThat(result.getResultCode(),
                equalTo(ResultCode.SUBJECT_NOT_FOUND.value()));
    }

}
