package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

import java.util.ArrayList;
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
    private boolean notify;
    private List<Index> indexes;

    public ScorocodeCollection() {}

    public ScorocodeCollection(
            String id, String name, boolean useDocsACL,
            ScorocodeACL ACL, ScorocodeTriggers triggers,
            List<ScorocodeField> fields, boolean system, List<Index> indices, boolean notify) {
        this.id = id;
        this.name = name;
        this.useDocsACL = useDocsACL;
        this.ACL = ACL;
        this.triggers = triggers;
        this.fields = fields;
        this.system = system;
        this.indexes = indices;
        this.notify = notify;
    }

    @NonNull
    public String getCollectionId() {
        return id == null? "" : id;
    }

    @NonNull
    public String getCollectionName() {
        return name == null? "" : name;
    }

    @NonNull
    public boolean isUseDocsACL() {
        return useDocsACL;
    }

    @NonNull
    public ScorocodeACL getACL() {
        return ACL == null? (new ScorocodeACL()) : ACL;
    }

    @NonNull
    public ScorocodeTriggers getTriggers() {
        return triggers == null? (new ScorocodeTriggers()) : triggers;
    }

    @NonNull
    public List<ScorocodeField> getFields() {
        return fields == null? (new ArrayList<ScorocodeField>()) : fields;
    }

    @NonNull
    public boolean isSystemCollection() {
        return system;
    }

    @NonNull
    public List<Index> getIndices() {
        return indexes == null? (new ArrayList<Index>()) : indexes;
    }

    @NonNull
    public ScorocodeCollection setCollectionId(String id) {
        this.id = id;
        return this;
    }

    @NonNull
    public ScorocodeCollection setCollectionName(String name) {
        this.name = name;
        return this;
    }

    @NonNull
    public ScorocodeCollection setUseDocsACL(boolean useDocsACL) {
        this.useDocsACL = useDocsACL;
        return this;
    }

    @NonNull
    public ScorocodeCollection setACL(ScorocodeACL ACL) {
        this.ACL = ACL;
        return this;
    }

    @NonNull
    public ScorocodeCollection setTriggers(ScorocodeTriggers triggers) {
        this.triggers = triggers;
        return this;
    }

    @NonNull
    public ScorocodeCollection setFields(List<ScorocodeField> fields) {
        this.fields = fields;
        return this;
    }

    @NonNull
    public ScorocodeCollection setSystem(boolean system) {
        this.system = system;
        return this;
    }

    @NonNull
    public ScorocodeCollection setIndexes(List<Index> indexes) {
        this.indexes = indexes;
        return this;
    }

    public boolean isNotify() {
        return notify;
    }

    @NonNull
    public ScorocodeCollection setNotify(boolean notify) {
        this.notify = notify;
        return this;
    }
}
