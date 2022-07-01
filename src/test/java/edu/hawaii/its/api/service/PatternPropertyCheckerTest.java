package edu.hawaii.its.api.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import edu.hawaii.its.api.configuration.SpringBootWebApplication;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootWebApplication.class })
public class PatternPropertyCheckerTest {

    private PatternPropertyChecker patternPropertyChecker;
    private String dirname = "src/test/resources/pattern-property-checker";

    @Before
    public void setUp() {
        patternPropertyChecker = new PatternPropertyChecker();
    }

    @Test
    public void testNullParam() {
        List<String> fileLocations = patternPropertyChecker.getPatternLocation(null, null);
        assertTrue(fileLocations.size() == 0);
    }

    @Test
    public void testNoPattern() {
        List<String> fileLocations = patternPropertyChecker.getPatternLocation(dirname + "/test2", ".txt");
        assertTrue(fileLocations.size() == 0);
    }

    @Test
    public void testNoFile() {
        List<String> fileLocations = patternPropertyChecker.getPatternLocation(dirname, ".properties");
        assertTrue(fileLocations.size() == 0);
    }

    @Test
    public void testEmptyFile() {
        List<String> fileLocations = patternPropertyChecker.getPatternLocation(dirname, ".txt");
        assertTrue(fileLocations.size() == 0);
    }

    @Test
    public void testOnePatternFound() {
        List<String> fileLocations = patternPropertyChecker.getPatternLocation(dirname + "/test1", ".properties");
        assertEquals(1, fileLocations.size());
        assertTrue(fileLocations.contains(dirname + "/test1/PatternPropertyCheckerTestFile.properties on line: 2"));
    }

    // test that it can find two instances of pattern in the same file (looks in test2 folder)
    @Test
    public void testTwoPatternSameFile() {
        List<String> fileLocations = patternPropertyChecker.getPatternLocation(dirname + "/test2", ".properties");
        assertEquals(2, fileLocations.size());
        assertTrue(fileLocations.contains(dirname + "/test2/PatternPropertyCheckerTestFile2.properties on line: 2"));
        assertTrue(fileLocations.contains(dirname + "/test2/PatternPropertyCheckerTestFile2.properties on line: 5"));
    }

    @Test
    public void testTwoPatternDiffFile() {
        List<String> fileLocations = patternPropertyChecker.getPatternLocation(dirname + "/test1", ".txt");
        assertEquals(2, fileLocations.size());
        assertTrue(fileLocations.contains(dirname + "/test1/PatternPropertyCheckerTwoPatterns.txt on line: 1"));
        assertTrue(fileLocations.contains(dirname + "/test1/PatternPropertyCheckerTwoPatterns2.txt on line: 3"));
    }

    @Test
    public void testBadDirectory() {
        List<String> results = patternPropertyChecker.getPatternLocation(null, ".txt");
        assertEquals(0, results.size());

        results = patternPropertyChecker.getPatternLocation("_no_way_", ".txt");
        assertEquals(0, results.size());
    }

    @Test
    public void testBadExtension() {
        List<String> results = patternPropertyChecker.getPatternLocation(dirname + "/test1", null);
        assertEquals(0, results.size());
    }

}
