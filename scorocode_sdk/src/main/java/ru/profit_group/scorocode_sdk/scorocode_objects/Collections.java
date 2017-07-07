package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackAddField;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCloneCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateCollectionIndex;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteCollectionIndex;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteField;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetCollectionsList;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateCollectionTriggers;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Peter Staranchuk on 12/9/16
 */

public class Collections {

    public void getCollectionsList(CallbackGetCollectionsList callback) {
        ScorocodeSdk.getCollectionsList(callback);
    }

    public Observable<List<ScorocodeCollection>> getCollectionsList() {
        return Observable.create(new Observable.OnSubscribe<List<ScorocodeCollection>>() {
            @Override
            public void call(final Subscriber<? super List<ScorocodeCollection>> subscriber) {
                getCollectionsList(new CallbackGetCollectionsList() {
                    @Override
                    public void onRequestSucceed(List<ScorocodeCollection> collections) {
                        subscriber.onNext(collections);
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

    public void getCollectionByName(String collectionName, CallbackGetCollection callback) {
        ScorocodeSdk.getCollectionByName(collectionName, callback);
    }

    public Observable<ScorocodeCollection>  getCollectionByName(final String collectionName) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeCollection>() {
            @Override
            public void call(final Subscriber<? super ScorocodeCollection> subscriber) {
                getCollectionByName(collectionName, new CallbackGetCollection() {
                    @Override
                    public void onRequestSucceed(ScorocodeCollection collection) {
                        subscriber.onNext(collection);
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

    public void createCollection(ScorocodeCollection collection, CallbackCreateCollection callback) {
        ScorocodeSdk.createCollection(collection.getCollectionName(), collection.isUseDocsACL(), collection.getACL(), collection.isNotify(), callback);
    }

    public Observable<ScorocodeCollection> createCollection(final ScorocodeCollection collection) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeCollection>() {
            @Override
            public void call(final Subscriber<? super ScorocodeCollection> subscriber) {
                createCollection(collection, new CallbackCreateCollection() {
                    @Override
                    public void onCollectionCreated(ScorocodeCollection collection) {
                        subscriber.onNext(collection);
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


    public void updateCollection(String collectionId, ScorocodeCollection collection, CallbackUpdateCollection callback) {
        ScorocodeSdk.updateCollection(collectionId, collection.getCollectionName(), collection.isUseDocsACL(), collection.getACL(), collection.isNotify(), callback);
    }

    public Observable<ScorocodeCollection> updateCollection(final String collectionId, final ScorocodeCollection collection) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeCollection>() {
            @Override
            public void call(final Subscriber<? super ScorocodeCollection> subscriber) {
                updateCollection(collectionId, collection, new CallbackUpdateCollection() {
                    @Override
                    public void onCollectionUpdated(ScorocodeCollection collection) {
                        subscriber.onNext(collection);
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

    public void deleteCollection(String collectionId, CallbackDeleteCollection callback) {
        ScorocodeSdk.deleteCollection(collectionId, callback);
    }

    public Observable<Void> deleteCollection(final String collectionId) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                deleteCollection(collectionId, new CallbackDeleteCollection() {
                    @Override
                    public void onCollectionDeleted() {
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

    public void cloneCollection(String collectionId, String collectionName, CallbackCloneCollection callback) {
        ScorocodeSdk.cloneCollection(collectionId, collectionName, callback);
    }

    public Observable<ScorocodeCollection> cloneCollection(final String collectionId, final String collectionName) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeCollection>() {
            @Override
            public void call(final Subscriber<? super ScorocodeCollection> subscriber) {
                cloneCollection(collectionId, collectionName, new CallbackCloneCollection() {
                    @Override
                    public void onCollectionCloned(ScorocodeCollection collection) {
                        subscriber.onNext(collection);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onCloneOperationFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void createCollectionIndex(String collectionName, Index index, CallbackCreateCollectionIndex callback) {
        ScorocodeSdk.createCollectionIndex(collectionName, index, callback);
    }

    public Observable<Void> createCollectionIndex(final String collectionName, final Index index) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
               createCollectionIndex(collectionName, index, new CallbackCreateCollectionIndex() {
                   @Override
                   public void onIndexCreated() {
                       subscriber.onCompleted();
                   }

                   @Override
                   public void onIndexCreationFailed(String errorCode, String errorMessage) {
                       subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                   }
               });
            }
        });
    }

    public void deleteCollectionIndex(String collectionName, String indexName, CallbackDeleteCollectionIndex callback) {
        ScorocodeSdk.deleteCollectionIndex(collectionName, indexName, callback);
    }

    public Observable<Void> deleteCollectionIndex(final String collectionName, final String indexName) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                deleteCollectionIndex(collectionName, indexName, new CallbackDeleteCollectionIndex() {
                    @Override
                    public void onIndexDeleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onIndexDeletionFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void updateCollectionTriggers(String collectionName, ScorocodeTriggers triggers, CallbackUpdateCollectionTriggers callback) {
        ScorocodeSdk.updateCollectionTriggers(collectionName, triggers, callback);
    }

    public Observable<ScorocodeTriggers> updateCollectionTriggers(final String collectionName, final ScorocodeTriggers triggers) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeTriggers>() {
            @Override
            public void call(final Subscriber<? super ScorocodeTriggers> subscriber) {
                updateCollectionTriggers(collectionName, triggers, new CallbackUpdateCollectionTriggers() {
                    @Override
                    public void onTriggersUpdated(ScorocodeTriggers triggers) {
                        subscriber.onNext(triggers);
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

    public void createCollectionField(String collectionName, ScorocodeField field, CallbackAddField callback) {
        ScorocodeSdk.createCollectionField(collectionName, field, callback);
    }

    public Observable<ScorocodeField> createCollectionField(final String collectionName, final ScorocodeField field) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeField>() {
            @Override
            public void call(final Subscriber<? super ScorocodeField> subscriber) {
                createCollectionField(collectionName, field, new CallbackAddField() {
                    @Override
                    public void onFieldAdded(ScorocodeField field) {
                        subscriber.onNext(field);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onAddFieldFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void deleteCollectionField(String collectionName, String fieldName, CallbackDeleteField callback) {
        ScorocodeSdk.deleteCollectionField(collectionName, fieldName, callback);
    }

    public Observable<ScorocodeCollection> deleteCollectionField(final String collectionName, final String fieldName) {
        return Observable.create(new Observable.OnSubscribe<ScorocodeCollection>() {
            @Override
            public void call(final Subscriber<? super ScorocodeCollection> subscriber) {
                deleteCollectionField(collectionName, fieldName, new CallbackDeleteField() {
                    @Override
                    public void onFieldDeleted(ScorocodeCollection collection) {
                        subscriber.onNext(collection);
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
