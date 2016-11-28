package ru.profit_group.scorocode_sdk.Callbacks;

import java.util.List;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;

/**
 * Created by Peter Staranchuk on 11/28/16
 */

public interface CallbackGetCollectionsList {
    void onRequestSucceed(List<ScorocodeCollection> collections);
    void onRequestFailed(String errorCode, String errorMessage);
}
