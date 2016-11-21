package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeField;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestAddFieldInCollection extends AppBase {

    private String collectionName;
    private ScorocodeField scorocodeField;

    public RequestAddFieldInCollection(String masterKey, String applicationId, String collectionName, ScorocodeField scorocodeField) {
        super(masterKey, applicationId);
        this.collectionName = collectionName;
        this.scorocodeField = scorocodeField;
    }
}
