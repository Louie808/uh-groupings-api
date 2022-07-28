package edu.hawaii.its.api.service;

import edu.hawaii.its.api.type.Grouping;
import edu.hawaii.its.api.type.GroupingsServiceResult;
import edu.hawaii.its.api.type.SyncDestination;
import edu.hawaii.its.api.type.OptType;

import java.util.List;

public interface GroupAttributeService {

    List<SyncDestination> getAllSyncDestinations(String currentUsername, String path);

    List<SyncDestination> getSyncDestinations(Grouping grouping);

    List<GroupingsServiceResult> changeOptStatus(String groupingPath, String currentUsername,
            String preferenceId, boolean isOptValue);

    GroupingsServiceResult changeGroupAttributeStatus(String groupPath, String ownerUsername,
            String attributeName, boolean turnAttributeOn);

    boolean isGroupAttribute(String groupPath, String attribute);

    GroupingsServiceResult assignGrouperPrivilege(String username, String privilegeName, String groupPath,
            boolean isSet);

    GroupingsServiceResult updateDescription(String groupPath, String ownerUsername, String description);

}
