package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.List;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class ScorocodeCollection {
    private String id;
    private String name;
    private boolean useDocsACL;
    private ScorocodeACL ACL;
    private ScorocodeTriggers triggers;
    private List<ScorocodeField> fields;
    private boolean system;
    private List<Index> indexes;

    public ScorocodeCollection(
            String id, String name, boolean useDocsACL,
            ScorocodeACL ACL, ScorocodeTriggers triggers,
            List<ScorocodeField> fields, boolean system, List<Index> indices) {
        this.id = id;
        this.name = name;
        this.useDocsACL = useDocsACL;
        this.ACL = ACL;
        this.triggers = triggers;
        this.fields = fields;
        this.system = system;
        this.indexes = indices;
    }

    public String getCollectionId() {
        return id;
    }

    public String getCollectionName() {
        return name;
    }

    public boolean isUseDocsACL() {
        return useDocsACL;
    }

    public ScorocodeACL getACL() {
        return ACL;
    }

    public ScorocodeTriggers getTriggers() {
        return triggers;
    }

    public List<ScorocodeField> getFields() {
        return fields;
    }

    public boolean isSystemCollection() {
        return system;
    }

    public List<Index> getIndices() {
        return indexes;
    }
}
