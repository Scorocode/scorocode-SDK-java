package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public class ScorocodeTriggers {
    private Trigger beforeInsert;
    private Trigger afterInsert;
    private Trigger beforeUpdate;
    private Trigger afterUpdate;
    private Trigger beforeRemove;
    private Trigger afterRemove;

    public ScorocodeTriggers() {}

    public ScorocodeTriggers setBeforeInsert(Trigger beforeInsert) {
        this.beforeInsert = beforeInsert;
        return this;
    }

    public ScorocodeTriggers setAfterInsert(Trigger afterInsert) {
        this.afterInsert = afterInsert;
        return this;
    }

    public ScorocodeTriggers setBeforeUpdate(Trigger beforeUpdate) {
        this.beforeUpdate = beforeUpdate;
        return this;
    }

    public ScorocodeTriggers setAfterUpdate(Trigger afterUpdate) {
        this.afterUpdate = afterUpdate;
        return this;
    }

    public ScorocodeTriggers setBeforeRemove(Trigger beforeRemove) {
        this.beforeRemove = beforeRemove;
        return this;
    }

    public ScorocodeTriggers setAfterRemove(Trigger afterRemove) {
        this.afterRemove = afterRemove;
        return this;
    }
}
