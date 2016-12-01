package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class Repeat {
    private TypeCustom custom;
    private TypeDaily daily;
    private TypeMonthly monthly;

    public Repeat() {}

    public Repeat(TypeCustom custom, TypeDaily daily, TypeMonthly monthly) {
        this.custom = custom;
        this.daily = daily;
        this.monthly = monthly;
    }

    public Repeat setCustom(TypeCustom custom) {
        this.custom = custom;
        return this;
    }

    public Repeat setDaily(TypeDaily daily) {
        this.daily = daily;
        return this;
    }

    public Repeat setMonthly(TypeMonthly monthly) {
        this.monthly = monthly;
        return this;
    }

    public TypeCustom getCustom() {
        return custom == null? (new TypeCustom()) : custom;
    }

    public TypeDaily getDaily() {
        return daily == null? (new TypeDaily()) : daily;
    }

    public TypeMonthly getMonthly() {
        return monthly == null? (new TypeMonthly()) : monthly;
    }
}
