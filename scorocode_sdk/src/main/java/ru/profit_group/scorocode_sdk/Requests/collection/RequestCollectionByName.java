package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestCollectionByName extends AppBase {
    private String coll;

    public RequestCollectionByName(ScorocodeCoreInfo stateHolder, String collectionName) {
        super(stateHolder);
        this.coll = collectionName;
    }
}
