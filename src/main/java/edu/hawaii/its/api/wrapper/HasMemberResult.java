package edu.hawaii.its.api.wrapper;

import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResult;
import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResults;

public class HasMemberResult extends Results {
    private WsHasMemberResults wsHasMemberResults;
    private WsHasMemberResult wsHasMemberResult;
    private Subject subject;

    public HasMemberResult(WsHasMemberResults wsHasMemberResults) {
        this.wsHasMemberResults = wsHasMemberResults;
        if (this.wsHasMemberResults == null) {
            this.wsHasMemberResults = new WsHasMemberResults();
        }
        if (wsHasMemberResults.getResults() != null) {
            wsHasMemberResult = wsHasMemberResults.getResults()[0];
            this.subject = new Subject(wsHasMemberResult.getWsSubject());
        } else {
            this.subject = new Subject();
        }
    }

    public HasMemberResult() {
        this.wsHasMemberResults = new WsHasMemberResults();
    }

    @Override public String getResultCode() {
        return this.wsHasMemberResult.getResultMetadata().getResultCode();
    }

    public String getUhUuid() {
        return subject.getUhUuid();
    }

    public String getName() {
        return subject.getName();
    }

    public String getUid() {
        return subject.getUid();
    }
}
