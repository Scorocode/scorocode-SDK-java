package ru.profit_group.scorocode_sdk.Callbacks;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public interface CallbackDeleteScript {
    void onScriptDeleted();
    void onDeletionFailed(String errorCode, String errorMessage);
}
