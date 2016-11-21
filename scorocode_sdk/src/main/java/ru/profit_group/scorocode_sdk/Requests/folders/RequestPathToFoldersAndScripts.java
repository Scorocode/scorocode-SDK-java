package ru.profit_group.scorocode_sdk.Requests.folders;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestPathToFoldersAndScripts extends AppBase {

    private String path;

    public RequestPathToFoldersAndScripts(String masterKey, String applicationId, String path) {
        super(masterKey, applicationId);
        this.path = path;
    }
}
