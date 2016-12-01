package ru.profit_group.scorocode_sdk.Requests.scripts;

import ru.profit_group.scorocode_sdk.Requests.application.AppBase;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeScript;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestCreateScript extends AppBase {

    private ScorocodeScript cloudCode;

    public RequestCreateScript(ScorocodeCoreInfo stateHolder, ScorocodeScript cloudCode) {
        super(stateHolder);
        this.cloudCode = cloudCode;
    }
}
