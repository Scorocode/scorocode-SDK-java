package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.io.Serializable;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetScriptById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateScript;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import rx.Observable;
import rx.Subscriber;

import static android.R.attr.path;

/**
 * Created by Peter Staranchuk on 10/14/16
 */

public class Script implements Serializable {

    private boolean isDebugMode;

    public Script(boolean isDebugMode) {
        this.isDebugMode = isDebugMode;
    }

    public Script() {
        this.isDebugMode = false;
    }

    public void runScript(String scriptId, Object dataPoolForScript, CallbackSendScript callbackRunScript) {
        ScorocodeSdk.runScript(scriptId, dataPoolForScript, false, null, isDebugMode, callbackRunScript);
    }

    public void runScriptByPath(String path, Object dataPoolForScript, CallbackSendScript callbackRunScript) {
        ScorocodeSdk.runScript(null, dataPoolForScript, true, path, isDebugMode, callbackRunScript);
    }

    public Observable runScript(final String scriptId, final Object dataPoolForScript) {
        return runScript(scriptId, false, null, dataPoolForScript);
    }

    public Observable runScriptByPath(final String path, final Object dataPoolForScript) {
        return runScript(null, true, path, dataPoolForScript);
    }

    public Observable runScript(final String scriptId, boolean isRunByPath, String path, final Object dataPoolForScript) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                ScorocodeSdk.runScript(scriptId, dataPoolForScript, false, "", isDebugMode, new CallbackSendScript() {
                    @Override
                    public void onScriptSent() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onScriptSendFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void runScript(String scriptId, CallbackSendScript callbackRunScript) {
        ScorocodeSdk.runScript(scriptId, null, false, "", isDebugMode, callbackRunScript);
    }

    public Observable runScript(final String scriptId) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                ScorocodeSdk.runScript(scriptId, null, false, "", isDebugMode, new CallbackSendScript() {
                    @Override
                    public void onScriptSent() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onScriptSendFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }


    public void createScript(ScorocodeScript script, CallbackCreateScript callback) {
        ScorocodeSdk.createScript(script, callback);
    }

    public Observable<ScorocodeScript> createScript(final ScorocodeScript script) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeScript>() {
            @Override
            public void call(final Subscriber<? super ScorocodeScript> subscriber) {
                createScript(script, new CallbackCreateScript() {
                    @Override
                    public void onScriptCreated(ScorocodeScript script) {
                        subscriber.onNext(script);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onCreationFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void getScriptById(String scriptId, CallbackGetScriptById callback) {
        ScorocodeSdk.getScriptById(scriptId, callback);
    }

    public Observable<ScorocodeScript> getScriptById(final String scriptId) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeScript>() {
            @Override
            public void call(final Subscriber<? super ScorocodeScript> subscriber) {
                getScriptById(scriptId, new CallbackGetScriptById() {
                    @Override
                    public void onRequestSucceed(ScorocodeScript script) {
                        subscriber.onNext(script);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onRequestFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void updateScript(String scriptToUpdateId, ScorocodeScript newScriptInfo, CallbackUpdateScript callback) {
        ScorocodeSdk.updateScript(newScriptInfo, scriptToUpdateId, callback);
    }

    public Observable<ScorocodeScript> updateScript(final String scriptToUpdateId, final ScorocodeScript newScriptInfo) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeScript>() {
            @Override
            public void call(final Subscriber<? super ScorocodeScript> subscriber) {
                updateScript(scriptToUpdateId, newScriptInfo, new CallbackUpdateScript() {
                    @Override
                    public void onUpdateScriptSucceed(ScorocodeScript scorocodeScript) {
                        subscriber.onNext(scorocodeScript);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onUpdateScriptFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void deleteScript(String scriptToDeleteId, CallbackDeleteScript callback) {
        ScorocodeSdk.deleteScriptById(scriptToDeleteId, callback);
    }

    public Observable<ScorocodeScript> deleteScript(final String scriptToDeleteId) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeScript>() {
            @Override
            public void call(final Subscriber<? super ScorocodeScript> subscriber) {
                deleteScript(scriptToDeleteId, new CallbackDeleteScript() {
                    @Override
                    public void onScriptDeleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onDeletionFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

}
