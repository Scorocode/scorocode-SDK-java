package ru.profit_group.scorocode_sdk.Requests.collection;

import java.util.HashMap;
import java.util.Map;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.Trigger;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestChangeCollectionTriggers extends AppBase {

    private String coll;
    private Map<String, Trigger> triggers;

    public RequestChangeCollectionTriggers(String masterKey, String applicationId, String collectionName) {
        super(masterKey, applicationId);
        this.coll = collectionName;
        triggers = new HashMap<>();
    }

    public RequestChangeCollectionTriggers setBeforeTrigger(String code, boolean isActive) {
        setTrigger("beforeInsert", code, isActive);
        return this;
    }

    public RequestChangeCollectionTriggers setAfterTrigger(String code, boolean isActive) {
        setTrigger("afterInsert", code, isActive);
        return this;
    }

    public RequestChangeCollectionTriggers setBeforeUpdateTrigger(String code, boolean isActive) {
        setTrigger("beforeUpdate", code, isActive);
        return this;
    }

    public RequestChangeCollectionTriggers setAfterUpdateTrigger(String code, boolean isActive) {
        setTrigger("afterUpdate", code, isActive);
        return this;
    }

    public RequestChangeCollectionTriggers setBeforeRemoveTrigger(String code, boolean isActive) {
        setTrigger("beforeRemove", code, isActive);
        return this;
    }

    public RequestChangeCollectionTriggers setAfterRemoveTrigger(String code, boolean isActive) {
        setTrigger("afterRemove", code, isActive);
        return this;
    }

    private RequestChangeCollectionTriggers setTrigger(String triggerName, String code, boolean isActive) {
        triggers.put(triggerName, new Trigger(code, isActive));
        return this;
    }
}
