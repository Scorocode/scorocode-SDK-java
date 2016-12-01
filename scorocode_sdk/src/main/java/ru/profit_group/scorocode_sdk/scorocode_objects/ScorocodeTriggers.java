package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

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

    @NonNull
    public ScorocodeTriggers setBeforeInsert(Trigger beforeInsert) {
        this.beforeInsert = beforeInsert;
        return this;
    }

    @NonNull
    public ScorocodeTriggers setAfterInsert(Trigger afterInsert) {
        this.afterInsert = afterInsert;
        return this;
    }

    @NonNull
    public ScorocodeTriggers setBeforeUpdate(Trigger beforeUpdate) {
        this.beforeUpdate = beforeUpdate;
        return this;
    }

    @NonNull
    public ScorocodeTriggers setAfterUpdate(Trigger afterUpdate) {
        this.afterUpdate = afterUpdate;
        return this;
    }

    @NonNull
    public ScorocodeTriggers setBeforeRemove(Trigger beforeRemove) {
        this.beforeRemove = beforeRemove;
        return this;
    }

    @NonNull
    public ScorocodeTriggers setAfterRemove(Trigger afterRemove) {
        this.afterRemove = afterRemove;
        return this;
    }

    @NonNull
    public Trigger getBeforeInsert() {
        return beforeInsert == null? (new Trigger()) : beforeInsert;
    }

    @NonNull
    public Trigger getAfterInsert() {
        return afterInsert == null? (new Trigger()) : afterInsert;
    }

    @NonNull
    public Trigger getBeforeUpdate() {
        return beforeUpdate == null? (new Trigger()) : beforeUpdate;
    }

    @NonNull
    public Trigger getAfterUpdate() {
        return afterUpdate == null? (new Trigger()) : afterUpdate;
    }

    @NonNull
    public Trigger getBeforeRemove() {
        return beforeRemove == null? (new Trigger()) : beforeRemove;
    }

    @NonNull
    public Trigger getAfterRemove() {
        return afterRemove == null? (new Trigger()) : afterRemove;
    }

}
