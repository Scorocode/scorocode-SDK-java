package ru.profit_group.scorocode_sdk.scorocode_objects;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetApplicationInfo;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Peter Staranchuk on 12/9/16
 */

public class ApplicationInfo {
    public void getApplicationInfo(CallbackGetApplicationInfo callback) {
        ScorocodeSdk.getApplicationInfo(callback);
    }

    public Observable<ScorocodeApplicationInfo> getApplicationInfo() {
        return Observable.create(new Observable.OnSubscribe<ScorocodeApplicationInfo>() {
            @Override
            public void call(final Subscriber<? super ScorocodeApplicationInfo> subscriber) {
                getApplicationInfo(new CallbackGetApplicationInfo() {
                    @Override
                    public void onRequestSucceed(ScorocodeApplicationInfo appInfo) {
                        subscriber.onNext(appInfo);
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
}
