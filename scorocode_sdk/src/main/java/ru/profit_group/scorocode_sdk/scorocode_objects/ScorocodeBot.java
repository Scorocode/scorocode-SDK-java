package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class ScorocodeBot {

    private String _id;
    private String name;
    private String botId;
    private String scriptId;
    private boolean isActive;

    public ScorocodeBot(String botName, String telegramBotId, String scriptId, boolean isActive) {
        this.name = botName;
        this.botId = telegramBotId;
        this.scriptId = scriptId;
        this.isActive = isActive;
    }

    public void setBotId(String _id) {
        this._id = _id;
    }

    public String getBotId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getTelegramBotId() {
        return botId;
    }

    public String getScriptId() {
        return scriptId;
    }

    public boolean isBotActive() {
        return isActive;
    }
}
