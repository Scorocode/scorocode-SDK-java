package ru.profit_group.scorocode_sdk.Responses.bots;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeBot;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public class ResponseBot extends ResponseCodes {
    private ScorocodeBot bot;

    public ResponseBot(ScorocodeBot bot) {
        this.bot = bot;
    }

    public ScorocodeBot getBot() {
        return bot;
    }
}
