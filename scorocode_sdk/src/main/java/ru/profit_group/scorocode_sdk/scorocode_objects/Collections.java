package ru.profit_group.scorocode_sdk.scorocode_objects;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackAddField;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCloneCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateCollectionIndex;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteCollectionIndex;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteField;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetCollectionsList;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateCollectionTriggers;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 12/9/16
 */

public class Collections {

    public void getCollectionsList(CallbackGetCollectionsList callback) {
        ScorocodeSdk.getCollectionsList(callback);
    }

    public void getCollectionByName(String collectionName, CallbackGetCollection callback) {
        ScorocodeSdk.getCollectionByName(collectionName, callback);
    }

    public void createCollection(ScorocodeCollection collection, CallbackCreateCollection callback) {
        ScorocodeSdk.createCollection(collection.getCollectionName(), collection.isUseDocsACL(), collection.getACL(), callback);
    }

    public void updateCollection(String collectionId, ScorocodeCollection collection, CallbackUpdateCollection callback) {
        ScorocodeSdk.updateCollection(collectionId, collection.getCollectionName(), collection.isUseDocsACL(), collection.getACL(), callback);
    }

    public void deleteCollection(String collectionId, CallbackDeleteCollection callback) {
        ScorocodeSdk.deleteCollection(collectionId, callback);
    }

    public void cloneCollection(String collectionId, String collectionName, CallbackCloneCollection callback) {
        ScorocodeSdk.cloneCollection(collectionId, collectionName, callback);
    }

    public void createCollectionIndex(String collectionName, Index index, CallbackCreateCollectionIndex callback) {
        ScorocodeSdk.createCollectionIndex(collectionName, index, callback);
    }

    public void deleteCollectionIndex(String collectionName, String indexName, CallbackDeleteCollectionIndex callback) {
        ScorocodeSdk.deleteCollectionIndex(collectionName, indexName, callback);
    }

    public void updateCollectionTriggers(String collectionName, ScorocodeTriggers triggers, CallbackUpdateCollectionTriggers callback) {
        ScorocodeSdk.updateCollectionTriggers(collectionName, triggers, callback);
    }

    public void createCollectionField(String collectionName, ScorocodeField field, CallbackAddField callback) {
        ScorocodeSdk.createCollectionField(collectionName, field, callback);
    }

    public void deleteCollectionField(String collectionName, String fieldName, CallbackDeleteField callback) {
        ScorocodeSdk.deleteCollectionField(collectionName, fieldName, callback);
    }


}
