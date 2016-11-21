package ru.profit_group.scorocode_sdk.Requests.application;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class AppBase {
    protected String masterKey;
    protected String applicationId;

    public AppBase(String masterKey, String applicationId) {
        this.masterKey = masterKey;
        this.applicationId = applicationId;
    }
}
