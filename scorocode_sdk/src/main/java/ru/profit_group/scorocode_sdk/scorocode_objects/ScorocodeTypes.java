package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/28/16
 */

public enum ScorocodeTypes {
    TypeDate("Date"),
    TypeBoolean("Boolean"),
    TypeString("String"),
    TypeFile("File"),
    TypeNumber("Number"),
    TypePassword("Password"),
    TypeArray("Array"),
    TypeObject("Object"),
    TypeRelation("Relation"),
    TypePointer("Pointer");

    private String name;

    ScorocodeTypes(java.lang.String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
