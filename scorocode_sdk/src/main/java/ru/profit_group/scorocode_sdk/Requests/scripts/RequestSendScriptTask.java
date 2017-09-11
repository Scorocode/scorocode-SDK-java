package ru.profit_group.scorocode_sdk.Requests.scripts;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;

/**
 * Created by Peter Staranchuk on 5/10/2016
 */

public class RequestSendScriptTask {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String script;
    private Object pool;
    private boolean isRunByPath;
    private String path;
    private boolean debug;

    public RequestSendScriptTask(
            @NonNull ScorocodeCoreInfo stateHolder,
            @Nullable String scriptId,
            @Nullable Object dataPoolForScript, boolean isRunByPath, String path, boolean debug) {
        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterOrScriptKey();
        this.sess = stateHolder.getSessionId();
        this.script = scriptId;
        this.pool = dataPoolForScript;
        this.isRunByPath = isRunByPath;
        this.path = path;
        this.debug = debug;
    }
}
