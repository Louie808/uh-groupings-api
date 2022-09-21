package edu.hawaii.its.api.service;

import edu.hawaii.its.api.type.AddMemberResult;
import edu.hawaii.its.api.type.Membership;
import edu.hawaii.its.api.type.RemoveMemberResult;
import edu.hawaii.its.api.type.UpdateTimestampResult;

import java.util.List;

public interface MembershipService {

    List<Membership> membershipResults(String currentUser, String uid);

    List<AddMemberResult> addMembers(String function, String currentUser, String groupPath, List<String> usersToAdd);

    List<RemoveMemberResult> removeMembers(String function, String currentUser, String groupPath, List<String> usersToRemove);

    AddMemberResult optIn(String currentUser, String groupingPath, String uid);

    AddMemberResult optOut(String currentUser, String groupingPath, String uid);

    List<RemoveMemberResult> removeFromGroups(String adminUsername, String userToRemove, List<String> groupPaths);

    List<RemoveMemberResult> resetGroup(String currentUser, String path, List<String> uhNumbersInclude,
            List<String> uhNumbersExclude);

    UpdateTimestampResult updateLastModified(String groupPath);

    UpdateTimestampResult updateLastModifiedTimestamp(String dateTime, String groupPath);

    Integer getNumberOfMemberships(String currentUser, String uid);
    
    AddMemberResult addMember(String currentUser, String userToAdd, String removalPath, String additionPath);
}