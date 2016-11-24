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

    public ScorocodeBot(String botId, String botName, String telegramBotId, String scriptId, boolean isActive) {
        this._id = botId;
        this.name = botName;
        this.botId = telegramBotId;
        this.scriptId = scriptId;
        this.isActive = isActive;
    }

}
