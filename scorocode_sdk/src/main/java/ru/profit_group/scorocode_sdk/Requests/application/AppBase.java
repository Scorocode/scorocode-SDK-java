package ru.profit_group.scorocode_sdk.Requests.application;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class AppBase {
    protected String acc;
    protected String app;
    protected String cli;

    public AppBase(ScorocodeCoreInfo stateHolder) {
        this.acc = stateHolder.getMasterKey();
        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
    }
}
