package ru.profit_group.scorocode_sdk;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.profit_group.scorocode_sdk.Requests.application.RequestAppInfo;
import ru.profit_group.scorocode_sdk.Requests.bots.RequestCreateBot;
import ru.profit_group.scorocode_sdk.Requests.bots.RequestDeleteBot;
import ru.profit_group.scorocode_sdk.Requests.bots.RequestGetBotsList;
import ru.profit_group.scorocode_sdk.Requests.bots.RequestUpdateBot;
import ru.profit_group.scorocode_sdk.Requests.fields.RequestCreateField;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestChangeCollectionTriggers;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestCloneCollection;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestCollectionByName;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestCollectionList;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestCreateCollection;
import ru.profit_group.scorocode_sdk.Requests.indexes.RequestCreateCollectionIndex;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestDeleteCollection;
import ru.profit_group.scorocode_sdk.Requests.fields.RequestDeleteField;
import ru.profit_group.scorocode_sdk.Requests.indexes.RequestDeleteCollectionIndex;
import ru.profit_group.scorocode_sdk.Requests.collection.RequestUpdateCollection;
import ru.profit_group.scorocode_sdk.Requests.data.RequestCount;
import ru.profit_group.scorocode_sdk.Requests.data.RequestFind;
import ru.profit_group.scorocode_sdk.Requests.data.RequestInsert;
import ru.profit_group.scorocode_sdk.Requests.files.RequestFile;
import ru.profit_group.scorocode_sdk.Requests.files.RequestUpload;
import ru.profit_group.scorocode_sdk.Requests.folders.RequestCreateNewFolder;
import ru.profit_group.scorocode_sdk.Requests.folders.RequestDeleteFolder;
import ru.profit_group.scorocode_sdk.Requests.folders.RequestFoldersList;
import ru.profit_group.scorocode_sdk.Requests.messages.RequestSendEmail;
import ru.profit_group.scorocode_sdk.Requests.messages.RequestSendPush;
import ru.profit_group.scorocode_sdk.Requests.messages.RequestSendSms;
import ru.profit_group.scorocode_sdk.Requests.scripts.RequestCreateScript;
import ru.profit_group.scorocode_sdk.Requests.scripts.RequestDeleteScriptById;
import ru.profit_group.scorocode_sdk.Requests.scripts.RequestGetScriptById;
import ru.profit_group.scorocode_sdk.Requests.scripts.RequestSendScriptTask;
import ru.profit_group.scorocode_sdk.Requests.scripts.RequestUpdateScript;
import ru.profit_group.scorocode_sdk.Requests.user.RequestLoginUser;
import ru.profit_group.scorocode_sdk.Requests.user.RequestLogoutUser;
import ru.profit_group.scorocode_sdk.Requests.user.RequestRegisterUser;
import ru.profit_group.scorocode_sdk.Requests.data.RequestRemove;
import ru.profit_group.scorocode_sdk.Requests.application.RequestStatistic;
import ru.profit_group.scorocode_sdk.Requests.data.RequestUpdate;
import ru.profit_group.scorocode_sdk.Requests.data.RequestUpdateById;
import ru.profit_group.scorocode_sdk.Responses.application.ResponseAppInfo;
import ru.profit_group.scorocode_sdk.Responses.bots.ResponseBot;
import ru.profit_group.scorocode_sdk.Responses.bots.ResponseBotList;
import ru.profit_group.scorocode_sdk.Responses.collections.ResponseChangeCollectionTriggers;
import ru.profit_group.scorocode_sdk.Responses.collections.ResponseGetCollectionsList;
import ru.profit_group.scorocode_sdk.Responses.collections.ResponseCollection;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseCount;
import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseInsert;
import ru.profit_group.scorocode_sdk.Responses.ResponseString;
import ru.profit_group.scorocode_sdk.Responses.fields.ResponseAddField;
import ru.profit_group.scorocode_sdk.Responses.folders.ResponseGetFoldersList;
import ru.profit_group.scorocode_sdk.Responses.scripts.ResponseScript;
import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;
import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdateById;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public interface ScorocodeApi {

    //Sign-up, sign-in methods
    @Headers({"Content-Type: application/json"})
    @POST("api/v1/register")
    Call<ResponseCodes> register(@Body RequestRegisterUser requestRegisterUser);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/login")
    Call<ResponseLogin> login(@Body RequestLoginUser requestLoginUser);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/logout")
    Call<ResponseCodes> logout(@Body RequestLogoutUser requestLogoutUser);

    //Data methods
    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/insert")
    Call<ResponseInsert> insert(@Body RequestInsert requestInsert);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/remove")
    Call<ResponseRemove> remove(@Body RequestRemove requestRemove);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/update")
    Call<ResponseUpdate> update(@Body RequestUpdate requestUpdate);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/updatebyid")
    Call<ResponseUpdateById> updateById(@Body RequestUpdateById requestUpdateById);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/find")
    Call<ResponseString> find(@Body RequestFind requestFind);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/count")
    Call<ResponseCount> count(@Body RequestCount requestCount);

    //File methods
    @Headers({"Content-Type: application/json"})
    @POST("api/v1/upload")
    Call<ResponseCodes> upload(@Body RequestUpload requestUpload);

    @GET("api/v1/getfile/{app}/{coll}/{field}/{docId}/{file}")
    Call<ResponseCodes> getFile(
            @NonNull @Path("app") String app,
            @NonNull @Path("coll") String coll,
            @NonNull @Path("field") String field,
            @NonNull @Path("docId") String docId,
            @NonNull @Path("file") String file);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/deletefile")
    Call<ResponseCodes> deleteFile(@Body RequestFile requestDeleteFile);

    //Message methods
    @Headers({"Content-Type: application/json"})
    @POST("api/v1/sendemail")
    Call<ResponseCodes> sendEmail(@Body RequestSendEmail requestSendEmail);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/sendpush")
    Call<ResponseCodes> sendPush(@Body RequestSendPush requestSendPush);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/sendsms")
    Call<ResponseCodes> sendSms(@Body RequestSendSms requestSendSms);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/scripts")
    Call<ResponseCodes> sendScriptTask(@Body RequestSendScriptTask requestSendScriptTask);

    //Application methods:
    @Headers({"Content-Type: application/json"})
    @POST("api/v1/stat")
    Call<ResponseAppStatistic> getAppStatistic(@Body RequestStatistic requestStatistic);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app")
    Call<ResponseAppInfo> getApplicationInfo(@Body RequestAppInfo requestAppInfo);

    //Collections methods
    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/collections")
    Call<ResponseGetCollectionsList> getCollectionsList(@Body RequestCollectionList requestCollectionList);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/collections/get")
    Call<ResponseCollection> getCollectionByName(@Body RequestCollectionByName requestCollectionByName);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/collections/create")
    Call<ResponseCollection> createCollection(@Body RequestCreateCollection requestCreateCollection);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/collections/update")
    Call<ResponseCollection> updateCollection(@Body RequestUpdateCollection requestUpdateCollection);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/collections/delete")
    Call<ResponseCodes> deleteCollection(@Body RequestDeleteCollection requestDeleteCollection);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/collections/clone")
    Call<ResponseCollection> cloneCollection(@Body RequestCloneCollection requestCloneCollection);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/collections/index/create")
    Call<ResponseCodes> createCollectionsIndex(@Body RequestCreateCollectionIndex requestCreateCollectionIndex);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/collections/index/delete")
    Call<ResponseCodes> deleteCollectionsIndex(@Body RequestDeleteCollectionIndex requestCreateCollectionIndex);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/collections/triggers")
    Call<ResponseChangeCollectionTriggers> changeCollectionTriggers(@Body RequestChangeCollectionTriggers requestChangeCollectionTriggers);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/collections/fields/create")
    Call<ResponseAddField> addFieldInCollection(@Body RequestCreateField requestAddFieldInCollection);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/collections/fields/delete")
    Call<ResponseCollection> deleteFieldFromCollection(@Body RequestDeleteField requestDeleteFieldFromCollection);

    //Folder methods
    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/scripts/folders")
    Call<ResponseGetFoldersList> getFoldersList(@Body RequestFoldersList requestFoldersList);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/scripts/folders/create")
    Call<ResponseCodes> createNewFolder(@Body RequestCreateNewFolder requestCreateNewFolder);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/scripts/folders/delete")
    Call<ResponseCodes> deleteFolder(@Body RequestDeleteFolder requestDeleteFolder);

    //Script methods
    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/scripts/create")
    Call<ResponseScript> createScript(@Body RequestCreateScript requestCreateScript);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/scripts/get")
    Call<ResponseScript> getScriptById(@Body RequestGetScriptById requestGetScript);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/scripts/update")
    Call<ResponseScript> updateScript(@Body RequestUpdateScript requestUpdateScript);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/app/scripts/delete")
    Call<ResponseCodes> deleteScript(@Body RequestDeleteScriptById requestDeleteScript);

    //Bot methods
    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/bots")
    Call<ResponseBotList> getBotsList(@Body RequestGetBotsList requestGetBotsList);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/bots/create")
    Call<ResponseBot> createBot(@Body RequestCreateBot requestCreateBot);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/bots/update")
    Call<ResponseBot> updateBot(@Body RequestUpdateBot requestUpdateBot);

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/bots/delete")
    Call<ResponseCodes> deleteBot(@Body RequestDeleteBot requestDeleteBot);

}

