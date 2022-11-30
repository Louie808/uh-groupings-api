package edu.hawaii.its.api.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static edu.hawaii.its.api.service.PathFilter.onlyMembershipPaths;
import static edu.hawaii.its.api.service.PathFilter.pathHasBasis;
import static edu.hawaii.its.api.service.PathFilter.pathHasExclude;
import static edu.hawaii.its.api.service.PathFilter.pathHasInclude;
import static edu.hawaii.its.api.service.PathFilter.pathHasOwner;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathFilterTest {

    private String includePath = "bogus:include";
    private String excludePath = "bogus:exclude";
    private String ownerPath = "bogus:owners";
    private String basisPath = "bogus:basis";

    private List<String> testPathList;

    @Test
    public void testPathHasInclude() {
        testPathList = new ArrayList<>();
        assertEquals(filterPaths(testPathList, pathHasInclude()).size(), 0);
        testPathList.add(includePath);
        testPathList.add(includePath);
        testPathList.add(excludePath);
        testPathList.add(ownerPath);
        testPathList.add(basisPath);
        assertEquals(filterPaths(testPathList, pathHasInclude()).size(), 2);
    }

    @Test
    public void testPathHasExclude() {
        testPathList = new ArrayList<>();
        assertEquals(filterPaths(testPathList, pathHasExclude()).size(), 0);
        testPathList.add(includePath);
        testPathList.add(excludePath);
        testPathList.add(excludePath);
        testPathList.add(ownerPath);
        testPathList.add(basisPath);
        assertEquals(filterPaths(testPathList, pathHasExclude()).size(), 2);
    }

    @Test
    public void testPathHasOwner() {
        testPathList = new ArrayList<>();
        assertEquals(filterPaths(testPathList, pathHasOwner()).size(), 0);
        testPathList.add(includePath);
        testPathList.add(excludePath);
        testPathList.add(ownerPath);
        testPathList.add(ownerPath);
        testPathList.add(basisPath);
        assertEquals(filterPaths(testPathList, pathHasOwner()).size(), 2);
    }

    @Test
    public void testPathHasBasis() {
        testPathList = new ArrayList<>();
        assertEquals(filterPaths(testPathList, pathHasBasis()).size(), 0);
        testPathList.add(includePath);
        testPathList.add(excludePath);
        testPathList.add(ownerPath);
        testPathList.add(basisPath);
        testPathList.add(basisPath);
        assertEquals(filterPaths(testPathList, pathHasBasis()).size(), 2);
    }

    @Test
    public void testOnlyMemberships() {
        testPathList = new ArrayList<>();
        assertEquals(filterPaths(testPathList, onlyMembershipPaths()).size(), 0);
        testPathList.add(includePath);
        testPathList.add(includePath);
        testPathList.add(excludePath);
        testPathList.add(excludePath);
        testPathList.add(ownerPath);
        testPathList.add(ownerPath);
        testPathList.add(basisPath);
        testPathList.add(basisPath);
        assertEquals(filterPaths(testPathList, onlyMembershipPaths()).size(), 4);
    }

    /**
     * General filter to filter paths with the predicates in the PathFilter class.
     */
    private static List<String> filterPaths(List<String> groupPaths, Predicate<String> predicate) {
        return groupPaths.stream().filter(predicate).collect(Collectors.<String>toList());
    }
}
