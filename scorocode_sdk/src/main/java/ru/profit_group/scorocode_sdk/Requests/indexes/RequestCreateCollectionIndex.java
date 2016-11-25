package ru.profit_group.scorocode_sdk.Requests.indexes;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.Index;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestCreateCollectionIndex extends AppBase {
    private String coll;
    private Index index;

    public RequestCreateCollectionIndex(ScorocodeCoreInfo stateHolder, String collectionName, Index index) {
        super(stateHolder);
        this.coll = collectionName;
        this.index = index;
    }
}
