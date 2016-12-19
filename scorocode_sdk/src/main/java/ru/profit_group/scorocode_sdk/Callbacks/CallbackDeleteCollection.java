package ru.profit_group.scorocode_sdk.Callbacks;

/**
 * Created by Peter Staranchuk on 11/28/16
 */

public interface CallbackDeleteCollection {
    void onCollectionDeleted();
    void onDeletionFailed(String errorCodes, String errorMessage);
}
