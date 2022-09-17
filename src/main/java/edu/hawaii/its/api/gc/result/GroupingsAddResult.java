package edu.hawaii.its.api.gc.result;

public class GroupingsAddResult extends GroupingsMemberResult {

    public GroupingsAddResult(AddMemberResult addMemberResult) {
        resultCode = addMemberResult.getResultCode();
        uid = addMemberResult.getUid();
        name = addMemberResult.getName();
        uhUuid = addMemberResult.getUhUuid();
    }

    public GroupingsAddResult(AddResult addResult) {
        resultCode = addResult.getResultCode();
        uid = addResult.getUid();
        name = addResult.getName();
        uhUuid = addResult.getUhUuid();
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    public String getUid() {
        return uid;
    }

    public String getUhUuid() {
        return uhUuid;
    }

    public String getName() {
        return name;
    }

}
