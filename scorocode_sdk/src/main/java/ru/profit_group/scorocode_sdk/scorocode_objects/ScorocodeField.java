package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class ScorocodeField {
    private String name;
    private String type;
    private String target;
    private boolean system;
    private boolean readonly;
    private boolean required;

    public ScorocodeField(String name, ScorocodeTypes type, String target, boolean system, boolean readonly, boolean required) {
        this.name = name;
        this.type = type.getName();
        this.target = target;
        this.system = system;
        this.readonly = readonly;
        this.required = required;
    }
}
