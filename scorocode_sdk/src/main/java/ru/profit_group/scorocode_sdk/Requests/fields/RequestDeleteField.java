package ru.profit_group.scorocode_sdk.Requests.fields;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeField;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestDeleteField extends AppBase {

    private String coll;
    private ScorocodeField collField;

    public RequestDeleteField(ScorocodeCoreInfo stateHolder, String collectionName, String fieldName) {
        super(stateHolder);
        this.coll = collectionName;
        this.collField = new ScorocodeField().setName(fieldName);
    }
}
