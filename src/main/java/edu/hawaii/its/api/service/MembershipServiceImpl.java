package edu.hawaii.its.api.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import edu.hawaii.its.api.exception.AccessDeniedException;
import edu.hawaii.its.api.exception.AddMemberRequestRejectedException;
import edu.hawaii.its.api.exception.RemoveMemberRequestRejectedException;
import edu.hawaii.its.api.type.AddMemberResult;
import edu.hawaii.its.api.type.GroupType;
import edu.hawaii.its.api.type.Membership;
import edu.hawaii.its.api.type.OptType;
import edu.hawaii.its.api.type.RemoveMemberResult;
import edu.hawaii.its.api.type.UpdateTimestampResult;
import edu.hawaii.its.api.util.Dates;
import edu.hawaii.its.api.wrapper.AddMemberResponse;
import edu.hawaii.its.api.wrapper.RemoveMemberResponse;

import edu.internet2.middleware.grouperClient.ws.GcWebServiceError;
import edu.internet2.middleware.grouperClient.ws.beans.WsAttributeAssignValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("membershipService")
public class MembershipServiceImpl implements MembershipService {

    @Value("${groupings.api.grouping_admins}")
    private String GROUPING_ADMINS;

    @Value("${groupings.api.yyyymmddThhmm}")
    private String YYYYMMDDTHHMM;

    @Value("${groupings.api.assign_type_group}")
    private String ASSIGN_TYPE_GROUP;

    @Value("${groupings.api.operation_assign_attribute}")
    private String OPERATION_ASSIGN_ATTRIBUTE;

    @Value("${groupings.api.operation_replace_values}")
    private String OPERATION_REPLACE_VALUES;

    @Value("${groupings.api.success}")
    private String SUCCESS;

    @Value("${groupings.api.failure}")
    private String FAILURE;

    @Autowired
    private GroupingAssignmentService groupingAssignmentService;

    @Autowired
    private MemberAttributeService memberAttributeService;

    @Autowired
    private GrouperApiService grouperApiService;

    public static final Log logger = LogFactory.getLog(MembershipServiceImpl.class);

    private String BASIS = GroupType.BASIS.value();
    private String INCLUDE = GroupType.INCLUDE.value();
    private String EXCLUDE = GroupType.EXCLUDE.value();
    private String OWNERS = GroupType.OWNERS.value();

    /**
     * Get a list of memberships pertaining to uid.
     */
    @Override
    public List<Membership> membershipResults(String currentUser, String uid) {
        String action = "getMembershipResults; currentUser: " + currentUser + "; uid: " + uid + ";";
        logger.info(action);

        if (!memberAttributeService.isAdmin(currentUser) && !currentUser.equals(uid)) {
            throw new AccessDeniedException();
        }
        List<Membership> memberships = new ArrayList<>();
        List<String> groupPaths;
        List<String> optOutList;
        try {
            groupPaths = groupingAssignmentService.getGroupPaths(currentUser, uid);
            optOutList = groupingAssignmentService.optableGroupings(OptType.OUT.value());
        } catch (GcWebServiceError e) {
            return memberships;
        }
        Map<String, List<String>> pathMap = new HashMap<>();
        for (String pathToCheck : groupPaths) {
            if (!pathToCheck.endsWith(INCLUDE)
                    && !pathToCheck.endsWith(EXCLUDE)
                    && !pathToCheck.endsWith(BASIS)
                    && !pathToCheck.endsWith(OWNERS)) {
                continue;
            }
            String parentPath = groupingAssignmentService.parentGroupingPath(pathToCheck);
            if (!pathMap.containsKey(parentPath)) {
                pathMap.put(parentPath, new ArrayList<>());
            }
            pathMap.get(parentPath).add(pathToCheck);
        }

        for (Map.Entry<String, List<String>> entry : pathMap.entrySet()) {
            String groupingPath = entry.getKey();
            List<String> paths = entry.getValue();
            Membership membership = new Membership();
            for (String path : paths) {
                if (path.endsWith(BASIS)) {
                    membership.setInBasis(true);
                }
                if (path.endsWith(INCLUDE)) {
                    membership.setInInclude(true);
                }
                if (path.endsWith(EXCLUDE)) {
                    membership.setInExclude(true);
                }
                if (path.endsWith(OWNERS)) {
                    membership.setInOwner(true);
                }
            }
            membership.setPath(groupingPath);
            membership.setOptOutEnabled(optOutList.contains(groupingPath));
            membership.setName(groupingAssignmentService.nameGroupingPath(groupingPath));
            membership.setDescription(grouperApiService.descriptionOf(groupingPath));
            memberships.add(membership);
        }
        return memberships;
    }

