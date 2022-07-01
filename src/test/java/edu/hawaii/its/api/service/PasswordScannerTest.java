package edu.hawaii.its.api.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import edu.hawaii.its.api.configuration.SpringBootWebApplication;
import edu.hawaii.its.api.exception.PasswordFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootWebApplication.class })
public class PasswordScannerTest {

    @Autowired
    private PasswordScanner passwordScanner;

    @Test
    public void construction() {
        assertNotNull(passwordScanner);
    }

    @Test
    public void testTwoPatternDiffFile() {
        String dirname = "src/test/resources/pattern-property-checker/test2";
        passwordScanner.setDirname(dirname);
        assertThrows(PasswordFoundException.class,
                () -> passwordScanner.init());
    }

}
