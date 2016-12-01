package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeTriggers;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public interface CallbackUpdateCollectionTriggers {
    void onTriggersUpdated(ScorocodeTriggers triggers);
    void onUpdateFailed(String errorCode, String errorMessage);
}
