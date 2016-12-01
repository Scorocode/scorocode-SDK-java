package ru.profit_group.scorocode_sdk;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackAddField;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackApplicationStatistic;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCloneCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCountDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateCollectionIndex;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateNewFolder;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteCollectionIndex;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteField;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFile;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFolder;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetApplicationInfo;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetCollectionsList;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetFile;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetFoldersList;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetScriptById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackInsert;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackLoginUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackLogoutUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRegisterUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendEmail;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendPush;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendSms;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateCollectionTriggers;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocumentById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUploadFile;
import ru.profit_group.scorocode_sdk.Requests.application.RequestAppInfo;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestChangeCollectionTriggers;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestCloneCollection;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestCollectionByName;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestCollectionList;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestCreateCollection;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestDeleteCollection;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestUpdateCollection;
import ru.profit_group.scorocode_sdk.Requests.fields.RequestCreateField;
import ru.profit_group.scorocode_sdk.Requests.fields.RequestDeleteField;
import ru.profit_group.scorocode_sdk.Requests.folders.RequestCreateNewFolder;
import ru.profit_group.scorocode_sdk.Requests.folders.RequestDeleteFolder;
import ru.profit_group.scorocode_sdk.Requests.folders.RequestFoldersList;
import ru.profit_group.scorocode_sdk.Requests.indexes.RequestCreateCollectionIndex;
import ru.profit_group.scorocode_sdk.Requests.indexes.RequestDeleteCollectionIndex;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageEmail;
import ru.profit_group.scorocode_sdk.Requests.messages.MessagePush;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageSms;
import ru.profit_group.scorocode_sdk.Requests.scripts.RequestCreateScript;
import ru.profit_group.scorocode_sdk.Requests.scripts.RequestDeleteScriptById;
import ru.profit_group.scorocode_sdk.Requests.scripts.RequestGetScriptById;
import ru.profit_group.scorocode_sdk.Requests.scripts.RequestUpdateScript;
import ru.profit_group.scorocode_sdk.Responses.application.ResponseAppInfo;
import ru.profit_group.scorocode_sdk.Responses.collections.ResponseChangeCollectionTriggers;
import ru.profit_group.scorocode_sdk.Responses.collections.ResponseCollection;
import ru.profit_group.scorocode_sdk.Responses.collections.ResponseGetCollectionsList;
import ru.profit_group.scorocode_sdk.Responses.fields.ResponseAddField;
import ru.profit_group.scorocode_sdk.Responses.folders.ResponseGetFoldersList;
import ru.profit_group.scorocode_sdk.Responses.scripts.ResponseScript;
import ru.profit_group.scorocode_sdk.dagger2_components.DaggerScorocodeApiComponent;
import ru.profit_group.scorocode_sdk.dagger2_components.ScorocodeApiComponent;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Index;
import ru.profit_group.scorocode_sdk.scorocode_objects.NetworkHelper;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.QueryInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeACL;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCoreInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeField;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeScript;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeTriggers;
import ru.profit_group.scorocode_sdk.scorocode_objects.SortInfo;
import ru.profit_group.scorocode_sdk.Requests.data.RequestCount;
import ru.profit_group.scorocode_sdk.Requests.data.RequestFind;
import ru.profit_group.scorocode_sdk.Requests.data.RequestInsert;
import ru.profit_group.scorocode_sdk.Requests.files.RequestFile;
import ru.profit_group.scorocode_sdk.Requests.files.RequestUpload;
import ru.profit_group.scorocode_sdk.Requests.messages.RequestSendEmail;
import ru.profit_group.scorocode_sdk.Requests.messages.RequestSendPush;
import ru.profit_group.scorocode_sdk.Requests.messages.RequestSendSms;
import ru.profit_group.scorocode_sdk.Requests.scripts.RequestSendScriptTask;
import ru.profit_group.scorocode_sdk.Requests.user.RequestLoginUser;
import ru.profit_group.scorocode_sdk.Requests.user.RequestLogoutUser;
import ru.profit_group.scorocode_sdk.Requests.user.RequestRegisterUser;
import ru.profit_group.scorocode_sdk.Requests.data.RequestRemove;
import ru.profit_group.scorocode_sdk.Requests.application.RequestStatistic;
import ru.profit_group.scorocode_sdk.Requests.data.RequestUpdate;
import ru.profit_group.scorocode_sdk.Requests.data.RequestUpdateById;
import ru.profit_group.scorocode_sdk.Responses.ResponseString;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseCount;
import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseInsert;
import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdateById;
import ru.profit_group.scorocode_sdk.scorocode_objects.UpdateInfo;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class ScorocodeSdk {

    public static final String ERROR_MESSAGE_GENERAL = "server not answered on request";
    public static final String ERROR_CODE_GENERAL = "-1";

    private static ScorocodeCoreInfo stateHolder;
    private static ScorocodeApiComponent scorocodeApiComponent;
    /**
     * Init Scorocode sdk with Keys
     */
    public static void initWith(
            @NonNull String applicationId,
            @NonNull String clientKey,
            @Nullable String masterKey,
            @Nullable String fileKey,
            @Nullable String messageKey,
            @Nullable String scriptKey,
            @Nullable String webSocket) {

        scorocodeApiComponent = DaggerScorocodeApiComponent.builder().build();
        stateHolder = new ScorocodeCoreInfo(applicationId, clientKey, masterKey, fileKey, messageKey, scriptKey, webSocket);
    }

    /**
     * Init Scorocode sdk with Keys
     */
    public static void initWith(@NonNull String applicationId, @NonNull String clientKey) {
        initWith(applicationId, clientKey, null, null, null, null, null);
    }

    public static void getApplicationStatistic(
            @NonNull final CallbackApplicationStatistic callbackApplicationStatistic) {

        Call<ResponseAppStatistic> appStatisticCall = getScorocodeApi().getAppStatistic(new RequestStatistic(stateHolder));
        appStatisticCall.enqueue(new Callback<ResponseAppStatistic>() {
            @Override
            public void onResponse(Call<ResponseAppStatistic> call, Response<ResponseAppStatistic> response) {
                if(response != null && response.body() != null) {
                    ResponseAppStatistic responseAppStatistic = response.body();
                    if(NetworkHelper.isResponseSucceed(responseAppStatistic)) {
                        callbackApplicationStatistic.onRequestSucceed(responseAppStatistic);
                    } else {
                        callbackApplicationStatistic.onRequestFailed(responseAppStatistic.getErrCode(), responseAppStatistic.getErrMsg());
                    }
                } else {
                    callbackApplicationStatistic.onRequestFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseAppStatistic> call, Throwable t) {
                callbackApplicationStatistic.onRequestFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * register new user
     * @param userName Username of new user
     * @param userEmail Email of new user
     * @param userPassword Password of new user
     * @param doc information to insert about new user in form (fieldName, Value) (field must exist)
     * @param callbackRegisterUser callback which will be invoked after request
     */
    public static void registerUser(
            @NonNull String userName,
            @NonNull String userEmail,
            @NonNull String userPassword,
            @Nullable DocumentInfo  doc,
            @NonNull final CallbackRegisterUser callbackRegisterUser) {

        Call<ResponseCodes> registerUserCall = getScorocodeApi().register(new RequestRegisterUser(stateHolder, userName, userEmail, userPassword, doc));
        registerUserCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackRegisterUser.onRegisterSucceed();
                    } else {
                        callbackRegisterUser.onRegisterFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackRegisterUser.onRegisterFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackRegisterUser.onRegisterFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * login user, initialize new session
     * user must exist
     * @param email email of user
     * @param password password of user
     * @param callbackLogin callback which will be invoked after request
     */
    public static void loginUser(
            @NonNull String email,
            @NonNull String password,
            @NonNull final CallbackLoginUser callbackLogin) {

        Call<ResponseLogin> loginUserCall = getScorocodeApi().login(new RequestLoginUser(stateHolder, email, password));
        loginUserCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response != null && response.body() != null) {
                    ResponseLogin responseLogin = response.body();
                    if(NetworkHelper.isResponseSucceed(responseLogin)) {
                        if(responseLogin.getResult() != null) {
                            ScorocodeSdk.setSessionId(responseLogin.getResult().getSessionId());
                            callbackLogin.onLoginSucceed(responseLogin);
                        } else {
                            callbackLogin.onLoginFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                        }
                    } else {
                        callbackLogin.onLoginFailed(responseLogin.getErrCode(), responseLogin.getErrMsg());
                    }
                } else {
                    callbackLogin.onLoginFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                callbackLogin.onLoginFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * logout user
     * clear current session
     * @param callbackLogout callback which will be invoked after request
     */
    public static void logoutUser(
            @NonNull final CallbackLogoutUser callbackLogout) {

        Call<ResponseCodes> logoutUserCall = getScorocodeApi().logout(new RequestLogoutUser(stateHolder));
        logoutUserCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackLogout.onLogoutSucceed();
                        ScorocodeSdk.setSessionId(null);
                    } else {
                        callbackLogout.onLogoutFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackLogout.onLogoutFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackLogout.onLogoutFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * insert document in collection
     * @param collectionName Collection name in which file will be inserted
     * @param doc document in format (field, value)
     * @param callbackInsert callback which will be invoked after request
     */
    public static void insertDocument(
            @NonNull String collectionName,
            @Nullable DocumentInfo doc,
            @NonNull final CallbackInsert callbackInsert) {

        Call<ResponseInsert> insertCall = getScorocodeApi().insert(new RequestInsert(stateHolder, collectionName, doc));
        insertCall.enqueue(new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                if(response != null && response.body() != null) {
                    ResponseInsert responseInsert = response.body();
                    if(NetworkHelper.isResponseSucceed(responseInsert)) {
                        callbackInsert.onInsertSucceed(responseInsert);
                    } else {
                        callbackInsert.onInsertFailed(responseInsert.getErrCode(), responseInsert.getErrMsg());
                    }
                } else {
                    callbackInsert.onInsertFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                callbackInsert.onInsertFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * remove document from collection
     * @param collectionName Name of collection in which document will be inserted
     * @param query query to find document
     * @param limit limit of documents which will be removed (can't be more than 1000. Default is 1)
     * @param callbackRemoveDocument callback which will be invoked after request
     */
    public static void removeDocument(
            @NonNull String collectionName,
            @Nullable Query query,
            @Nullable Integer limit,
            @NonNull final CallbackRemoveDocument callbackRemoveDocument) {

        Call<ResponseRemove> removeCall = getScorocodeApi().remove(new RequestRemove(stateHolder, collectionName, query, limit));
        removeCall.enqueue(new Callback<ResponseRemove>() {
            @Override
            public void onResponse(Call<ResponseRemove> call, Response<ResponseRemove> response) {
                if(response != null && response.body() != null) {
                    ResponseRemove responseRemove = response.body();
                    if(NetworkHelper.isResponseSucceed(responseRemove)) {
                        callbackRemoveDocument.onRemoveSucceed(responseRemove);
                    } else {
                        callbackRemoveDocument.onRemoveFailed(responseRemove.getErrCode(), responseRemove.getErrMsg());
                    }
                } else {
                    callbackRemoveDocument.onRemoveFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseRemove> call, Throwable t) {
                callbackRemoveDocument.onRemoveFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * update any number of documents in collection
     * document must exist
     * @param collectionName name of collection in which document will be updated
     * @param query query to find document
     * @param updateInfo update info. You can get this info using getUpdateInfo() method of Update class
     * @param limit of documents which will be updated
     * @param callbackUpdateDocument callback which will be invoked after request
     */
    public static void updateDocument(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull UpdateInfo updateInfo,
            @Nullable Integer limit,
            @NonNull final CallbackUpdateDocument callbackUpdateDocument) {

        Call<ResponseUpdate> updateCall = getScorocodeApi().update(new RequestUpdate(stateHolder, collectionName, query, updateInfo, limit));
        updateCall.enqueue(new Callback<ResponseUpdate>() {
            @Override
            public void onResponse(Call<ResponseUpdate> call, Response<ResponseUpdate> response) {
                if(response != null && response.body() != null) {
                    ResponseUpdate responseUpdate = response.body();
                    if(NetworkHelper.isResponseSucceed(responseUpdate)) {
                        callbackUpdateDocument.onUpdateSucceed(responseUpdate);
                    } else {
                        callbackUpdateDocument.onUpdateFailed(responseUpdate.getErrCode(), responseUpdate.getErrMsg());
                    }
                } else {
                    callbackUpdateDocument.onUpdateFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdate> call, Throwable t) {
                callbackUpdateDocument.onUpdateFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     *  update document by it's id.
     *  @param collectionName collection in which document will be updated
     *  @param query query to find document by it's id (use equalTo method "_id" : "documentId" ).
     *  @param updateInfo for document to insert. You can get this info using getUpdateInfo() method of Update class
     *  @param callbackUpdateDocumentById callback which will be invoked after request
     */
    public static void updateDocumentById(
            @NonNull String collectionName,
            @NonNull QueryInfo query,
            @NonNull UpdateInfo updateInfo,
            @NonNull final CallbackUpdateDocumentById callbackUpdateDocumentById) {

        Call<ResponseUpdateById> updateByIdCall = getScorocodeApi().updateById(new RequestUpdateById(stateHolder, collectionName, query, updateInfo));
        updateByIdCall.enqueue(new Callback<ResponseUpdateById>() {
            @Override
            public void onResponse(Call<ResponseUpdateById> call, Response<ResponseUpdateById> response) {
                if(response != null && response.body() != null) {
                    ResponseUpdateById responseUpdateById = response.body();
                    if(NetworkHelper.isResponseSucceed(responseUpdateById)) {
                        callbackUpdateDocumentById.onUpdateByIdSucceed(responseUpdateById);
                    } else {
                        callbackUpdateDocumentById.onUpdateByIdFailed(responseUpdateById.getErrCode(), responseUpdateById.getErrMsg());
                    }
                } else {
                    callbackUpdateDocumentById.onUpdateByIdFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateById> call, Throwable t) {
                callbackUpdateDocumentById.onUpdateByIdFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * search for documents which match query
     * @param collectionName name of collection where document will be searching
     * @param query query to find document
     * @param sort parameter to sort returned document
     * @param fieldsNamesToFind field's names which will be returned with document;
     * @param limit max number of documents which request return
     * @param skip number of documents which method skip in search
     * @param callbackFindDocument callback which will be invoked after request
     */
    public static void findDocument(
            @NonNull String collectionName,
            @Nullable Query query,
            @Nullable SortInfo sort,
            @Nullable List<String> fieldsNamesToFind,
            @Nullable Integer limit,
            @Nullable Integer skip,
            @NonNull final CallbackFindDocument callbackFindDocument) {

        Call<ResponseString> findCall = getScorocodeApi().find(new RequestFind(stateHolder, collectionName, query, sort, fieldsNamesToFind, limit, skip));
        findCall.enqueue(new Callback<ResponseString>() {
            @Override
            public void onResponse(Call<ResponseString> call, Response<ResponseString> response) {
                if(response != null && response.body() != null) {
                    ResponseString responseFindDocument = response.body();
                    if(NetworkHelper.isResponseSucceed(responseFindDocument)) {
                        String base64data = response.body().getResult();
                        List<DocumentInfo> documentInfos = Document.decodeDocumentsList(base64data);
                        if(documentInfos.size() != 0) {
                            callbackFindDocument.onDocumentFound(documentInfos);
                        } else {
                            callbackFindDocument.onDocumentNotFound("0", "Document not found");
                        }
                    } else {
                        callbackFindDocument.onDocumentNotFound(responseFindDocument.getErrCode(), responseFindDocument.getErrMsg());
                    }
                } else {
                    callbackFindDocument.onDocumentNotFound(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseString> call, Throwable t) {
                callbackFindDocument.onDocumentNotFound(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * get number of documents which match query
     * @param collectionName name of collection in which documents will be counting
     * @param query to select documents
     * @param callbackCountDocument which will be invoked after request
     */
    public static void getDocumentsCount(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull final CallbackCountDocument callbackCountDocument) {

        Call<ResponseCount> callCount = getScorocodeApi().count(new RequestCount(stateHolder, collectionName, query));
        callCount.enqueue(new Callback<ResponseCount>() {
            @Override
            public void onResponse(Call<ResponseCount> call, Response<ResponseCount> response) {
                if(response != null && response.body() != null) {
                    ResponseCount responseCount = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCount)) {
                        callbackCountDocument.onDocumentsCounted(responseCount);
                    } else {
                        callbackCountDocument.onCountFailed(responseCount.getErrCode(), responseCount.getErrMsg());
                    }
                } else {
                    callbackCountDocument.onCountFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCount> call, Throwable t) {
                callbackCountDocument.onCountFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * upload file in document of collection
     * @param collectionName name of collection with document
     * @param documentId document in which file will be upload
     * @param fieldName name of field in which file will be upload (must have file type)
     * @param fileName name of file to upload
     * @param contentToUpload content to upload in file in base64 format
     * @param callbackUploadFile which will be invoked after request
     */
    public static void uploadFile(
            @NonNull String collectionName,
            @NonNull String documentId,
            @NonNull String fieldName,
            @NonNull String fileName,
            @NonNull String contentToUpload,
            @NonNull final CallbackUploadFile callbackUploadFile) {

        Call<ResponseCodes> uploadFileCall = getScorocodeApi().upload(new RequestUpload(stateHolder, collectionName, documentId, fieldName, fileName, contentToUpload));
        uploadFileCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackUploadFile.onDocumentUploaded();
                    } else {
                        callbackUploadFile.onDocumentUploadFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackUploadFile.onDocumentUploadFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackUploadFile.onDocumentUploadFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * get link on file from collection
     * @param collectionName name of collection with file
     * @param fieldName name of the field where file stored
     * @param docId id of document where file stored
     * @param fileName name of the file
     */
    public static String getFileLink(
            @NonNull String collectionName,
            @NonNull String fieldName,
            @NonNull String docId,
            @NonNull String fileName) {

        Call<ResponseCodes> getFileCallback = getScorocodeApi().getFile(stateHolder.getApplicationId(), collectionName, fieldName, docId, fileName);
        return getFileCallback.request().url().url().toString();
    }

    /**
     * delete file from document of collection
     * @param collectionName name of collection which contain file
     * @param docId id of document which contain file
     * @param fieldName name of field in which file stored
     * @param fileName name of file to remove
     * @param callbackDeleteFile callback which will be invoked after request
     */
    public static void deleteFile(
            @NonNull String collectionName,
            @NonNull String docId,
            @NonNull String fieldName,
            @NonNull String fileName,
            @NonNull final CallbackDeleteFile callbackDeleteFile) {

        Call<ResponseCodes> deleteFileCall = getScorocodeApi().deleteFile(new RequestFile(stateHolder, collectionName, docId, fieldName, fileName));
        deleteFileCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackDeleteFile.onDocumentDeleted();
                    } else {
                        callbackDeleteFile.onDetelionFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackDeleteFile.onDetelionFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackDeleteFile.onDetelionFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * send email to one or more users
     * @param collectionName name of collection which contain user (must be USERS of ROLES)
     * @param query query to find users
     * @param msg message to send to user
     * @param callbackSendEmail callback which will be invoked after request
     */
    public static void sendEmail(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull MessageEmail msg,
            @NonNull final CallbackSendEmail callbackSendEmail) {

        Call<ResponseCodes> sendEmailCall = getScorocodeApi().sendEmail(new RequestSendEmail(stateHolder, collectionName, query, msg));
        sendEmailCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackSendEmail.onEmailSend();
                    } else {
                        callbackSendEmail.onEmailSendFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackSendEmail.onEmailSendFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackSendEmail.onEmailSendFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * send push to one or more users
     * @param collectionName name of collection which contain user (must be USERS of ROLES)
     * @param query query to find users
     * @param msg message to send to user
     * @param callbackSendPush callback which will be invoked after request
     */
    public static void sendPush(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull MessagePush msg,
            @NonNull final CallbackSendPush callbackSendPush) {

        Call<ResponseCodes> sendPushCall = getScorocodeApi().sendPush(new RequestSendPush(stateHolder, collectionName, query, msg));
        sendPushCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackSendPush.onPushSended();
                    } else {

                        callbackSendPush.onPushSendFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackSendPush.onPushSendFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackSendPush.onPushSendFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * send sms to one or more users
     * @param collectionName name of collection which contain user (must be USERS of ROLES)
     * @param query query to find users
     * @param msg message to send to user
     * @param callbackSendSms callback which will be invoked after request
     */
    public static void sendSms(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull MessageSms msg,
            @NonNull final CallbackSendSms callbackSendSms) {

        Call<ResponseCodes> sendSmsCall = getScorocodeApi().sendSms(new RequestSendSms(stateHolder, collectionName, query, msg));
        sendSmsCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackSendSms.onSmsSended();
                    } else {
                        callbackSendSms.onSmsSendFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackSendSms.onSmsSendFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackSendSms.onSmsSendFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    /**
     * run script in server
     * @param scriptId id of script to run
     * @param dataPoolForScript parameter for script
     * @param callbackSendScript callback which will be invoked after request
     */
    public static void runScript(
            @NonNull String scriptId,
            @Nullable Object dataPoolForScript,
            @NonNull final CallbackSendScript callbackSendScript) {

        Call<ResponseCodes> sendScriptTask = getScorocodeApi().sendScriptTask(new RequestSendScriptTask(stateHolder, scriptId, dataPoolForScript));
        sendScriptTask.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackSendScript.onScriptSended();
                    } else {
                        callbackSendScript.onScriptSendFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackSendScript.onScriptSendFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackSendScript.onScriptSendFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void getFileContent(String collectionName, final String fieldName, String documentId, String fileName, final CallbackGetFile callbackGetFile) {
        final String fileLink = getFileLink(collectionName, fieldName, documentId, fileName);

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                if(fileLink == null) {
                    return null;
                }

                try {
                    return NetworkHelper.readUrl(fileLink);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String fileContent) {
                super.onPostExecute(fileContent);

                if(fileContent != null) {
                    callbackGetFile.onSucceed(fileContent);
                } else {
                    callbackGetFile.onFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }
        }.execute();

    }

    public static void getApplicationInfo(final CallbackGetApplicationInfo callbackGetApplicationInfo) {
        Call<ResponseAppInfo> appInfoCall = getScorocodeApi().getApplicationInfo(new RequestAppInfo(stateHolder));
        appInfoCall.enqueue(new Callback<ResponseAppInfo>() {
            @Override
            public void onResponse(Call<ResponseAppInfo> call, Response<ResponseAppInfo> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackGetApplicationInfo.onRequestSucceed(response.body().getApplicationInfo());
                    } else {
                        callbackGetApplicationInfo.onRequestFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackGetApplicationInfo.onRequestFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseAppInfo> call, Throwable t) {
                callbackGetApplicationInfo.onRequestFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void getCollectionsList(final CallbackGetCollectionsList callbackGetCollectionsList) {
        Call<ResponseGetCollectionsList> collectionsCall = getScorocodeApi().getCollectionsList(new RequestCollectionList(stateHolder));
        collectionsCall.enqueue(new Callback<ResponseGetCollectionsList>() {
            @Override
            public void onResponse(Call<ResponseGetCollectionsList> call, Response<ResponseGetCollectionsList> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackGetCollectionsList.onRequestSucceed(response.body().getCollections());
                    } else {
                        callbackGetCollectionsList.onRequestFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackGetCollectionsList.onRequestFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetCollectionsList> call, Throwable t) {
                callbackGetCollectionsList.onRequestFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void getCollectionByName(String collectionName, final CallbackGetCollection callbackGetCollection) {
        Call<ResponseCollection> getCollectionByNameCall = getScorocodeApi().getCollectionByName(new RequestCollectionByName(stateHolder, collectionName));
        getCollectionByNameCall.enqueue(new Callback<ResponseCollection>() {
            @Override
            public void onResponse(Call<ResponseCollection> call, Response<ResponseCollection> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackGetCollection.onRequestSucceed(response.body().getCollection());
                    } else {
                        callbackGetCollection.onRequestFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackGetCollection.onRequestFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCollection> call, Throwable t) {
                callbackGetCollection.onRequestFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void createCollection(String collectionName, boolean isUseDocsACL, ScorocodeACL ACL, final CallbackCreateCollection callbackCreateCollection) {
        Call<ResponseCollection> createCollectionCall = getScorocodeApi().createCollection(new RequestCreateCollection(stateHolder, collectionName, isUseDocsACL, ACL));
        createCollectionCall.enqueue(new Callback<ResponseCollection>() {
            @Override
            public void onResponse(Call<ResponseCollection> call, Response<ResponseCollection> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackCreateCollection.onCollectionCreated(response.body().getCollection());
                    } else {
                        callbackCreateCollection.onCreationFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackCreateCollection.onCreationFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCollection> call, Throwable t) {
                callbackCreateCollection.onCreationFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void updateCollection(String collectionId, String collectionName, boolean isUseDocsACL, ScorocodeACL ACL, final CallbackUpdateCollection callbackUpdateCollection) {
        Call<ResponseCollection> updateCollectionCall = getScorocodeApi().updateCollection(new RequestUpdateCollection(stateHolder, collectionId, collectionName, isUseDocsACL, ACL));
        updateCollectionCall.enqueue(new Callback<ResponseCollection>() {
            @Override
            public void onResponse(Call<ResponseCollection> call, Response<ResponseCollection> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackUpdateCollection.onCollectionUpdated(response.body().getCollection());
                    } else {
                        callbackUpdateCollection.onUpdateFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackUpdateCollection.onUpdateFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCollection> call, Throwable t) {
                callbackUpdateCollection.onUpdateFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void deleteCollection(String collectionId, final CallbackDeleteCollection callbackDeleteCollection) {
        Call<ResponseCodes> deleteCollectionCall = getScorocodeApi().deleteCollection(new RequestDeleteCollection(stateHolder, collectionId));
        deleteCollectionCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackDeleteCollection.onCollectionDeleted();
                    } else {
                        callbackDeleteCollection.onDetelionFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackDeleteCollection.onDetelionFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackDeleteCollection.onDetelionFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void cloneCollection(String collectionId, String newCollectionName, final CallbackCloneCollection callbackCloneCollection) {
        getScorocodeApi().cloneCollection(new RequestCloneCollection(stateHolder, collectionId, newCollectionName))
        .enqueue(new Callback<ResponseCollection>() {
            @Override
            public void onResponse(Call<ResponseCollection> call, Response<ResponseCollection> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackCloneCollection.onCollectionCloned(response.body().getCollection());
                    } else {
                        callbackCloneCollection.onCloneOperationFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackCloneCollection.onCloneOperationFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCollection> call, Throwable t) {
                callbackCloneCollection.onCloneOperationFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void createCollectionIndex(String collectionName, Index index, final CallbackCreateCollectionIndex callbackCreateCollectionIndex) {
        getScorocodeApi().createCollectionsIndex(new RequestCreateCollectionIndex(stateHolder, collectionName, index))
        .enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(NetworkHelper.isResponseSucceed(responseCodes)) {
                        callbackCreateCollectionIndex.onIndexCreated();
                    } else {
                        callbackCreateCollectionIndex.onIndexCreationFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackCreateCollectionIndex.onIndexCreationFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackCreateCollectionIndex.onIndexCreationFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void deleteCollectionIndex(String collectionName, String indexName, final CallbackDeleteCollectionIndex callbackDeleteCollectionIndex) {
        getScorocodeApi().deleteCollectionsIndex(new RequestDeleteCollectionIndex(stateHolder, collectionName, indexName))
                .enqueue(new Callback<ResponseCodes>() {
                    @Override
                    public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                        if(response != null && response.body() != null) {
                            ResponseCodes responseCodes = response.body();
                            if(NetworkHelper.isResponseSucceed(responseCodes)) {
                                callbackDeleteCollectionIndex.onIndexDeleted();
                            } else {
                                callbackDeleteCollectionIndex.onIndexDeletionFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                            }
                        } else {
                            callbackDeleteCollectionIndex.onIndexDeletionFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseCodes> call, Throwable t) {
                        callbackDeleteCollectionIndex.onIndexDeletionFailed(ERROR_CODE_GENERAL, t.getMessage());
                    }
                });
    }


    public static void updateCollectionTriggers(String collectionName, ScorocodeTriggers triggers, final CallbackUpdateCollectionTriggers callbackUpdateCollectionTriggers) {
        getScorocodeApi().changeCollectionTriggers(new RequestChangeCollectionTriggers(stateHolder, collectionName, triggers))
                .enqueue(new Callback<ResponseChangeCollectionTriggers>() {
                    @Override
                    public void onResponse(Call<ResponseChangeCollectionTriggers> call, Response<ResponseChangeCollectionTriggers> response) {
                        if(response != null && response.body() != null) {
                            ResponseCodes responseCodes = response.body();
                            if(NetworkHelper.isResponseSucceed(responseCodes)) {
                                callbackUpdateCollectionTriggers.onTriggersUpdated(response.body().getTriggers());
                            } else {
                                callbackUpdateCollectionTriggers.onUpdateFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                            }
                        } else {
                            callbackUpdateCollectionTriggers.onUpdateFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseChangeCollectionTriggers> call, Throwable t) {
                        callbackUpdateCollectionTriggers.onUpdateFailed(ERROR_CODE_GENERAL, t.getMessage());
                    }
                });
    }

    public static void addFieldInCollection(String collectionName, ScorocodeField field, final CallbackAddField callbackAddField) {
        getScorocodeApi().addFieldInCollection(new RequestCreateField(stateHolder, collectionName, field))
                .enqueue(new Callback<ResponseAddField>() {
                    @Override
                    public void onResponse(Call<ResponseAddField> call, Response<ResponseAddField> response) {
                        if(response != null && response.body() != null) {
                            ResponseCodes responseCodes = response.body();
                            if(NetworkHelper.isResponseSucceed(responseCodes)) {
                                callbackAddField.onFieldAdded(response.body().getField());
                            } else {
                                callbackAddField.onAddFieldFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                            }
                        } else {
                            callbackAddField.onAddFieldFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAddField> call, Throwable t) {
                        callbackAddField.onAddFieldFailed(ERROR_CODE_GENERAL, t.getMessage());
                    }
                });
    }

    public static void deleteFieldFromCollection(String collectionName, String fieldName, final CallbackDeleteField callbackDeleteField) {
        getScorocodeApi().deleteFieldFromCollection(new RequestDeleteField(stateHolder, collectionName, fieldName))
                .enqueue(new Callback<ResponseCollection>() {
                    @Override
                    public void onResponse(Call<ResponseCollection> call, Response<ResponseCollection> response) {
                        if(response != null && response.body() != null) {
                            ResponseCodes responseCodes = response.body();
                            if(NetworkHelper.isResponseSucceed(responseCodes)) {
                                callbackDeleteField.onFieldDeleted(response.body().getCollection());
                            } else {
                                callbackDeleteField.onDeletionFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                            }
                        } else {
                            callbackDeleteField.onDeletionFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseCollection> call, Throwable t) {
                        callbackDeleteField.onDeletionFailed(ERROR_CODE_GENERAL, t.getMessage());
                    }
                });
    }

    public static void getFoldersList(String path, final CallbackGetFoldersList callbackGetFoldersList) {
        getScorocodeApi().getFoldersList(new RequestFoldersList(stateHolder, path))
                .enqueue(new Callback<ResponseGetFoldersList>() {
                    @Override
                    public void onResponse(Call<ResponseGetFoldersList> call, Response<ResponseGetFoldersList> response) {
                        if(response != null && response.body() != null) {
                            ResponseCodes responseCodes = response.body();
                            if(NetworkHelper.isResponseSucceed(responseCodes)) {
                                callbackGetFoldersList.onRequestSucceed(response.body().getFolders());
                            } else {
                                callbackGetFoldersList.onRequestFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                            }
                        } else {
                            callbackGetFoldersList.onRequestFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseGetFoldersList> call, Throwable t) {
                        callbackGetFoldersList.onRequestFailed(ERROR_CODE_GENERAL, t.getMessage());
                    }
                });
    }

    public static void createNewFolder(String pathToFolder, final CallbackCreateNewFolder callbackCreateNewFolder) {
        getScorocodeApi().createNewFolder(new RequestCreateNewFolder(stateHolder, pathToFolder))
                .enqueue(new Callback<ResponseCodes>() {
                    @Override
                    public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                        if(response != null && response.body() != null) {
                            ResponseCodes responseCodes = response.body();
                            if(NetworkHelper.isResponseSucceed(responseCodes)) {
                                callbackCreateNewFolder.onFolderCreated();
                            } else {
                                callbackCreateNewFolder.onCreationFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                            }
                        } else {
                            callbackCreateNewFolder.onCreationFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseCodes> call, Throwable t) {
                        callbackCreateNewFolder.onCreationFailed(ERROR_CODE_GENERAL, t.getMessage());
                    }
                });
    }

    public static void deleteFolder(String pathToFolder, final CallbackDeleteFolder callbackDeleteFolder) {
        getScorocodeApi().deleteFolder(new RequestDeleteFolder(stateHolder, pathToFolder))
                .enqueue(new Callback<ResponseCodes>() {
                    @Override
                    public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                        if(response != null && response.body() != null) {
                            ResponseCodes responseCodes = response.body();
                            if(NetworkHelper.isResponseSucceed(responseCodes)) {
                                callbackDeleteFolder.onFolderDeleted();
                            } else {
                                callbackDeleteFolder.onDeletionFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                            }
                        } else {
                            callbackDeleteFolder.onDeletionFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseCodes> call, Throwable t) {
                        callbackDeleteFolder.onDeletionFailed(ERROR_CODE_GENERAL, t.getMessage());
                    }
                });
    }

    public static void createScript(ScorocodeScript script, final CallbackCreateScript callbackCreateScript) {
        getScorocodeApi().createScript(new RequestCreateScript(stateHolder, script))
                .enqueue(new Callback<ResponseScript>() {
                    @Override
                    public void onResponse(Call<ResponseScript> call, Response<ResponseScript> response) {
                        if(response != null && response.body() != null) {
                            ResponseCodes responseCodes = response.body();
                            if(NetworkHelper.isResponseSucceed(responseCodes)) {
                                callbackCreateScript.onScriptCreated(response.body().getScript());
                            } else {
                                callbackCreateScript.onCreationFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                            }
                        } else {
                            callbackCreateScript.onCreationFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseScript> call, Throwable t) {
                        callbackCreateScript.onCreationFailed(ERROR_CODE_GENERAL, t.getMessage());
                    }
                });
    }

    public static void getScriptById(String scriptId, final CallbackGetScriptById callbackGetScriptById) {
        getScorocodeApi().getScriptById(new RequestGetScriptById(stateHolder, scriptId))
                .enqueue(new Callback<ResponseScript>() {
                    @Override
                    public void onResponse(Call<ResponseScript> call, Response<ResponseScript> response) {
                        if(response != null && response.body() != null) {
                            ResponseCodes responseCodes = response.body();
                            if(NetworkHelper.isResponseSucceed(responseCodes)) {
                                callbackGetScriptById.onRequestSucceed(response.body().getScript());
                            } else {
                                callbackGetScriptById.onRequestFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                            }
                        } else {
                            callbackGetScriptById.onRequestFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseScript> call, Throwable t) {
                        callbackGetScriptById.onRequestFailed(ERROR_CODE_GENERAL, t.getMessage());
                    }
                });
    }

    public static void updateScript(ScorocodeScript script, String scriptId, final CallbackUpdateScript callbackUpdateScript) {
        getScorocodeApi().updateScript(new RequestUpdateScript(stateHolder, scriptId, script))
                .enqueue(new Callback<ResponseScript>() {
                    @Override
                    public void onResponse(Call<ResponseScript> call, Response<ResponseScript> response) {
                        if(response != null && response.body() != null) {
                            ResponseCodes responseCodes = response.body();
                            if(NetworkHelper.isResponseSucceed(responseCodes)) {
                                callbackUpdateScript.onUpdateScriptSucceed(response.body().getScript());
                            } else {
                                callbackUpdateScript.onUpdateScriptFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                            }
                        } else {
                            callbackUpdateScript.onUpdateScriptFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseScript> call, Throwable t) {
                        callbackUpdateScript.onUpdateScriptFailed(ERROR_CODE_GENERAL, t.getMessage());
                    }
                });
    }

    public static void deleteScriptById(String scriptId, final CallbackDeleteScript callbackDeleteScript) {
        getScorocodeApi().deleteScript(new RequestDeleteScriptById(stateHolder, scriptId))
                .enqueue(new Callback<ResponseCodes>() {
                    @Override
                    public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                        if(response != null && response.body() != null) {
                            ResponseCodes responseCodes = response.body();
                            if(NetworkHelper.isResponseSucceed(responseCodes)) {
                                callbackDeleteScript.onScriptDeleted();
                            } else {
                                callbackDeleteScript.onDeletionFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                            }
                        } else {
                            callbackDeleteScript.onDeletionFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseCodes> call, Throwable t) {
                        callbackDeleteScript.onDeletionFailed(ERROR_CODE_GENERAL, t.getMessage());
                    }
                });
    }

    private static ScorocodeApi getScorocodeApi() {
        return scorocodeApiComponent.getScorocodeApi();
    }

    /**
     * set session id inside sdk
     */
    public static void setSessionId(String sessionId) {
        if(stateHolder != null) {
            stateHolder.setSessionId(sessionId);
        }
    }

    public static String getSessionId() {
        if (stateHolder != null) {
            if (stateHolder.getSessionId() != null) {
                String sessionIdCopy = new String(stateHolder.getSessionId()); //return copy of sessionId;
                return sessionIdCopy;
            }
        }

        return null;
    }

    public static String getMessageDebugEndpoint() {
        return "wss://wss.scorocode.ru/"+stateHolder.getApplicationId()+"/"+stateHolder.getWebsocketKey()+"/messenger_debugger";
    }
}
