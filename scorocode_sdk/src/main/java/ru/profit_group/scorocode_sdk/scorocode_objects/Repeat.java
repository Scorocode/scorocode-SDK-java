package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class Repeat {
    private TypeCustom custom;
    private TypeDaily daily;
    private TypeMonthly monthly;

    public Repeat(TypeCustom custom, TypeDaily daily, TypeMonthly monthly) {
        this.custom = custom;
        this.daily = daily;
        this.monthly = monthly;
    }
}
