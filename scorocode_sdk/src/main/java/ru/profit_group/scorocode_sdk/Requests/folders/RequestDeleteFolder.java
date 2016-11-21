package ru.profit_group.scorocode_sdk.Requests.folders;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestDeleteFolder extends AppBase {

    private String path;

    public RequestDeleteFolder(String masterKey, String applicationId, String path) {
        super(masterKey, applicationId);
        this.path = path;
    }
}
