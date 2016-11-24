package ru.profit_group.scorocode_sdk.Requests.scripts;

import java.util.List;

import ru.profit_group.scorocode_sdk.scorocode_objects.CloudCode;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeACL;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestUpdateScript extends RequestCreateScript {

    public RequestUpdateScript(ScorocodeSdkStateHolder stateHolder, CloudCode cloudCode, ScorocodeACL ACL) {
        super(stateHolder, cloudCode, ACL);
    }
}
