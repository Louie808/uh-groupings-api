package edu.hawaii.its.api.gc.command;

import java.util.List;

import edu.hawaii.its.api.exception.AddMemberRequestRejectedException;
import edu.hawaii.its.api.gc.result.AddMembersResults;
import edu.internet2.middleware.grouperClient.api.GcAddMember;
import edu.internet2.middleware.grouperClient.ws.beans.WsAddMemberResults;

public class AddMembersCommand implements Command<AddMembersResults> {

    private final GcAddMember gcAddMember;

    public AddMembersCommand(String groupPath, List<String> uhIdentifiers) {
        gcAddMember = new GcAddMember();
        for (String uhIdentifier : uhIdentifiers) {
            addUhIdentifier(uhIdentifier);
        }
        includeUhMemberDetails(true);
        assignGroupPath(groupPath);
    }

    @Override
    public AddMembersResults execute() {
        AddMembersResults addMembersResults;
        try {
            WsAddMemberResults wsAddMemberResults = gcAddMember.execute();
            addMembersResults = new AddMembersResults(wsAddMemberResults);
        } catch (RuntimeException e) {
            throw new AddMemberRequestRejectedException(e);
        }
        return addMembersResults;
    }

    private AddMembersCommand addUhIdentifier(String uhIdentifier) {
        if (isUhUuid(uhIdentifier)) {
            addUhUuid(uhIdentifier);
            includeUhMemberDetails(true);
        } else {
            addUid(uhIdentifier);
        }
        return this;
    }

    private AddMembersCommand addUhUuid(String uhUuid) {
        gcAddMember.addSubjectId(uhUuid);
        return this;
    }

    private AddMembersCommand addUid(String uid) {
        gcAddMember.addSubjectIdentifier(uid);
        return this;
    }

    private AddMembersCommand assignGroupPath(String groupPath) {
        gcAddMember.assignGroupName(groupPath);
        return this;
    }

    private AddMembersCommand includeUhMemberDetails(boolean includeDetails) {
        gcAddMember.assignIncludeSubjectDetail(includeDetails);
        return this;
    }

    private boolean isUhUuid(String naming) {
        return naming != null && naming.matches("\\d+");
    }

}
