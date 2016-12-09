package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetFoldersList;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetScriptById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateScript;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 10/14/16
 */

public class Script implements Serializable {

    public void runScript(String scriptId, Object dataPoolForScript, CallbackSendScript callbackRunScript) {
        ScorocodeSdk.runScript(scriptId, dataPoolForScript, callbackRunScript);
    }

    public void runScript(String scriptId, CallbackSendScript callbackRunScript) {
        ScorocodeSdk.runScript(scriptId, null, callbackRunScript);
    }

    public void createScript(ScorocodeScript script, CallbackCreateScript callback) {
        ScorocodeSdk.createScript(script, callback);
    }

    public void getScriptById(String scriptId, CallbackGetScriptById callback) {
        ScorocodeSdk.getScriptById(scriptId, callback);
    }

    public void updateScript(ScorocodeScript newScript, String scriptToUpdateId, CallbackUpdateScript callback) {
        ScorocodeSdk.updateScript(newScript, scriptToUpdateId, callback);
    }

    public void deleteScript(String scriptToDeleteId, CallbackDeleteScript callback) {
        ScorocodeSdk.deleteScriptById(scriptToDeleteId, callback);
    }

}
