package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.reflect.TypeToken;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCountDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocument;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseCount;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Peter Staranchuk on 25/09/16
 */
public class Query implements Serializable {

    private String collectionName;
    private Integer limit;
    private Integer skip;
    private SortInfo sort;
    private List<String> fieldIds;
    private QueryInfo queryInfo;

    public Query(String collectionName) {
        this.collectionName = collectionName;
        this.sort = new SortInfo();
        this.queryInfo = new QueryInfo();
    }

    public void findDocuments(CallbackFindDocument callback) {
        ScorocodeSdk.findDocument(collectionName, this, sort, fieldIds, limit, skip, callback);
    }

    public Observable<List<DocumentInfo>> findDocuments() {
        return Observable.create(new Observable.OnSubscribe<List<DocumentInfo>>() {
            @Override
            public void call(final Subscriber<? super List<DocumentInfo>> subscriber) {
                ScorocodeSdk.findDocument(collectionName, Query.this, sort, fieldIds, limit, skip, new CallbackFindDocument() {
                    @Override
                    public void onDocumentFound(List<DocumentInfo> documentInfos) {
                        subscriber.onNext(documentInfos);
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


    public void countDocuments(CallbackCountDocument callback) {
        ScorocodeSdk.getDocumentsCount(collectionName, this, callback);
    }

    public Observable<ResponseCount> countDocuments() {
        return Observable.create(new Observable.OnSubscribe<ResponseCount>() {
            @Override
            public void call(final Subscriber<? super ResponseCount> subscriber) {
                countDocuments(new CallbackCountDocument() {
                    @Override
                    public void onDocumentsCounted(ResponseCount responseCount) {
                        subscriber.onNext(responseCount);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onCountFailed(String errorCode, String errorMessage) {
                        subscriber.onError(new Throwable(errorCode + " " + errorMessage));
                    }
                });
            }
        });
    }

    public void updateDocument(Update update, CallbackUpdateDocument callback) {
        UpdateInfo doc = update.getUpdateInfo();
        ScorocodeSdk.updateDocument(collectionName, this, doc, limit, callback);
    }

    public Observable<ResponseUpdate> updateDocument(final Update update) {
        return Observable.create(new Observable.OnSubscribe<ResponseUpdate>() {
            @Override
            public void call(final Subscriber<? super ResponseUpdate> subscriber) {
                updateDocument(update, new CallbackUpdateDocument() {
                    @Override
                    public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                        subscriber.onNext(responseUpdate);
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

    public void removeDocument(CallbackRemoveDocument callback) {
        ScorocodeSdk.removeDocument(collectionName, this, limit, callback);
    }

    public Observable<ResponseRemove> removeDocument() {
        return Observable.create(new Observable.OnSubscribe<ResponseRemove>() {
            @Override
            public void call(final Subscriber<? super ResponseRemove> subscriber) {
                ScorocodeSdk.removeDocument(collectionName, Query.this, limit, new CallbackRemoveDocument() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public void setPage(Integer page) {
        if(page > 0) {
            skip = (page - 1) * limit;
        }
    }

    public Query equalTo(String field, Object value) {
        addQueryRule(field, "$eq", value);
        return this;
    }

    public Query notEqualTo(String field, Object value) {
        addQueryRule(field, "$ne", value);
        return this;
    }

    public Query containedIn(String field, List<Object> values) {
        addQueryRule(field, "$in", values);
        return this;
    }

    public Query containsAll(String field, List<Object> values) {
            addQueryRule(field, "$all", values);
        return this;
    }

    public Query notContainedIn(String field, List<Object> values) {
        addQueryRule(field, "$nin", values);
        return this;
    }

    public Query greaterThan(String field, Object value) {
        addQueryRule(field, "$gt", value);
        return this;
    }

    public Query greaterThenOrEqualTo(String field, Object value) {
        addQueryRule(field, "$gte", value);
        return this;
    }

    public Query lessThan(String field, Object value) {
        addQueryRule(field, "$lt", value);
        return this;
    }

    public Query lessThanOrEqualTo(String field, Object value) {
        addQueryRule(field, "$lte", value);
        return this;
    }

    public Query exists(String field) {
        addQueryRule(field, "$exists", true);
        return this;
    }

    public Query doesNotExist(String field) {
        addQueryRule(field, "$exists", false);
        return this;
    }

    public Query contains(String field, String regEx, RegexOptions options) {
        HashMap<String, Object> operationMap = new HashMap<>();
        operationMap.put("$regex", regEx);
        operationMap.put("$options", options.getRegexOptions());

        queryInfo.getInfo().put(field, operationMap);
        return this;
    }

    public Query startsWith(String field, String string, RegexOptions options) {
        HashMap<String, Object> operationMap = new HashMap<>();
        operationMap.put("$regex", "^" + string);
        if(options != null) {
            operationMap.put("$options", options.getRegexOptions());
        }
        queryInfo.getInfo().put(field, operationMap);
        return this;
    }

    public Query startsWith(String field, String string) {
        return startsWith(field, string, null);
    }

    public Query endsWith(String field, String string, RegexOptions options) {
        HashMap<String, Object> operationMap = new HashMap<>();
        operationMap.put("$regex", string + "$");
        if(options != null) {
            operationMap.put("$options", options.getRegexOptions());
        }

        queryInfo.getInfo().put(field, operationMap);
        return this;
    }

    public Query endsWith(String field, String string) {
        return endsWith(field,string,null);
    }

    public Query and(String field, Query query) {
        try {
            List<HashMap<String, HashMap<String, Object>>> list = new ArrayList<>();
            HashMap<String, Object> queryParam1 = (HashMap<String, Object>) query.getQueryInfo().getInfo().get(field);
            HashMap<String, Object> queryParam2 = (HashMap<String, Object>) queryInfo.getInfo().get(field);

            HashMap<String, HashMap<String, Object>> parameter1 = new HashMap<>();
            parameter1.put(field, queryParam1);

            HashMap<String, HashMap<String, Object>> parameter2 = new HashMap<>();
            parameter2.put(field, queryParam2);

            list.add(parameter1);
            list.add(parameter2);

            queryInfo.getInfo().remove(field);
            queryInfo.getInfo().put("$and", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Query or(String field, Query query) {
        try {
            List<HashMap<String, HashMap<String, Object>>> list = new ArrayList<>();
            HashMap<String, Object> queryParam1 = (HashMap<String, Object>) query.getQueryInfo().getInfo().get(field);
            HashMap<String, Object> queryParam2 = (HashMap<String, Object>) queryInfo.getInfo().get(field);

            HashMap<String, HashMap<String, Object>> parameter1 = new HashMap<>();
            parameter1.put(field, queryParam1);

            HashMap<String, HashMap<String, Object>> parameter2 = new HashMap<>();
            parameter2.put(field, queryParam2);

            list.add(parameter1);
            list.add(parameter2);

            queryInfo.getInfo().remove(field);
            queryInfo.getInfo().put("$or", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @NonNull
    private static HashMap<String, Object> getRecord(Object value, String operator) {
        HashMap<String, Object> record = new HashMap<>();
        record.put(operator, value);
        return record;
    }

    public void raw(String json) {
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> map = new Gson().fromJson(json, type);
        reset();
        queryInfo.getInfo().putAll(map);
    }

    private void addQueryRule(String field, String operation, Object value) {
        HashMap<String, Object> element = (HashMap<String, Object>) queryInfo.getInfo().get(field);

        if(queryInfo.getInfo().containsKey(field)) {
            element.put(operation, value);
        } else {
            queryInfo.getInfo().put(field, getRecord(value, operation));
        }
    }

    @NonNull
    public QueryInfo getQueryInfo() {
        if(queryInfo == null) {
            return new QueryInfo();
        }
        return queryInfo;
    }

    public void reset() {
        queryInfo.getInfo().clear();
        sort = null;
        fieldIds = null;
    }

    public void ascending(String field) {
        sort.setAscendingFor(field);
    }

    public void descending(String field) {
        sort.setDescendingFor(field);
    }

    public void setFieldsForSearch(List<String> fields) {
        fieldIds = fields;
    }

    public String getCollectionName() {
        return collectionName;
    }
}
