package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class ScorocodeBot {

    private String _id;
    private String name;
    private String botId;
    private String scriptId;
    private boolean isActive;

    public ScorocodeBot() {}

    public ScorocodeBot(String botName, String telegramBotId, String scriptId, boolean isActive) {
        this.name = botName;
        this.botId = telegramBotId;
        this.scriptId = scriptId;
        this.isActive = isActive;
    }

    @NonNull
    public String getBotId() {
        return _id == null? "" : _id;
    }

    @NonNull
    public String getName() {
        return name == null? "" : name;
    }

    @NonNull
    public String getTelegramBotId() {
        return botId == null? "" : botId;
    }

    @NonNull
    public String getScriptId() {
        return scriptId == null? "" : scriptId;
    }

    public boolean isBotActive() {
        return isActive;
    }

    @NonNull
    public ScorocodeBot setBotId(String id) {
        this._id = id;
        return this;
    }

    @NonNull
    public ScorocodeBot setTelegramBotId(String id) {
        this._id = id;
        return this;
    }

    @NonNull
    public ScorocodeBot setBotName(String name) {
        this.name = name;
        return this;
    }

    @NonNull
    public ScorocodeBot setScriptId(String scriptId) {
        this.scriptId = scriptId;
        return this;
    }

    @NonNull
    public ScorocodeBot setActive(boolean active) {
        isActive = active;
        return this;
    }
}
