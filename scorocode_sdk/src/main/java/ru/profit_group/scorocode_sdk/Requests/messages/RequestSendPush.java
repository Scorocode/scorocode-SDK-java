package ru.profit_group.scorocode_sdk.Requests.messages;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;

import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 5/10/2016
 */

public class RequestSendPush {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private Map<String, Object> query;
    private MessagePush msg;
    private boolean debug;

    public RequestSendPush(
            @NonNull ScorocodeCoreInfo stateHolder,
            @NonNull String coll,
            @Nullable Query query,
            @NonNull MessagePush msg, boolean debug) {

        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterOrMessageKey();
        this.sess = stateHolder.getSessionId();
        this.coll = coll;
        if(query != null) {
            this.query = query.getQueryInfo().getInfo();
        }
        this.msg = msg;
        this.debug = debug;
    }
}
