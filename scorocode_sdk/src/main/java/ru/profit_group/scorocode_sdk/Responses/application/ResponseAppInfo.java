package ru.profit_group.scorocode_sdk.Responses.application;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeApplicationInfo;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public class ResponseAppInfo extends ResponseCodes {
    private ScorocodeApplicationInfo app;

    public ResponseAppInfo(ScorocodeApplicationInfo applicationInfo) {
        this.app = applicationInfo;
    }

    public ScorocodeApplicationInfo getApplicationInfo() {
        return app;
    }
}
