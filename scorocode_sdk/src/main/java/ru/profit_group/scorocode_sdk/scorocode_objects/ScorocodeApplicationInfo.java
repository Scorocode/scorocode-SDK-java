package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
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
    private String npm;

    public ScorocodeApplicationInfo(
            String id, String appId, String name, String description, String userId,
            String serverId, Limits limits, Map<String, ScorocodeCollection> schemas, ScorocodePublicKeys accessKeys,
            ScorocodeClientKeys clientKeys, boolean readonly, ScorocodeACLPublic ACLPublic,
            Settings settings, StorageInfo storage, String stringId, String npm) {
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
        this.npm = npm;
    }

    @NonNull
    public String getId() {
        return _id == null? "" : _id;
    }

    @NonNull
    public String getAppId() {
        return appId == null? "" : appId;
    }

    @NonNull
    public String getApplicationName() {
        return name == null? "" : name;
    }

    @NonNull
    public String getDescription() {
        return description == null? "" : description;
    }

    @NonNull
    public String getUserId() {
        return userId == null? "" : userId;
    }

    @NonNull
    public String getServerId() {
        return serverId == null? "" : serverId;
    }

    @NonNull
    public Limits getLimits() {
        return limits == null? new Limits(0L,0L,0L,0L,0L,0L,0L) : limits;
    }

    @NonNull
    public List<ScorocodeCollection> getCollections() {
        List<ScorocodeCollection> scorocodeCollectionList = new ArrayList<>();
        for(String key : schemas.keySet()) {
            scorocodeCollectionList.add(schemas.get(key));
        }
        return scorocodeCollectionList;
    }

    @NonNull
    public ScorocodePublicKeys getAccessKeys() {
        return accessKeys == null? new ScorocodePublicKeys("","","","","") : accessKeys;
    }

    @NonNull
    public ScorocodeClientKeys getClientKeys() {
        return clientKeys == null? new ScorocodeClientKeys("","","","") : clientKeys;
    }

    @NonNull
    public boolean isReadonly() {
        return readonly;
    }

    @NonNull
    public ScorocodeACLPublic getACLPublic() {
        return ACLPublic == null? new ScorocodeACLPublic(false, false, false, false) : ACLPublic;
    }

    @NonNull
    public Settings getSettings() {
        return settings == null? new Settings(false, 0L, "", "", (new HashMap<String, MailTemplate>()), "") : settings;
    }

    @NonNull
    public StorageInfo getStorage() {
        return storage == null? new StorageInfo("","") : storage;
    }

    @NonNull
    public String getStringId() {
        return stringId == null? "" : stringId;
    }

    @NonNull
    public String getNpm() {
        return npm == null? "" : npm;
    }
}
