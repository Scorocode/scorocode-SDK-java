package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdateById;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeScript;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public interface CallbackUpdateScript {
    void onUpdateScriptSucceed(ScorocodeScript scorocodeScript);
    void onUpdateScriptFailed(String errorCode, String errorMessage);
}
