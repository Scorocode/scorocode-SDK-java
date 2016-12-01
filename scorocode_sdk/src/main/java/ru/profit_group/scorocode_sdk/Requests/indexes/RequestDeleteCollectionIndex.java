package ru.profit_group.scorocode_sdk.Requests.indexes;

import java.util.ArrayList;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.Index;
import ru.profit_group.scorocode_sdk.scorocode_objects.IndexField;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestDeleteCollectionIndex extends AppBase {

    private String coll;
    private Index index;

    public RequestDeleteCollectionIndex(ScorocodeCoreInfo stateHolder, String collectionName, String indexName) {
        super(stateHolder);
        this.coll = collectionName;
        this.index = new Index(indexName, new ArrayList<IndexField>());
    }
}
