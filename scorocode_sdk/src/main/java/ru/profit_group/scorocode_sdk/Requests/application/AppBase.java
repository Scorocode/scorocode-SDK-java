package ru.profit_group.scorocode_sdk.Requests.application;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class AppBase {
    protected String acc;
    protected String app;
    protected String cli;

    public AppBase(ScorocodeSdkStateHolder stateHolder) {
        this.acc = stateHolder.getMasterKey();
        this.app = stateHolder.getApplicationId();
        this.app = stateHolder.getClientKey();
    }
}
