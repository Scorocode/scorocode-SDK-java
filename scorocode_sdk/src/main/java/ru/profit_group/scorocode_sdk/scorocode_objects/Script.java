package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.io.Serializable;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetScriptById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateScript;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 10/14/16
 */

public class Script implements Serializable {

    private boolean isDebugMode;

    public Script(boolean isDebugMode) {
        this.isDebugMode = isDebugMode;
    }

    public Script() {
        this.isDebugMode = false;
    }

    public void runScript(String scriptId, Object dataPoolForScript, CallbackSendScript callbackRunScript) {
        ScorocodeSdk.runScript(scriptId, dataPoolForScript, isDebugMode, callbackRunScript);
    }

    public void runScript(String scriptId, CallbackSendScript callbackRunScript) {
        ScorocodeSdk.runScript(scriptId, null, isDebugMode, callbackRunScript);
    }

    public void createScript(ScorocodeScript script, CallbackCreateScript callback) {
        ScorocodeSdk.createScript(script, callback);
    }

    public void getScriptById(String scriptId, CallbackGetScriptById callback) {
        ScorocodeSdk.getScriptById(scriptId, callback);
    }

    public void updateScript(String scriptToUpdateId, ScorocodeScript newScriptInfo, CallbackUpdateScript callback) {
        ScorocodeSdk.updateScript(newScriptInfo, scriptToUpdateId, callback);
    }

    public void deleteScript(String scriptToDeleteId, CallbackDeleteScript callback) {
        ScorocodeSdk.deleteScriptById(scriptToDeleteId, callback);
    }

}
