package edu.hawaii.its.api.wrapper;

import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResult;
import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResults;

public class HasMemberResult extends Results {
    private WsHasMemberResults wsHasMemberResults;
    private WsHasMemberResult wsHasMemberResult;

    public HasMemberResult(WsHasMemberResults wsHasMemberResults) {
        this.wsHasMemberResults = wsHasMemberResults;
        if (this.wsHasMemberResults == null) {
            this.wsHasMemberResults = new WsHasMemberResults();
        }
        if (wsHasMemberResults.getResults() != null) {
            this.wsHasMemberResult = wsHasMemberResults.getResults()[0];
        } else {
            this.wsHasMemberResult = new WsHasMemberResult();
        }
    }

    public HasMemberResult() {
        this.wsHasMemberResults = new WsHasMemberResults();
    }

    @Override public String getResultCode() {
        return this.wsHasMemberResult.getResultMetadata().getResultCode();
    }
}
