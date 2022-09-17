package edu.hawaii.its.api.gc.command;

import edu.internet2.middleware.grouperClient.ws.beans.WsSubjectLookup;

public abstract class GrouperCommand {

    protected WsSubjectLookup subjectLookup(String username) {
        WsSubjectLookup wsSubjectLookup = new WsSubjectLookup();

        if (isUhUuid(username)) {
            wsSubjectLookup.setSubjectId(username);
        } else {
            wsSubjectLookup.setSubjectIdentifier(username);
        }
        return wsSubjectLookup;
    }

    protected boolean isUhUuid(String naming) {
        return naming != null && naming.matches("\\d+");
    }
}
