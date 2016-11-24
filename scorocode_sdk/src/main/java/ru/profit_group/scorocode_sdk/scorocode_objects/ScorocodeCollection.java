package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class ScorocodeCollection {
    private String id;
    private String name;
    private boolean useDocsACL;
    private ScorocodeACL ACL;

    public ScorocodeCollection(String id, String name, boolean useDocsACL, ScorocodeACL ACL) {
        this.id = id;
        this.name = name;
        this.useDocsACL = useDocsACL;
        this.ACL = ACL;
    }
}
