package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.List;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public class ScorocodeACL {
    private List<String> create;
    private List<String> read;
    private List<String> remove;
    private List<String> update;

    public ScorocodeACL(List<String> create, List<String> read, List<String> remove, List<String> update) {
        this.create = create;
        this.read = read;
        this.remove = remove;
        this.update = update;
    }

    public List<String> getCreate() {
        return create;
    }

    public List<String> getRead() {
        return read;
    }

    public List<String> getRemove() {
        return remove;
    }

    public List<String> getUpdate() {
        return update;
    }
}
