package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestCollectionByName extends AppBase {
    private String collectionName;

    public RequestCollectionByName(String masterKey, String applicationId, String collectionName) {
        super(masterKey, applicationId);
        this.collectionName = collectionName;
    }
}
