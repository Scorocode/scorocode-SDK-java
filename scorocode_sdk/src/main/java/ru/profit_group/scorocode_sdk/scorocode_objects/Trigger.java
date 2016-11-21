package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class Trigger {
    private String code;
    private boolean isActive;

    public Trigger(String code, boolean isActive) {
        this.code = code;
        this.isActive = isActive;
    }
}
