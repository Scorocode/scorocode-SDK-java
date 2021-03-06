package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public class ScorocodeACL {
    private List<String> create;
    private List<String> read;
    private List<String> remove;
    private List<String> update;

    public ScorocodeACL() {}

    public ScorocodeACL(List<String> create, List<String> read, List<String> remove, List<String> update) {
        this.create = create;
        this.read = read;
        this.remove = remove;
        this.update = update;
    }

    @NonNull
    public List<String> getCreate() {
        return create == null? (new ArrayList<String>()) : create;
    }

    @NonNull
    public List<String> getRead() {
        return read == null? (new ArrayList<String>()) : read;
    }

    @NonNull
    public List<String> getRemove() {
        return remove == null? (new ArrayList<String>()) : remove;
    }

    @NonNull
    public List<String> getUpdate() {
        return update == null? (new ArrayList<String>()) : update;
    }
}
