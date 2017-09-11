package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateBot;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteBot;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetBotList;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateBot;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Peter Staranchuk on 12/9/16
 */

public class Bot {
    public void getBotsList(CallbackGetBotList callback) {
        ScorocodeSdk.getBotList(callback);
    }

    public Observable<List<ScorocodeBot>> getBotsList() {
        return Observable.create(new Observable.OnSubscribe<List<ScorocodeBot>>() {
            @Override
            public void call(final Subscriber<? super List<ScorocodeBot>> subscriber) {
                ScorocodeSdk.getBotList(new CallbackGetBotList() {
                    @Override
                    public void onRequestSucceed(List<ScorocodeBot> botList) {
                        subscriber.onNext(botList);
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


    public void createBot(ScorocodeBot botInfo, CallbackCreateBot callback) {
        ScorocodeSdk.createBot(botInfo.getBotName(), botInfo.getTelegramBotId(), botInfo.getScriptId(), botInfo.isBotActive(), callback);
    }

    public Observable<ScorocodeBot> createBot(final ScorocodeBot botInfo) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeBot>() {
            @Override
            public void call(final Subscriber<? super ScorocodeBot> subscriber) {
                ScorocodeSdk.createBot(botInfo.getBotName(), botInfo.getTelegramBotId(), botInfo.getScriptId(), botInfo.isBotActive(), new CallbackCreateBot() {
                    @Override
                    public void onBotCreated(ScorocodeBot bot) {
                        subscriber.onNext(bot);
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

    public void updateBot(String botId, ScorocodeBot newBotInfo, CallbackUpdateBot callback) {
        ScorocodeSdk.updateBot(botId, newBotInfo, callback);
    }

    public Observable<ScorocodeBot> updateBot(final String botId, final ScorocodeBot newBotInfo) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeBot>() {
            @Override
            public void call(final Subscriber<? super ScorocodeBot> subscriber) {
                ScorocodeSdk.updateBot(botId, newBotInfo, new CallbackUpdateBot() {
                    @Override
                    public void onBotUpdated(ScorocodeBot bot) {
                        subscriber.onNext(bot);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onUpdateFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void deleteBot(String botId, CallbackDeleteBot callback) {
        ScorocodeSdk.deleteBot(botId, callback);
    }

    public Observable<Void> deleteBot(final String botId) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                ScorocodeSdk.deleteBot(botId, new CallbackDeleteBot() {
                    @Override
                    public void onBotDeleted() {
                        subscriber.onNext(null);
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
