package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/28/16
 */

public class IndexField {
    private String name;
    private Integer order;

    public IndexField(String name, Integer order) {
        this.name = name;
        this.order = order;
    }

    public Integer getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }
}
