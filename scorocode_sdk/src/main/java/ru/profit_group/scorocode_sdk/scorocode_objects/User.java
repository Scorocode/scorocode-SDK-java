package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.io.Serializable;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackLoginUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackLogoutUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRegisterUser;
import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Peter Staranchuk on 10/7/16
 */

public class User extends Document implements Serializable {

    public User() {
        super("users");
    }

    public void login(String email, String password, final CallbackLoginUser callback) {
        ScorocodeSdk.loginUser(email, password, new CallbackLoginUser() {
            @Override
            public void onLoginSucceed(ResponseLogin responseLogin) {
                if(responseLogin != null && responseLogin.getResult() != null && responseLogin.getResult().getUserInfo() != null) {
                    documentContent = responseLogin.getResult().getUserInfo();
                    if(documentContent != null) {
                        documentId = documentContent.getId();
                    }
                }
                callback.onLoginSucceed(responseLogin);
            }

            @Override
            public void onLoginFailed(String errorCode, String errorMessage) {
                callback.onLoginFailed(errorCode, errorMessage);
            }
        });
    }

    public Observable<ResponseLogin> login(final String email, final String password) {
        return Observable.create(new Observable.OnSubscribe<ResponseLogin>() {
            @Override
            public void call(final Subscriber<? super ResponseLogin> subscriber) {
                login(email, password, new CallbackLoginUser() {
                    @Override
                    public void onLoginSucceed(ResponseLogin responseLogin) {
                        subscriber.onNext(responseLogin);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onLoginFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void logout(final CallbackLogoutUser callback) {
        ScorocodeSdk.logoutUser(new CallbackLogoutUser() {
            @Override
            public void onLogoutSucceed() {
                documentId = null;
                documentContent = null;
                callback.onLogoutSucceed();
            }

            @Override
            public void onLogoutFailed(String errorCode, String errorMessage) {
                callback.onLogoutFailed(errorCode, errorMessage);
            }
        });
    }

    public Observable logout() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                logout(new CallbackLogoutUser() {
                    @Override
                    public void onLogoutSucceed() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onLogoutFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void register(String username, String email, String password, DocumentInfo documentContent, CallbackRegisterUser callback) {
        ScorocodeSdk.registerUser(username, email, password, documentContent, callback);
    }

    public Observable register(final String username, final String email, final String password, final DocumentInfo documentContent) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                register(username, email, password, documentContent, new CallbackRegisterUser() {
                    @Override
                    public void onRegisterSucceed() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onRegisterFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void register(String username, String email, String password, CallbackRegisterUser callback) {
        register(username, email, password, null, callback);
    }

    public Observable register(final String username, final String email, final String password) {
        return register(username, email, password, new DocumentInfo());
    }
}
