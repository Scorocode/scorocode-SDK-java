package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestDeleteCollection extends AppBase {
    private ScorocodeCollection collection;

    public RequestDeleteCollection(String masterKey, String applicationId, ScorocodeCollection collection) {
        super(masterKey, applicationId);
        this.collection = collection;
    }
}
