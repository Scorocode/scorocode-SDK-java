package ru.profit_group.scorocode_sdk.Requests.bots;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeBot;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestDeleteBot extends AppBase {

    private ScorocodeBot bot;

    public RequestDeleteBot(ScorocodeCoreInfo stateHolder, String botId) {
        super(stateHolder);
        this.bot = new ScorocodeBot().setBotId(botId);
    }
}
