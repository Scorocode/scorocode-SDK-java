package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public interface CallbackCreateNewFolder {
    void onFolderCreated();
    void onCreationFailed(String errorCode, String errorMessage);
}
