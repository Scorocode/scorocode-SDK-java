package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.Index;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestRemoveCollectionIndex extends AppBase {

    private String coll;
    private Index index;

    public RequestRemoveCollectionIndex(String masterKey, String applicationId, String collectionName, Index index) {
        super(masterKey, applicationId);
        this.coll = collectionName;
        this.index = index;
    }
}
