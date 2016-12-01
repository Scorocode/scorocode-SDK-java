package ru.profit_group.scorocode_sdk.Requests.bots;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeBot;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestCreateBot extends AppBase {

    private ScorocodeBot bot;

    public RequestCreateBot(ScorocodeCoreInfo stateHolder, String botName, String botId, String scriptId, boolean isActive) {
        super(stateHolder);
        this.bot = new ScorocodeBot(null, botName, botId, scriptId, isActive);
    }
}
