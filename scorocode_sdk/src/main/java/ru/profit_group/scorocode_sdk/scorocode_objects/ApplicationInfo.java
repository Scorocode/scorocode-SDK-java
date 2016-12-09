package ru.profit_group.scorocode_sdk.scorocode_objects;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetApplicationInfo;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 12/9/16
 */

public class ApplicationInfo {
    public void getApplicationInfo(CallbackGetApplicationInfo callback) {
        ScorocodeSdk.getApplicationInfo(callback);
    }
}
