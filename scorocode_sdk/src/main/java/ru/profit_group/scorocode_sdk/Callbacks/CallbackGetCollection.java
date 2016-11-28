package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;

/**
 * Created by Peter Staranchuk on 11/28/16
 */

public interface CallbackGetCollection {
    void onRequestSucceed(ScorocodeCollection collection);
    void onRequestFailed(String errorCode, String errorMessage);
}
