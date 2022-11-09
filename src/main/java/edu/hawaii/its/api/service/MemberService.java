package edu.hawaii.its.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberService {
    @Value("${groupings.api.grouping_admins}")
    private String GROUPING_ADMINS;

    public boolean isAdmin(String uhIdentifier) {
       return false;
    }

    public boolean isMember(String groupPath, String uhIdentifier) {
        return false;
    }

    public  boolean isIncludeMember(String groupingPath, String uhIdentifier) {
        return false;
    }

    public  boolean isExcludeMember(String groupingPath, String uhIdentifier) {
        return false;
    }

    public boolean isOwner(String groupingPath, String uhIdentifier) {
        return false;
    }


    /*
    public GroupingsHasMemberResults hasAdmins(List<String> uhIdentifiers);
    public GroupingsHasMemberResults hasOwners(String groupingPath, List<String> uhIdentifiers);
    public GroupingsHasMemberResults hasMembers(String groupPath, List<String> uhIdentifiers);
    public GroupingsHasMemberResults hasIncludeMembers(String groupPath, List<String> uhIdentifiers);
    public GroupingsHasMemberResults hasExcludeMembers(String groupPath, List<String> uhIdentifiers);
     */

}
