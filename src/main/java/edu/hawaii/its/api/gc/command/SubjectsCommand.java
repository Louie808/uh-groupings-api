package edu.hawaii.its.api.gc.command;

import java.util.List;

import edu.hawaii.its.api.gc.result.SubjectsResults;
import edu.internet2.middleware.grouperClient.api.GcGetSubjects;
import edu.internet2.middleware.grouperClient.ws.beans.WsGetSubjectsResults;

public class SubjectsCommand implements Command<SubjectsResults> {

    private final GcGetSubjects gcGetSubjects;

    public SubjectsCommand(List<String> uhIdentifiers) {
        gcGetSubjects = new GcGetSubjects();
        for (String uhIdentifier : uhIdentifiers) {
            addSubject(uhIdentifier);
        }
    }

    @Override
    public SubjectsResults execute() {
        SubjectsResults subjectsResults;
        try {
            WsGetSubjectsResults wsGetSubjectsResults = gcGetSubjects.execute();
            subjectsResults = new SubjectsResults(wsGetSubjectsResults);
        } catch (RuntimeException e) {
            subjectsResults = new SubjectsResults();
        }
        return subjectsResults;
    }

    private SubjectsCommand addSubject(String uhIdentifier) {
        gcGetSubjects.addWsSubjectLookup(new SubjectLookup(uhIdentifier));
        return this;
    }
}
