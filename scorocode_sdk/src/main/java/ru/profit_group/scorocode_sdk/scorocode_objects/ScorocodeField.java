package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

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

    public ScorocodeField() {}

    public ScorocodeField(String name, ScorocodeTypes type, String target, boolean system, boolean readonly, boolean required) {
        this.name = name;
        this.type = type.getName();
        this.target = target;
        this.system = system;
        this.readonly = readonly;
        this.required = required;
    }

    @NonNull
    public ScorocodeField setName(String name) {
        this.name = name;
        return this;
    }

    @NonNull
    public ScorocodeField setType(String type) {
        this.type = type;
        return this;
    }

    @NonNull
    public ScorocodeField setTarget(String target) {
        this.target = target;
        return this;
    }

    @NonNull
    public ScorocodeField setSystem(boolean system) {
        this.system = system;
        return this;
    }

    @NonNull
    public ScorocodeField setReadonly(boolean readonly) {
        this.readonly = readonly;
        return this;
    }

    @NonNull
    public ScorocodeField setRequired(boolean required) {
        this.required = required;
        return this;
    }

    @NonNull
    public String getFieldName() {
        return name == null? "" : name;
    }

    @NonNull
    public ScorocodeTypes getFieldType() {
        for(ScorocodeTypes fieldType : ScorocodeTypes.values()) {
            if(fieldType.getName().equalsIgnoreCase(type)) {
                return fieldType;
            }
        }

        return ScorocodeTypes.TypeWrong;
    }

    @NonNull
    public String getTarget() {
        return target == null? "" : target;
    }

    public boolean isSystemField() {
        return system;
    }

    public boolean isReadonlyField() {
        return readonly;
    }

    public boolean isRequiredField() {
        return required;
    }
}
