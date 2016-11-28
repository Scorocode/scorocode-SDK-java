package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeACL;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestUpdateCollection extends AppBase {
    private ScorocodeCollection collection;

    public RequestUpdateCollection(ScorocodeCoreInfo stateHolder, String collectionId, String collectionName, boolean isUseDocsACL, ScorocodeACL ACL) {
        super(stateHolder);
        this.collection = new ScorocodeCollection()
                .setCollectionName(collectionName)
                .setUseDocsACL(isUseDocsACL)
                .setACL(ACL)
                .setId(collectionId);
    }
}
