package ru.profit_group.scorocode_sdk.Responses.collections;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;

/**
 * Created by Peter Staranchuk on 11/28/16
 */

public class ResponseGetCollectionByName extends ResponseCodes {
    private ScorocodeCollection collection;

    public ResponseGetCollectionByName(ScorocodeCollection collection) {
        this.collection = collection;
    }

    public ScorocodeCollection getCollection() {
        return collection;
    }
}
