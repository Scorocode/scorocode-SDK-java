package ru.profit_group.scorocode_sdk.Callbacks;

import java.util.List;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeBot;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public interface CallbackGetBotList {
    void onRequestSucceed(List<ScorocodeBot> botList);
    void onRequestFailed(String errorCode, String errorMessage);
}
