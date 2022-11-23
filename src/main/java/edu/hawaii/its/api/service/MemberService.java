package edu.hawaii.its.api.service;

import edu.hawaii.its.api.wrapper.HasMemberCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("memberService")

/**
 * MemberService checks if a UH affiliate is a member of a group or grouping.
 */
public class MemberService {
    @Value("${groupings.api.grouping_admins}")
    private String GROUPING_ADMINS;

    @Autowired
    private SubjectService subjectService;

    public boolean isAdmin(String uhIdentifier) {
        String validUhUuid = subjectService.getValidUhUuid(uhIdentifier);
        HasMemberCommand hasMemberCommand = new HasMemberCommand(GROUPING_ADMINS, validUhUuid);
        return hasMemberCommand.execute().getResultCode().equals("IS_MEMBER");
    }
   // public GroupingHasMemberResult isAdminResult(String uhIdentifier);

    public boolean isMember(String groupPath, String uhIdentifier) {
        String validUhUuid = subjectService.getValidUhUuid(uhIdentifier);
        HasMemberCommand hasMemberCommand = new HasMemberCommand(groupPath, validUhUuid);
        return hasMemberCommand.execute().getResultCode().equals("IS_MEMBER");
    }
    // public GroupingHasMemberResult isMemberResult(String uhIdentifier);

    public boolean isIncludeMember(String groupingPath, String uhIdentifier) {
        String validUhUuid = subjectService.getValidUhUuid(uhIdentifier);
        return isMember(groupingPath + ":include", validUhUuid);
    }
    // public GroupingHasMemberResult isIncludeMemberResult(String uhIdentifier);

    public boolean isExcludeMember(String groupingPath, String uhIdentifier) {
        String validUhUuid = subjectService.getValidUhUuid(uhIdentifier);
        return isMember(groupingPath + ":exclude", validUhUuid);
    }
    // public GroupingHasMemberResult isExcludeMemberResult(String uhIdentifier);

    public boolean isOwner(String groupingPath, String uhIdentifier) {
        String validUhUuid = subjectService.getValidUhUuid(uhIdentifier);
        return isMember(groupingPath + ":owners", validUhUuid);
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
