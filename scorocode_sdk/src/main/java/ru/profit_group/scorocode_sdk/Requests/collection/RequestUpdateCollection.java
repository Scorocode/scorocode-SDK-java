package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestUpdateCollection extends AppBase {
    private ScorocodeCollection collection;

    public RequestUpdateCollection(String masterKey, String applicationId, ScorocodeCollection collection) {
        super(masterKey, applicationId);
    }
}
