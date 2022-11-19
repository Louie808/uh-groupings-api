package edu.hawaii.its.api.service;

import edu.hawaii.its.api.type.GroupType;
import edu.hawaii.its.api.wrapper.HasMemberCommand;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("memberService")

/**
 * MemberService checks if a UH affiliate is a member of a group or grouping.
 */
public class MemberService {
    @Value("${groupings.api.grouping_admins}")
    private String GROUPING_ADMINS;

    public boolean isAdmin(String uhIdentifier) {
        HasMemberCommand hasMemberCommand = new HasMemberCommand(GROUPING_ADMINS, uhIdentifier);
        return hasMemberCommand.execute().getResultCode().equals("IS_MEMBER");
    }
   // public GroupingHasMemberResult isAdminResult(String uhIdentifier);

    public boolean isMember(String groupPath, String uhIdentifier) {
        HasMemberCommand hasMemberCommand = new HasMemberCommand(groupPath, uhIdentifier);
        return hasMemberCommand.execute().getResultCode().equals("IS_MEMBER");
    }
    // public GroupingHasMemberResult isMemberResult(String uhIdentifier);

    public boolean isIncludeMember(String groupingPath, String uhIdentifier) {
        return isMember(groupingPath + ":include", uhIdentifier);
    }
    // public GroupingHasMemberResult isIncludeMemberResult(String uhIdentifier);

    public boolean isExcludeMember(String groupingPath, String uhIdentifier) {
        return isMember(groupingPath + ":exclude", uhIdentifier);
    }
    // public GroupingHasMemberResult isExcludeMemberResult(String uhIdentifier);

    public boolean isOwner(String groupingPath, String uhIdentifier) {
        return isMember(groupingPath + ":owners", uhIdentifier);
    }
    // public GroupingHasMemberResult isOwnerResult(String uhIdentifier);
    /*
    public GroupingHasMembersResult hasAdmins(List<String> uhIdentifiers);
    public GroupingHasMembersResult hasOwners(String groupingPath, List<String> uhIdentifiers);
    public GroupingHasMembersResult hasMembers(String groupPath, List<String> uhIdentifiers);
    public GroupingHasMembersResult hasIncludeMembers(String groupPath, List<String> uhIdentifiers);
    public GroupingHasMembersResult hasExcludeMembers(String groupPath, List<String> uhIdentifiers);
     */
    // Todo: GroupingHasMemberResult and GroupingsHasMembersResult still need to be implemented.
    /////// Look at GroupingsAddResult and GroupingsAddResults as an example.
    /////// GroupingHasMemberResult and GroupingsHasMembersResult will allow data results about members to be
    /////// Returned to the UI>
}
