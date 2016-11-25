package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeApplicationInfo;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public interface CallbackGetApplicationInfo {
    void onRequestSucceed(ScorocodeApplicationInfo appInfo);
    void onRequestFailed(String errorCode, String errorMessage);
}
