package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.List;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class ScorocodeCollection {
    private String id;
    private String name;
    private boolean useDocsACL;
    private List<String> ACL; // TODO check

    public ScorocodeCollection(String id, String name, boolean useDocsACL, List<String> ACL) {
        this.id = id;
        this.name = name;
        this.useDocsACL = useDocsACL;
        this.ACL = ACL;
    }
}
