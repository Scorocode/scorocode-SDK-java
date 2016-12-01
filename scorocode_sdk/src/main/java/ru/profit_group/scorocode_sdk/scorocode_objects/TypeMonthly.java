package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class TypeMonthly {

    private List<Integer> on;
    private List<Integer> days;
    private boolean lastDate;
    private Integer hours;
    private Integer minutes;

    public TypeMonthly() {
        this(new ArrayList<Integer>(), new ArrayList<Integer>(), false, 0, 0);
    }

    public TypeMonthly(List<Integer> on, List<Integer> days, boolean lastDate, Integer hours, Integer minutes) {
        this.on = on;
        this.days = days;
        this.lastDate = lastDate;
        this.hours = hours;
        this.minutes = minutes;
    }

    public static String getName() {
        return "monthly";
    }
}
