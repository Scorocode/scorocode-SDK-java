package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestCreateCollectionIndex extends AppBase {
    private String coll;

    public RequestCreateCollectionIndex(String masterKey, String applicationId, String collectionName) {
        super(masterKey, applicationId);
        this.coll = collectionName;
    }
}
