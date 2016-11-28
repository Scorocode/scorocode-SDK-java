package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public class ScorocodeApplicationInfo {
    private String _id;
    private String appId;
    private String name;
    private String description;
    private String userId;
    private String serverId;
    private Limits limits;
    private Map<String, ScorocodeCollection> schemas;
    private ScorocodePublicKeys accessKeys;
    private ScorocodeClientKeys clientKeys;
    private boolean readonly;
    private ScorocodeACLPublic ACLPublic;
    private Settings settings;
    private StorageInfo storage;
    private String stringId;

    public ScorocodeApplicationInfo(
            String id, String appId, String name, String description, String userId,
            String serverId, Limits limits, Map<String, ScorocodeCollection> schemas, ScorocodePublicKeys accessKeys,
            ScorocodeClientKeys clientKeys, boolean readonly, ScorocodeACLPublic ACLPublic,
            Settings settings, StorageInfo storage, String stringId) {
        this._id = id;
        this.appId = appId;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.serverId = serverId;
        this.limits = limits;
        this.schemas = schemas;
        this.accessKeys = accessKeys;
        this.clientKeys = clientKeys;
        this.readonly = readonly;
        this.ACLPublic = ACLPublic;
        this.settings = settings;
        this.storage = storage;
        this.stringId = stringId;

    }

    public String getId() {
        return _id;
    }

    public String getAppId() {
        return appId;
    }

    public String getApplicationName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUserId() {
        return userId;
    }

    public String getServerId() {
        return serverId;
    }

    public Limits getLimits() {
        return limits;
    }

    public List<ScorocodeCollection> getCollections() {
        List<ScorocodeCollection> scorocodeCollectionList = new ArrayList<>();
        for(String key : schemas.keySet()) {
            scorocodeCollectionList.add(schemas.get(key));
        }
        return scorocodeCollectionList;
    }

    public ScorocodePublicKeys getAccessKeys() {
        return accessKeys;
    }

    public ScorocodeClientKeys getClientKeys() {
        return clientKeys;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public ScorocodeACLPublic getACLPublic() {
        return ACLPublic;
    }

    public Settings getSettings() {
        return settings;
    }

    public StorageInfo getStorage() {
        return storage;
    }

    public String getStringId() {
        return stringId;
    }

}
