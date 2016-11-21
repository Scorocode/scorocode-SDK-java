package ru.profit_group.scorocode_sdk.Requests.collection;

import java.util.HashMap;
import java.util.Map;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestDeleteFieldFromCollection extends AppBase {

    private String coll;
    private Map<String, String> collField;

    public RequestDeleteFieldFromCollection(String masterKey, String applicationId, String collectionName, String fieldName) {
        super(masterKey, applicationId);
        this.coll = collectionName;
        collField = new HashMap<>();
        collField.put("name", fieldName);
    }
}
