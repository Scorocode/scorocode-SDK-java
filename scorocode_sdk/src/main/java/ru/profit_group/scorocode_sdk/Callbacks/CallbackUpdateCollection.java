package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;

/**
 * Created by Peter Staranchuk on 11/28/16
 */

public interface CallbackUpdateCollection {
    void onCollectionUpdated(ScorocodeCollection collection);
    void onUpdateFailed(String errorCode, String errorMessage);
}
