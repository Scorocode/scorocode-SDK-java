package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestCloneCollection extends AppBase {

    private ScorocodeCollection collection;

    public RequestCloneCollection(ScorocodeSdkStateHolder stateHolder, ScorocodeCollection collection) {
        super(stateHolder);
        this.collection = collection;
    }
}