    public void checkPrivileges(String function, String currentUser, String groupPath) {
        // checks if user has privileges for add/remove Ownerships and add/remove include/exclude
        // has to be either an owner of the group path or an admin or both to have sufficient privileges
        if (!function.toUpperCase().equals("ADMIN")) {
            if (!memberAttributeService.isOwner(groupPath, currentUser) && !memberAttributeService.isAdmin(
                    currentUser)) {
                throw new AccessDeniedException();
            }
        // checks if user has privileges for add/remove admin
        // has to just be an admin
        } else if (function.toUpperCase().equals("ADMIN")) {
            if (!memberAttributeService.isAdmin(currentUser)) {
                throw new AccessDeniedException();
            }
        }
    }

    /**
     * Add all uids/uhUuids contained in list usersToAdd to the group at groupPath. When adding to the include group
     * members which already exist in the exclude group will be removed from the exclude group and visa-versa. This
     * method was designed to add new members to the include and exclude groups only. Upon passing group paths other than
     * include or exclude, addGroupMembers will return empty list.
     */
    @Override
    public List<AddMemberResult> addMembers(String function, String currentUser, String groupPath, List<String> usersToAdd) {
        logger.info("addMembers; function: " + function + "; currentUser: " + currentUser + "; groupPath: " + groupPath + ";"
                + "usersToAdd: " + usersToAdd + ";");

        checkPrivileges(function, currentUser, groupPath);

        //In original function but variable is never called
        //WsSubjectLookup wsSubjectLookup = grouperApiService.subjectLookup(ownerUsername);

        List<AddMemberResult> addMemberResults = new ArrayList<>();
        String removalPath = groupingAssignmentService.parentGroupingPath(groupPath);

        switch (function.toUpperCase()) {
            case "INCLUDE":
                groupPath = groupPath + INCLUDE;
                removalPath += EXCLUDE;
                break;
            case "EXCLUDE":
                groupPath = groupPath + EXCLUDE;
                removalPath += INCLUDE;
                break;
            case "OWNERS":
                groupPath = groupPath + OWNERS;
                break;
            case "ADMIN":
                //Empty for else statement
                break;
            default:
                throw new GcWebServiceError("404: Invalid group path.");
        }

        for (String userToAdd : usersToAdd) {
            AddMemberResult addMemberResult;
            AddMemberResponse addMemberResponse;

            switch (function.toUpperCase()) {
                case "OWNER":
                    addMemberResponse = grouperApiService.addMember(groupPath, userToAdd);
                    addMemberResult = new AddMemberResult(addMemberResponse);

                    if (addMemberResult.isUserWasAdded()) {
                        updateLastModified(groupPath);
                        updateLastModified(groupPath);
                    }
                    addMemberResults.add(addMemberResult);
                    break;
                case "ADMIN":
                    addMemberResponse = grouperApiService.addMember(GROUPING_ADMINS, userToAdd);
                    addMemberResult = new AddMemberResult(addMemberResponse);
                    addMemberResults.add(addMemberResult);
                    break;
                case "INCLUDE":
                case "EXCLUDE":
                    addMemberResults.add(addMember(currentUser, userToAdd, removalPath, groupPath));
                    break;
            }

        }
        return addMemberResults;
    }
            /*
        if (usersToAdd.size() > 100) {
            groupingsMailService
                    .setJavaMailSender(javaMailSender)
                    .setFrom("no-reply@its.hawaii.edu");
            groupingsMailService.sendCSVMessage(
                    "no-reply@its.hawaii.edu",
                    groupingsMailService.getUserEmail(currentUser),
                    "Groupings: Add " + groupPath,
                    "",
                    "UH-Groupings-Report-" + LocalDateTime.now().toString() + ".csv", addMemberResults);
        }
         */

