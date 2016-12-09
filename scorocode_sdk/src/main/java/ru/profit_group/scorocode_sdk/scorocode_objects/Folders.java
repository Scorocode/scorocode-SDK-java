package ru.profit_group.scorocode_sdk.scorocode_objects;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateNewFolder;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFolder;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetFoldersList;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 12/9/16
 */

public class Folders {
    public void getFoldersList(String pathToFolder, CallbackGetFoldersList callback) {
        ScorocodeSdk.getFoldersList(pathToFolder, callback);
    }

    public void createFolder(String pathToFolder, CallbackCreateNewFolder callback) {
        ScorocodeSdk.createFolder(pathToFolder, callback);
    }

    public void deleteFolder(String pathToFolder, CallbackDeleteFolder callback) {
        ScorocodeSdk.deleteFolder(pathToFolder, callback);
    }
}
