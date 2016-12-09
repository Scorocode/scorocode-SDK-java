package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;

/**
 * Created by Peter Staranchuk on 12/9/16
 */

public interface CallbackDeleteBot {
    void onBotDeleted();
    void onDeletionFailed(String errorCode, String errorMessage);
}
