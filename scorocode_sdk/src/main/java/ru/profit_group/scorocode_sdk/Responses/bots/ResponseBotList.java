package ru.profit_group.scorocode_sdk.Responses.bots;

import java.util.ArrayList;
import java.util.List;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeBot;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public class ResponseBotList extends ResponseCodes {
    private List<ScorocodeBot> items;

    public ResponseBotList(List<ScorocodeBot> items) {
        this.items = items;
    }

    public List<ScorocodeBot> getBotsList() {
        return items == null? (new ArrayList<ScorocodeBot>()) : items;
    }
}
