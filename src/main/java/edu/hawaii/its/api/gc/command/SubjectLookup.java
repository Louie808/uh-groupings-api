package edu.hawaii.its.api.gc.command;

import edu.internet2.middleware.grouperClient.ws.beans.WsSubjectLookup;

public final class SubjectLookup extends WsSubjectLookup {

    // Constructor.
    public SubjectLookup(String subject) {
        if (isUhUuid(subject)) {
            super.setSubjectId(subject);
        } else {
            super.setSubjectIdentifier(subject);
        }
    }

    private boolean isUhUuid(String s) {
        return s != null && s.matches("\\d+");
    }

}
