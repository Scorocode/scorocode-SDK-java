package ru.profit_group.scorocode_sdk.Requests.collection;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeACL;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestCreateCollection extends AppBase {

    private ScorocodeCollection collection;

    public RequestCreateCollection(ScorocodeCoreInfo stateHolder, String collectionName, boolean isUseDocsACL, ScorocodeACL ACL) {
        super(stateHolder);
        this.collection = new ScorocodeCollection()
                .setCollectionName(collectionName)
                .setUseDocsACL(isUseDocsACL)
                .setACL(ACL)
                .setNotify(false);
    }

    public RequestCreateCollection(ScorocodeCoreInfo stateHolder, String collectionName, boolean isUseDocsACL, ScorocodeACL ACL, boolean notify) {
        super(stateHolder);
        this.collection = new ScorocodeCollection()
                .setCollectionName(collectionName)
                .setUseDocsACL(isUseDocsACL)
                .setACL(ACL)
                .setNotify(notify);
    }
}
