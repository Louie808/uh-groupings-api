package edu.hawaii.its.api.wrapper;

import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResult;

public class HasResult extends MemberResult {
   private WsHasMemberResult wsHasMemberResult;

   public HasResult(WsHasMemberResult wsHasMemberResult) {
       if (wsHasMemberResult == null) {
           this.wsHasMemberResult = new WsHasMemberResult();
       } else {
           this.wsHasMemberResult = wsHasMemberResult;
           if (wsHasMemberResult.getWsSubject() != null) {
               subject = new Subject(wsHasMemberResult.getWsSubject());
           }
       }
   }


    @Override public String getResultCode() {
        return null;
    }

    @Override public String getUhUuid() {
        return super.getUhUuid();
    }

    @Override public String getName() {
        return super.getName();
    }


}
