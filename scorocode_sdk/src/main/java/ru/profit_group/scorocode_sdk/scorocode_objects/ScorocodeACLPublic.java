package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public class ScorocodeACLPublic {
    private boolean create;
    private boolean read;
    private boolean remove;
    private boolean update;

    public ScorocodeACLPublic(boolean create, boolean read, boolean remove, boolean update) {
        this.create = create;
        this.read = read;
        this.remove = remove;
        this.update = update;
    }

    public boolean isCreatePermitted() {
        return create;
    }

    public boolean isReadPermitted() {
        return read;
    }

    public boolean isRemovePermitted() {
        return remove;
    }

    public boolean isUpdatePermitted() {
        return update;
    }
}
