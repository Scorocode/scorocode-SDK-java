package ru.profit_group.scorocode_sdk.Callbacks;

import java.util.List;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeFolder;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public interface CallbackGetFoldersList {
    void onRequestSucceed(List<ScorocodeFolder> folderList);
    void onRequestFailed(String errorCode, String errorMessage);
}
