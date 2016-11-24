package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.List;
import java.util.Map;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public class Schemas {
    private Map<String, ScorocodeCollection> collections;

    public Schemas(Map<String, ScorocodeCollection> collections) {
        this.collections = collections;
    }
}
