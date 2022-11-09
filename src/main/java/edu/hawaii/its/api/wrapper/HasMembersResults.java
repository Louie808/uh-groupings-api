package edu.hawaii.its.api.wrapper;

import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResult;
import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResults;

import java.util.ArrayList;
import java.util.List;

public class HasMembersResults extends Results {

    private WsHasMemberResults wsHasMemberResults;

    public HasMembersResults(WsHasMemberResults wsHasMemberResults) {
        if (wsHasMemberResults == null) {
            this.wsHasMemberResults = new WsHasMemberResults();
        } else {
            this.wsHasMemberResults = wsHasMemberResults;
        }
    }

    public HasMembersResults() {
        this.wsHasMemberResults = new WsHasMemberResults();
    }

    public List<HasMemberResult> getResults() {
        List<HasMemberResult> hasMembersResults = new ArrayList<>();
        WsHasMemberResult[] wsResults = wsHasMemberResults.getResults();
        if (isEmpty(wsResults)) {
        } else {
            for (WsHasMemberResult wsResult : wsResults) {
                hasMembersResults.add(new HasMemberResult(wsResult));
            }
        }
        return hasMembersResults;
    }

    @Override public String getResultCode() {
        String success = "SUCCESS";
        String failure = "FAILURE";
        for (HasMemberResult hasMemberResult : getResults()) {
            if (hasMemberResult.getResultCode().equals(success)) {
                return success;
            }
        }
        return failure;
    }
}
