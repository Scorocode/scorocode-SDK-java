package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.List;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public class ScorocodeUserAuth {
    private String _id;
    private List<String> roles;

    public ScorocodeUserAuth(String id, List<String> roles) {
        this._id = id;
        this.roles = roles;
    }

    public String getId() {
        return _id;
    }

    public List<String> getRoles() {
        return roles;
    }
}
