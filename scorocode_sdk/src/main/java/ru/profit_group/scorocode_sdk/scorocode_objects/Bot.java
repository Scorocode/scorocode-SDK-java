package ru.profit_group.scorocode_sdk.scorocode_objects;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateBot;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteBot;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetBotList;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateBot;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 12/9/16
 */

public class Bot {
    public void getBotsList(CallbackGetBotList callback) {
        ScorocodeSdk.getBotList(callback);
    }

    public void createBot(String botName, String telegramBotId, String scriptId, boolean isBotActive, CallbackCreateBot callback) {
        ScorocodeSdk.createBot(botName, telegramBotId, scriptId, isBotActive, callback);
    }

    public void updateBot(String botId, ScorocodeBot newBotInfo, CallbackUpdateBot callback) {
        ScorocodeSdk.updateBot(botId, newBotInfo, callback);
    }

    public void deleteBot(String botId, CallbackDeleteBot callback) {
        ScorocodeSdk.deleteBot(botId, callback);
    }
}
