package ru.profit_group.scorocode_sdk.Requests.bots;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestGetBotsList extends AppBase {

    public RequestGetBotsList(String masterKey, String applicationId) {
        super(masterKey, applicationId);
    }
}
