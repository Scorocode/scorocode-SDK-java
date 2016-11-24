package ru.profit_group.scorocode_sdk.Requests.collection;

import java.util.HashMap;
import java.util.Map;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeTriggers;
import ru.profit_group.scorocode_sdk.scorocode_objects.Trigger;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestChangeCollectionTriggers extends AppBase {

    private String coll;
    private ScorocodeTriggers triggers;

    public RequestChangeCollectionTriggers(ScorocodeSdkStateHolder stateHolder, String collectionName, ScorocodeTriggers triggers) {
        super(stateHolder);
        this.coll = collectionName;
        this.triggers = triggers;
    }
}
