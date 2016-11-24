package ru.profit_group.scorocode_sdk.scorocode_objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public class Limits {
    @SerializedName("rps")
    @Expose
    private Long rps;

    @SerializedName("store")
    @Expose
    private Long store;

    @SerializedName("pushValue")
    @Expose
    private Long pushValue;

    @SerializedName("pushUsed")
    @Expose
    private Long pushUsed;

    @SerializedName("developers")
    @Expose
    private Long developers;

    @SerializedName("ws")
    @Expose
    private Long ws;

    @SerializedName("scriptTimeout")
    @Expose
    private Long scriptTimeout;

    public Limits(Long rps, Long store, Long pushValue, Long pushUsed,
                  Long developers, Long ws, Long scriptTimeout) {
        this.rps = rps;
        this.store = store;
        this.pushValue = pushValue;
        this.pushUsed = pushUsed;
        this.developers = developers;
        this.ws = ws;
        this.scriptTimeout = scriptTimeout;
    }

    public Long getRequestPerSecondLimit() {
        return rps;
    }

    public Long getStoreAmountLimit() {
        return store;
    }

    public Long getPushCountLimit() {
        return pushValue;
    }

    public Long getPushUsedNumber() {
        return pushUsed;
    }

    public Long getDevelopersLimit() {
        return developers;
    }

    public Long getWebsocketsConnectionLimit() {
        return ws;
    }

    public Long getScriptTimeout() {
        return scriptTimeout;
    }
}
