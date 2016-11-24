package ru.profit_group.scorocode_sdk.Requests.scripts;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestDeleteScriptById extends AppBase {

    private String script;

    public RequestDeleteScriptById(ScorocodeSdkStateHolder stateHolder, String scriptId) {
        super(stateHolder);
        this.script = scriptId;
    }
}
