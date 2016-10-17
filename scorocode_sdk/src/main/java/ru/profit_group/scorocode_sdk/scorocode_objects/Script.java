package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendScript;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 10/14/16
 */

public class Script {

    public void runScript(String scriptId, HashMap<String, Object> dataPoolForScript, CallbackSendScript callbackRunScript) {
        ScorocodeSdk.runScript(scriptId, dataPoolForScript, callbackRunScript);
    }

    public void runScript(String scriptId, CallbackSendScript callbackRunScript) {
        ScorocodeSdk.runScript(scriptId, null, callbackRunScript);
    }
}