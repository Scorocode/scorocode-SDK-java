package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.io.Serializable;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendPush;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendSms;
import ru.profit_group.scorocode_sdk.Requests.messages.MessagePush;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageSms;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Peter Staranchuk on 10/7/16
 */

public class Message implements Serializable {

    private boolean isDebugMode;

    public Message(boolean isDebugMode) {
        this.isDebugMode = isDebugMode;
    }

    public Message() {
        this.isDebugMode = false;
    }

    public void sendPush(MessagePush messagePush, Query query, CallbackSendPush callback) throws IllegalStateException {
        ScorocodeSdk.sendPush(query.getCollectionName(), query, messagePush, isDebugMode, callback);
    }

    public Observable<Void> sendPush(final MessagePush messagePush, final Query query) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                sendPush(messagePush, query, new CallbackSendPush() {
                    @Override
                    public void onPushSent() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onPushSendFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void sendPush(MessagePush messagePush, CallbackSendPush callback) throws IllegalStateException {
        ScorocodeSdk.sendPush("users", null, messagePush, isDebugMode, callback);
    }

    public Observable<Void> sendPush(final MessagePush messagePush) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                sendPush(messagePush, null, new CallbackSendPush() {
                    @Override
                    public void onPushSent() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onPushSendFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void sendSms(MessageSms messageSms, Query query, CallbackSendSms callback) throws IllegalStateException {
        ScorocodeSdk.sendSms(query.getCollectionName(), query, messageSms, isDebugMode, callback);
    }

    public Observable<Void> sendSms(final MessageSms messageSms, final Query query) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                sendSms(messageSms, query, new CallbackSendSms() {
                    @Override
                    public void onSmsSent() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onSmsSendFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void sendSms(MessageSms messageSms, CallbackSendSms callback) throws IllegalStateException {
        ScorocodeSdk.sendSms("users", null, messageSms, isDebugMode, callback);
    }

    public Observable<Void> sendSms(final MessageSms messageSms) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                sendSms(messageSms, null, new CallbackSendSms() {
                    @Override
                    public void onSmsSent() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onSmsSendFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

}
