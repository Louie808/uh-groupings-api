package edu.hawaii.its.api.gc.command;

import edu.hawaii.its.api.gc.result.SubjectResult;
import edu.internet2.middleware.grouperClient.api.GcGetSubjects;
import edu.internet2.middleware.grouperClient.ws.beans.WsGetSubjectsResults;

public class SubjectCommand implements Command<SubjectResult> {

    private final GcGetSubjects gcGetSubjects;

    // Constructor.
    public SubjectCommand(String uhIdentifier) {
        gcGetSubjects = new GcGetSubjects();
        this.addSubject(uhIdentifier)
                .addSubjectAttribute("uhUuid")
                .addSubjectAttribute("uid")
                .addSubjectAttribute("cn")
                .addSubjectAttribute("sn")
                .addSubjectAttribute("givenName");
    }

    @Override
    public SubjectResult execute() {
        SubjectResult subjectResult;
        try {
            WsGetSubjectsResults wsGetSubjectsResults = gcGetSubjects.execute();
            subjectResult = new SubjectResult(wsGetSubjectsResults);
        } catch (RuntimeException e) {
            subjectResult = new SubjectResult();
        }
        return subjectResult;
    }

    private SubjectCommand addSubject(String uhIdentifier) {
        gcGetSubjects.addWsSubjectLookup(new SubjectLookup(uhIdentifier));
        return this;
    }

    private SubjectCommand addSubjectAttribute(String attribute) {
        gcGetSubjects.addSubjectAttributeName(attribute);
        return this;
    }

}
