package ru.profit_group.scorocode_sdk.Requests.fields;

import java.util.HashMap;
import java.util.Map;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeField;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestDeleteField extends AppBase {

    private String coll;
    private ScorocodeField collField;

    public RequestDeleteField(ScorocodeSdkStateHolder stateHolder, String collectionName, ScorocodeField field) {
        super(stateHolder);
        this.coll = collectionName;
        this.collField = field;
    }
}
