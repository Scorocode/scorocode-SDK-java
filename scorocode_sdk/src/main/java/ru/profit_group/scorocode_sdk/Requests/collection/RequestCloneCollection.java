package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestCloneCollection extends AppBase {

    private ScorocodeCollection collection;

    public RequestCloneCollection(String masterKey, String applicationId, ScorocodeCollection collection) {
        super(masterKey, applicationId);
        this.collection = collection;
    }
}
