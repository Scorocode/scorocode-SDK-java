package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateNewFolder;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFolder;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetFoldersList;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Peter Staranchuk on 12/9/16
 */

public class Folders {
    public void getFoldersList(String pathToFolder, CallbackGetFoldersList callback) {
        ScorocodeSdk.getFoldersList(pathToFolder, callback);
    }

    public Observable<List<ScorocodeFolder>> getFoldersList(final String pathToFolder) {
        return Observable.create(new Observable.OnSubscribe<List<ScorocodeFolder>>() {
            @Override
            public void call(final Subscriber<? super List<ScorocodeFolder>> subscriber) {
                getFoldersList(pathToFolder, new CallbackGetFoldersList() {
                    @Override
                    public void onRequestSucceed(List<ScorocodeFolder> folderList) {
                        subscriber.onNext(folderList);
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

    public void createFolder(String pathToFolder, CallbackCreateNewFolder callback) {
        ScorocodeSdk.createFolder(pathToFolder, callback);
    }

    public Observable<Void> createFolder(final String pathToFolder) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                createFolder(pathToFolder, new CallbackCreateNewFolder() {
                    @Override
                    public void onFolderCreated() {
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

    public void deleteFolder(String pathToFolder, CallbackDeleteFolder callback) {
        ScorocodeSdk.deleteFolder(pathToFolder, callback);
    }

    public Observable<Void> deleteFolder(final String pathToFolder) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                deleteFolder(pathToFolder, new CallbackDeleteFolder() {
                    @Override
                    public void onFolderDeleted() {
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
