package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeScript;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public interface CallbackCreateScript {
    void onScriptCreated(ScorocodeScript script);
    void onCreationFailed(String errorCode, String errorMessage);
}
