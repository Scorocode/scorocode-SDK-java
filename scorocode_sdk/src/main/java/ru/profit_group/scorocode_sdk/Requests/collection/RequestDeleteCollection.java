package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestDeleteCollection extends AppBase {
    private ScorocodeCollection collection;

    public RequestDeleteCollection(ScorocodeCoreInfo stateHolder, ScorocodeCollection collection) {
        super(stateHolder);
        this.collection = collection;
    }
}
