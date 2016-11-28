package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;

/**
 * Created by Peter Staranchuk on 11/28/16
 */

public interface CallbackCreateCollection {
    void onCollectionCreated(ScorocodeCollection collection);
    void onCreationFailed(String errorCode, String errorMessage);
}
