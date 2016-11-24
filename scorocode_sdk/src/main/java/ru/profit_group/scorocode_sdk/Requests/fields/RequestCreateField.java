package ru.profit_group.scorocode_sdk.Requests.fields;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeField;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestCreateField extends AppBase {

    private String coll;
    private ScorocodeField collField;

    public RequestCreateField(ScorocodeSdkStateHolder stateHolder, String collectionName, ScorocodeField scorocodeField) {
        super(stateHolder);
        this.coll = collectionName;
        this.collField = scorocodeField;
    }
}
