package edu.hawaii.its.api.gc.command;

import java.util.List;

import edu.hawaii.its.api.exception.AddMemberRequestRejectedException;
import edu.hawaii.its.api.gc.result.RemoveMembersResults;
import edu.internet2.middleware.grouperClient.api.GcDeleteMember;
import edu.internet2.middleware.grouperClient.ws.beans.WsDeleteMemberResults;

public class RemoveMembersCommand implements Command<RemoveMembersResults> {

    private final GcDeleteMember gcDeleteMember;

    // Constructor.
    public RemoveMembersCommand(String groupPath, List<String> uhIdentifiers) {
        gcDeleteMember = new GcDeleteMember();
        for (String uhIdentifier : uhIdentifiers) {
            addUhIdentifier(uhIdentifier);
        }
        includeUhMemberDetails(true);
        assignGroupPath(groupPath);

    }

    @Override
    public RemoveMembersResults execute() {
        RemoveMembersResults removeMembersResults;
        try {
            WsDeleteMemberResults wsDeleteMemberResults = gcDeleteMember.execute();
            removeMembersResults = new RemoveMembersResults(wsDeleteMemberResults);
        } catch (RuntimeException e) {
            throw new AddMemberRequestRejectedException(e);
        }
        return removeMembersResults;
    }

    private RemoveMembersCommand addUhIdentifier(String uhIdentifier) {
        if (isUhUuid(uhIdentifier)) {
            addUhUuid(uhIdentifier);
            includeUhMemberDetails(true);
        } else {
            addUid(uhIdentifier);
        }
        return this;
    }

    private RemoveMembersCommand addUhUuid(String uhUuid) {
        gcDeleteMember.addSubjectId(uhUuid);
        return this;
    }

    private RemoveMembersCommand addUid(String uid) {
        gcDeleteMember.addSubjectIdentifier(uid);
        return this;
    }

    private RemoveMembersCommand assignGroupPath(String groupPath) {
        gcDeleteMember.assignGroupName(groupPath);
        return this;
    }

    private RemoveMembersCommand includeUhMemberDetails(boolean includeDetails) {
        gcDeleteMember.assignIncludeSubjectDetail(includeDetails);
        return this;
    }

    private boolean isUhUuid(String naming) {
        return naming != null && naming.matches("\\d+");
    }

}
