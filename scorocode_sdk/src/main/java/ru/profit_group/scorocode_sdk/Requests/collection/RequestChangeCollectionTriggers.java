package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeTriggers;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestChangeCollectionTriggers extends AppBase {

    private String coll;
    private ScorocodeTriggers triggers;

    public RequestChangeCollectionTriggers(ScorocodeCoreInfo stateHolder, String collectionName, ScorocodeTriggers triggers) {
        super(stateHolder);
        this.coll = collectionName;
        this.triggers = triggers;
    }
}