    /**
     * Remove all the members in list usersToRemove from group at groupPath. This method was designed to remove members
     * from the include and exclude groups only. Passing in other group paths will result in undefined behavior.
     */
    @Override
    public List<RemoveMemberResult> removeMembers(String function, String currentUser, String groupPath,
            List<String> usersToRemove) {
        logger.info("removeGroupMembers; currentUser: " + currentUser + "; groupPath: " + groupPath + ";"
                + "usersToRemove: " + usersToRemove + ";");

        checkPrivileges(function, currentUser, groupPath);

        List<RemoveMemberResult> removeMemberResults = new ArrayList<>();

        switch (function.toUpperCase()) {
            case "INCLUDE":
                groupPath = groupPath + INCLUDE;
                break;
            case "EXCLUDE":
                groupPath = groupPath + EXCLUDE;
                break;
            case "OWNERS":
                groupPath = groupPath + OWNERS;
                break;
            case "ADMIN":
                //Empty for else statement
                break;
            default:
                throw new GcWebServiceError("404: Invalid group path.");
        }


        for (String userToRemove : usersToRemove) {
            RemoveMemberResult removeMemberResult;
            RemoveMemberResponse removeMemberResponse;

            switch (function.toUpperCase()) {
                case "OWNER":
                    removeMemberResponse =
                            grouperApiService.removeMember(groupPath + OWNERS, userToRemove);
                    removeMemberResult = new RemoveMemberResult(removeMemberResponse);
                    if (removeMemberResult.isUserWasRemoved()) {
                        updateLastModified(groupPath);
                        updateLastModified(groupPath + OWNERS);
                    }
                    removeMemberResults.add(removeMemberResult);
                    break;
                case "ADMIN":
                    removeMemberResponse = grouperApiService.removeMember(GROUPING_ADMINS, userToRemove);
                    removeMemberResult = new RemoveMemberResult(removeMemberResponse);
                    removeMemberResults.add(removeMemberResult);
                    break;
                case "INCLUDE":
                case "EXCLUDE":
                    removeMemberResponse = grouperApiService.removeMember(groupPath, userToRemove);
                    removeMemberResult = new RemoveMemberResult(removeMemberResponse);
                    if (removeMemberResult.isUserWasRemoved()) {
                        updateLastModified(groupPath);
                    }
                    removeMemberResults.add(removeMemberResult);
                    logger.info("removeGroupMembers; " + removeMemberResult.toString());
                    break;
            }
    }
        return removeMemberResults;
    }






    /**
     * Check if the currentUser has the proper privileges to opt, then call addGroupMembers. Opting in adds a member/user at
     * uid to the include list and removes them from the exclude list.
     */
    @Override public AddMemberResult optIn(String currentUser, String groupingPath, String uid) {
        logger.info("optIn; currentUser: " + currentUser + "; groupingPath: " + groupingPath + "; uid: " + uid + ";");
        if (!currentUser.equals(uid) && !memberAttributeService.isAdmin(currentUser)) {
            throw new AccessDeniedException();
        }

        String removalPath = groupingPath + GroupType.EXCLUDE.value();
        String additionPath = groupingPath + GroupType.INCLUDE.value();
        return addMember(currentUser, uid, removalPath, additionPath);
    }

    /**
     * Check if the currentUser has the proper privileges to opt, then call addGroupMembers. Opting out adds a member/user
     * at uid to the exclude list and removes them from the include list.
     */
    @Override public AddMemberResult optOut(String currentUser, String groupingPath, String uid) {
        logger.info("optOut; currentUser: " + currentUser + "; groupingPath: " + groupingPath + "; uid: " + uid + ";");
        if (!currentUser.equals(uid) && !memberAttributeService.isAdmin(currentUser)) {
            throw new AccessDeniedException();
        }

        String removalPath = groupingPath + GroupType.INCLUDE.value();
        String additionPath = groupingPath + GroupType.EXCLUDE.value();
        return addMember(currentUser, uid, removalPath, additionPath);
    }

