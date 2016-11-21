package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.List;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class TypeDaily {
    private List<Integer> on;
    private List<Integer> hours;
    private Integer minutes;

    public TypeDaily(List<Integer> on, List<Integer> hours, Integer minutes) {
        this.on = on;
        this.hours = hours;
        this.minutes = minutes;
    }

    public static String getName() {
        return "daily";
    }
}
