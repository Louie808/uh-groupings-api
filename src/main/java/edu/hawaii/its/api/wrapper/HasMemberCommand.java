package edu.hawaii.its.api.wrapper;

import edu.internet2.middleware.grouperClient.api.GcHasMember;
import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResults;

public class HasMemberCommand extends GrouperCommand implements Command<HasMemberResults> {
    private GcHasMember gcHasMember;

    public HasMemberCommand(String groupPath, String uhIdentifier) {
        gcHasMember = new GcHasMember();
        gcHasMember.assignIncludeSubjectDetail(true);
        this.assignGroupPath(groupPath)
                .addUhIdentifier(uhIdentifier);
    }

    @Override public HasMemberResults execute() {
        HasMemberResults hasMemberResults;
        try {
            WsHasMemberResults wsHasMemberResults = gcHasMember.execute();
            hasMemberResults = new HasMemberResults(wsHasMemberResults);
        } catch (RuntimeException e) {
            hasMemberResults = new HasMemberResults();
        }
        return hasMemberResults;
    }

    private HasMemberCommand assignGroupPath(String groupPath) {
        gcHasMember.assignGroupName(groupPath);
        return this;
    }

    private HasMemberCommand addUhIdentifier(String uhIdentifier) {
        if (isUhUuid(uhIdentifier)) {
            addUhUuid(uhIdentifier);
        } else {
            addUid(uhIdentifier);
        }
        return this;
    }

    private HasMemberCommand addUhUuid(String uhUuid) {
        gcHasMember.addSubjectId(uhUuid);
        return this;
    }

    private HasMemberCommand addUid(String uid) {
        gcHasMember.addSubjectIdentifier(uid);
        return this;
    }
}
