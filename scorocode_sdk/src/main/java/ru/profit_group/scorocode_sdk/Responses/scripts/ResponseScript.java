package ru.profit_group.scorocode_sdk.Responses.scripts;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeScript;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public class ResponseScript extends ResponseCodes {
    private ScorocodeScript script;

    public ResponseScript(ScorocodeScript script) {
        this.script = script;
    }

    public ScorocodeScript getScript() {
        return script;
    }
}
