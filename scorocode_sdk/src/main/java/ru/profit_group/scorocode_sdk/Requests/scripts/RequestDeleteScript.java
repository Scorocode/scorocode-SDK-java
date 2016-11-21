package ru.profit_group.scorocode_sdk.Requests.scripts;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestDeleteScript extends AppBase {

    private String script;

    public RequestDeleteScript(String masterKey, String applicationId, String scriptId) {
        super(masterKey, applicationId);
        this.script = scriptId;
    }
}
