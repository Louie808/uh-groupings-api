package edu.hawaii.its.api.gc.result;

import java.util.ArrayList;
import java.util.List;

import edu.hawaii.its.api.type.GroupingsMembersResults;

public class GroupingsAddResults extends GroupingsMembersResults implements MemberResults<GroupingsAddResult> {

    private final List<GroupingsAddResult> groupingsAddResults;

    public GroupingsAddResults(AddMembersResults addMembersResults) {
        groupingsAddResults = new ArrayList<>();
        for (AddResult addResult : addMembersResults.getResults()) {
            groupingsAddResults.add(new GroupingsAddResult(addResult));
        }
    }

    @Override
    public String getResultCode() {
        String success = "SUCCESS";
        String failure = "FAILURE";
        for (GroupingsAddResult groupingsAddResult : groupingsAddResults) {
            if (groupingsAddResult.resultCode.equals(success)) {
                return success;
            }
        }
        return failure;
    }

    @Override
    public List<GroupingsAddResult> getResults() {
        return groupingsAddResults;
    }
}
