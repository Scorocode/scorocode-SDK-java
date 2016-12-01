package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class TypeCustom {

    private int days;
    private int hours;
    private int minutes;

    public TypeCustom() {
        this(0,0,0);
    }

    public TypeCustom(int days, int hours, int minutes) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
    }

    public static String getName() {
        return "custom";
    }
}
