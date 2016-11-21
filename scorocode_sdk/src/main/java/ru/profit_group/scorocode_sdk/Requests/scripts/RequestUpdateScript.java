package ru.profit_group.scorocode_sdk.Requests.scripts;

import java.util.List;

import ru.profit_group.scorocode_sdk.scorocode_objects.CloudCode;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class RequestUpdateScript extends RequestCreateScript {

    public RequestUpdateScript(String masterKey, String applicationId, CloudCode cloudCode, List<String> ACL) {
        super(masterKey, applicationId, cloudCode, ACL);
    }
}
