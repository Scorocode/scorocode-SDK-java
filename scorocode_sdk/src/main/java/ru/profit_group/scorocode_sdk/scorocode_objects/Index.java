package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.List;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class Index {
    private String name;
    private List<IndexField> fields;

    public Index(String name, List<IndexField> fields) {
        this.name = name;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public List<IndexField> getFields() {
        return fields;
    }
}
