package ru.profit_group.scorocode_sdk.Callbacks;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public interface CallbackDeleteCollectionIndex {
    void onIndexDeleted();
    void onIndexDeletionFailed(String errorCode, String errorMessage);
}
