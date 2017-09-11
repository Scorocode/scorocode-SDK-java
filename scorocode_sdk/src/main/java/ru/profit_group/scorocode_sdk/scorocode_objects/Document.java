package ru.profit_group.scorocode_sdk.scorocode_objects;

import org.bson.BSON;
import org.bson.BSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFile;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDocumentSaved;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetDocumentById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetFile;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackInsert;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocumentById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUploadFile;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseInsert;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdateById;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Peter Staranchuk on 10/6/16
 */

public class Document implements Serializable {
    protected String collectionName;
    protected String documentId;
    protected DocumentInfo documentContent;
    protected Update update;

    public Document(String collectionName) {
        this.collectionName = collectionName;
        documentContent = new DocumentInfo(null);
        update = new Update();
    }

    public DocumentInfo getDocumentContent() {
        return documentContent;
    }

    public void getDocumentById(final String documentId, final CallbackGetDocumentById callbackGetDocumentById) {
        Query query = new Query(collectionName);
        query.equalTo("_id", documentId);

        ScorocodeSdk.findDocument(collectionName, query, null, null, null, null, new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                Document.this.documentId = documentId;
                if(documentInfos != null && documentInfos.size() > 0) {
                    documentContent = documentInfos.get(0);
                    callbackGetDocumentById.onDocumentFound(documentInfos.get(0));
                }
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Document.this.documentId = null;
                callbackGetDocumentById.onDocumentNotFound(errorCode, errorMessage);
            }
        });
    }


    public Observable<DocumentInfo> getDocumentById(final String documentId) {
        return Observable.create(new Observable.OnSubscribe<DocumentInfo>() {
            @Override
            public void call(final Subscriber<? super DocumentInfo> subscriber) {
                getDocumentById(documentId, new CallbackGetDocumentById() {
                    @Override
                    public void onDocumentFound(DocumentInfo documentInfo) {
                        subscriber.onNext(documentInfo);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onDocumentNotFound(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void saveDocument(final CallbackDocumentSaved callbackDocumentSaved) {
        if(documentId == null) {
            ScorocodeSdk.insertDocument(collectionName, documentContent, new CallbackInsert() {
                @Override
                public void onInsertSucceed(ResponseInsert responseInsert) {
                    documentContent = responseInsert.getResult();
                    documentId = documentContent.getId();
                    callbackDocumentSaved.onDocumentSaved();
                }

                @Override
                public void onInsertFailed(String errorCode, String errorMessage) {
                    callbackDocumentSaved.onDocumentSaveFailed(errorCode, errorMessage);
                }
            });
        } else {
            QueryInfo queryInfo = new QueryInfo();
            queryInfo.getInfo().put("_id", documentId);

            ScorocodeSdk.updateDocumentById(collectionName, queryInfo, update.getUpdateInfo(), new CallbackUpdateDocumentById() {
                @Override
                public void onUpdateByIdSucceed(ResponseUpdateById requestUpdateById) {
                    documentContent = requestUpdateById.getResult();
                    documentId = documentContent.getId();
                    callbackDocumentSaved.onDocumentSaved();
                    if(update != null && update.getUpdateInfo() != null) {
                        update.getUpdateInfo().getInfo().clear();
                    }
                }

                @Override
                public void onUpdateByIdFailed(String errorCode, String errorMessage) {
                    callbackDocumentSaved.onDocumentSaveFailed(errorCode, errorMessage);
                }
            });
        }
    }

    public Observable<Void> saveDocument() {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                saveDocument(new CallbackDocumentSaved() {
                    @Override
                    public void onDocumentSaved() {
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void removeDocument(CallbackRemoveDocument callback) {
        Query query = new Query(collectionName);
        query.equalTo("_id", documentId);

        ScorocodeSdk.removeDocument(collectionName, query, null, callback);
    }

    public Observable<ResponseRemove> removeDocument() {
        return Observable.create(new Observable.OnSubscribe<ResponseRemove>() {
            @Override
            public void call(final Subscriber<? super ResponseRemove> subscriber) {
                removeDocument(new CallbackRemoveDocument() {
                    @Override
                    public void onRemoveSucceed(ResponseRemove responseRemove) {
                        subscriber.onNext(responseRemove);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onRemoveFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void uploadFile(String fieldName, String fileName, String contenToUploadInBase64, CallbackUploadFile callback) {
        ScorocodeSdk.uploadFile(collectionName,
                documentId, fieldName, fileName, contenToUploadInBase64, callback);
    }

    public Observable<Void> uploadFile(final String fieldName, final String fileName, final String contenToUploadInBase64) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                uploadFile(fieldName, fileName, contenToUploadInBase64, new CallbackUploadFile() {
                    @Override
                    public void onDocumentUploaded() {
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onDocumentUploadFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public Object getField(String field) {
        return documentContent.get(field);
    }

    public void setField(String field, Object value) {
        documentContent.put(field, value);
    }


    public String getFileLink(String fieldName, String fileName) {
        return ScorocodeSdk.getFileLink(collectionName, fieldName, documentId, fileName);
    }

    public void getFileContent(String fieldName, String fileName, final CallbackGetFile callbackGetFile) {
        ScorocodeSdk.getFileContent(collectionName, fieldName, documentId, fileName, new CallbackGetFile() {
            @Override
            public void onSucceed(String fileContent) {
               callbackGetFile.onSucceed(fileContent);
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                callbackGetFile.onFailed(errorCode, errorMessage);
            }
        });
    }


    public Observable<String> getFileContent(final String fieldName, final String fileName) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                getFileContent(fieldName, fileName, new CallbackGetFile() {
                    @Override
                    public void onSucceed(String fileContent) {
                        subscriber.onNext(fileContent);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void removeFile(String fieldName, String fileName, CallbackDeleteFile callback) {
        ScorocodeSdk.deleteFile(collectionName, documentId, fieldName, fileName, callback);
    }

    public Observable<Void> removeFile(final String fieldName, final String fileName) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                removeFile(fieldName, fileName, new CallbackDeleteFile() {
                    @Override
                    public void onDocumentDeleted() {
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onDeletionFailed(String errorCodes, String errorMessage) {
                        subscriber.onError(new Throwable(errorCodes + " " + errorMessage));
                    }
                });
            }
        });
    }

    @Deprecated
    public static List<DocumentInfo> decodeDocumentsList(String base64data) {

        try {
            byte[] bson = ru.profit_group.scorocode_sdk.scorocode_objects.Base64.decode(base64data);
            BSONObject bsonObject = BSON.decode(bson);

            HashMap<Integer, HashMap<String, String>> documentMap = (HashMap<Integer, HashMap<String, String>>) bsonObject.toMap();

            List<DocumentInfo> documentInfos = new ArrayList<>();
            for(int i = 0; i < documentMap.size(); i++) {
                HashMap<String, String> document = documentMap.get(String.valueOf(i));

                DocumentInfo documentInfo = new DocumentInfo();
                for(String key : document.keySet()) {
                    documentInfo.put(key, document.get(key));
                }

                documentInfos.add(documentInfo);
            }

            return documentInfos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Update updateDocument() {
        return update;
    }
}
