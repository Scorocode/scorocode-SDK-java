package ru.profit_group.scorocode_sdk.Requests.scripts;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestGetScriptById extends AppBase {

    private String script;

    public RequestGetScriptById(ScorocodeCoreInfo stateHolder, String scriptId) {
        super(stateHolder);
        this.script = scriptId;
    }
}
