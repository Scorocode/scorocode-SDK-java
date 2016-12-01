package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeField;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public interface CallbackAddField {
    void onFieldAdded(ScorocodeField field);
    void onAddFieldFailed(String errorCode, String errorMessage);
}