    /**
     * Remove a user from all groups listed in groupPaths
     */
    @Override
    public List<RemoveMemberResult> removeFromGroups(String adminUsername, String userToRemove,
            List<String> groupPaths) {
        if (!memberAttributeService.isAdmin(adminUsername)) {
            throw new AccessDeniedException();
        }
        List<RemoveMemberResult> results = new ArrayList<>();
        for (String groupPath : groupPaths) {
            if (!groupPath.endsWith(GroupType.OWNERS.value())
                    && !groupPath.endsWith(GroupType.INCLUDE.value())
                    && !groupPath.endsWith(GroupType.EXCLUDE.value())) {
                throw new GcWebServiceError("404: Invalid group path.");
            }
            results.add(new RemoveMemberResult(grouperApiService.removeMember(groupPath, userToRemove)));
        }
        return results;
    }

    /**
     * Remove all uses from the include and/or exclude groups of grouping at path.
     */
    @Override
    public List<RemoveMemberResult> resetGroup(String currentUser, String path, List<String> uhNumbersInclude,
            List<String> uhNumbersExclude) {
        if (!memberAttributeService.isAdmin(currentUser) && !memberAttributeService.isOwner(path, currentUser)) {
            throw new AccessDeniedException();
        }
        List<RemoveMemberResult> results = new ArrayList<>();
        if (!uhNumbersInclude.isEmpty()) {
            results.addAll(removeMembers("INCLUDE", currentUser, path + GroupType.INCLUDE.value(), uhNumbersInclude));
        }
        if (!uhNumbersExclude.isEmpty()) {
            results.addAll(removeMembers("EXCLUDE", currentUser, path + GroupType.EXCLUDE.value(), uhNumbersExclude));
        }
        return results;
    }

    /**
     * Update the last modified attribute of a group to the current date and time.
     */
    @Override
    public UpdateTimestampResult updateLastModified(String groupPath) {
        logger.info("updateLastModified; group: " + groupPath + ";");
        String dateTime = Dates.formatDate(LocalDateTime.now(), "yyyyMMdd'T'HHmm");
        return updateLastModifiedTimestamp(dateTime, groupPath);
    }

    /**
     * Update the last modified attribute of a group to dateTime.
     */
    @Override
    public UpdateTimestampResult updateLastModifiedTimestamp(String dateTime, String groupPath) {
        WsAttributeAssignValue wsAttributeAssignValue = grouperApiService.assignAttributeValue(dateTime);
        return new UpdateTimestampResult(grouperApiService.assignAttributesResults(
                ASSIGN_TYPE_GROUP,
                OPERATION_ASSIGN_ATTRIBUTE,
                groupPath,
                YYYYMMDDTHHMM,
                OPERATION_REPLACE_VALUES,
                wsAttributeAssignValue));
    }

    /**
     * Get the number of memberships.
     */
    @Override
    public Integer getNumberOfMemberships(String currentUser, String uid) {
        return membershipResults(currentUser, uid).size();
    }

    /**
     * Adds the uid/uhUuid in userToAdd to the group at additionPath and removes userToAdd from the group at
     * removalPath. If the userToAdd is already in the group at additionPath, it does not get added.
     */
    @Override
    public AddMemberResult addMember(String currentUser, String userToAdd, String removalPath, String additionPath) {
        AddMemberResult addMemberResult;
        try {
            RemoveMemberResponse removeMemberResponse = grouperApiService.removeMember(removalPath, userToAdd);
            AddMemberResponse addMemberResponse = grouperApiService.addMember(additionPath, userToAdd);
            addMemberResult = new AddMemberResult(addMemberResponse, removeMemberResponse);
            if (addMemberResult.isUserWasAdded()) {
                updateLastModified(additionPath);
            }
            if (addMemberResult.isUserWasRemoved()) {
                updateLastModified(removalPath);
            }
        } catch (AddMemberRequestRejectedException | RemoveMemberRequestRejectedException e) {
            addMemberResult = new AddMemberResult(userToAdd, FAILURE);
        }
        logger.info("addGroupMembers; " + addMemberResult);
        return addMemberResult;
    }
}