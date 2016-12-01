package ru.profit_group.scorocode_sdk.Requests.scripts;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeScript;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeACL;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestUpdateScript extends RequestCreateScript {
    private String script;

    public RequestUpdateScript(ScorocodeCoreInfo stateHolder, String scriptId, ScorocodeScript script) {
        super(stateHolder, script);
        this.script = scriptId;
    }
}
