package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class Index {
    private String name;
    private List<Map<String, Integer>> fields;

    public Index(String name, List<SortInfo> sortInfos) {
        this.name = name;
        fields = new ArrayList<>();

        if(sortInfos != null) {
            for(SortInfo sortInfo : sortInfos) {
                fields.add(sortInfo.getInfo());
            }
        }
    }
}
