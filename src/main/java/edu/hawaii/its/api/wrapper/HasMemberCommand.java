package edu.hawaii.its.api.wrapper;

import edu.hawaii.its.api.util.JsonUtil;

import edu.internet2.middleware.grouperClient.api.GcHasMember;
import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResults;

public class HasMemberCommand extends GrouperCommand implements Command<HasMemberResult> {
    private GcHasMember gcHasMember;

    public HasMemberCommand(String groupPath, String uhIdentifier) {
        gcHasMember = new GcHasMember();
        this.assignGroupPath(groupPath)
                .addUhIdentifier(uhIdentifier);
    }

    @Override public HasMemberResult execute() {
        HasMemberResult hasMemberResult;
        try {
            WsHasMemberResults wsHasMemberResults = gcHasMember.execute();
            JsonUtil.printJson(wsHasMemberResults);
            hasMemberResult = new HasMemberResult(wsHasMemberResults);
        } catch (RuntimeException e) {
            hasMemberResult = new HasMemberResult();
        }
        return hasMemberResult;
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
