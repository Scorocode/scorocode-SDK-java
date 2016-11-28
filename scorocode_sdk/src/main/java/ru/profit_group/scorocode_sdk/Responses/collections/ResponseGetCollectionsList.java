package ru.profit_group.scorocode_sdk.Responses.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;

/**
 * Created by Peter Staranchuk on 11/28/16
 */

public class ResponseGetCollectionsList extends ResponseCodes {
    private Map<String, ScorocodeCollection> collections;

    public ResponseGetCollectionsList(Map<String, ScorocodeCollection> collections) {
        this.collections = collections;
    }

    public List<ScorocodeCollection> getCollections() {
        List<ScorocodeCollection> result = new ArrayList<>();

        if(collections != null) {
            for (String collectionName : collections.keySet()) {
                result.add(collections.get(collectionName));
            }
        }

        return result;
    }
}
