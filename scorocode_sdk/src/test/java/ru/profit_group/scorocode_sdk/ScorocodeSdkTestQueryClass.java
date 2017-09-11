package ru.profit_group.scorocode_sdk;

import android.util.Log;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackDocumentSaved;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.RegexOptions;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.ARRAY_FIELD_1;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.NUMBER_FIELD_1;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.TEST_COLLECTION_NAME;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.TEXT_FIELD_1;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.printError;

/**
 * Created by Peter Staranchuk on 10/21/16
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScorocodeSdkTestQueryClass {

    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(ScorocodeTestHelper.getAppId(), ScorocodeTestHelper.getClientKey(), ScorocodeTestHelper.getMasterKey(), null, null, null, null);
        if(BuildConfig.DEBUG) {
            ScorocodeSdkStateHolder.setBaseURL("https://94.126.157.202");
        }

    }

    private static void initData() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(7);

        removeDocuments(countDownLatch);
        createDocForEqualTest(countDownLatch);
        createDocForNotEqualTest(countDownLatch);
        createDocForContainsAllTest(countDownLatch);

        createDocForRegexTest(countDownLatch, "aab");
        createDocForRegexTest(countDownLatch, "aAb");
        createDocForRegexTest(countDownLatch, "abc");
        createDocForRegexTest(countDownLatch, "aBC");

        countDownLatch.await();
    }

    private static void removeDocuments(final CountDownLatch countDownLatch) {
        Query query = new Query(TEST_COLLECTION_NAME);
        query.removeDocument(new CallbackRemoveDocument() {
            @Override
            public void onRemoveSucceed(ResponseRemove responseRemove) {
                countDownLatch.countDown();
            }

            @Override
            public void onRemoveFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
                printError("initialization error", errorCode, errorMessage);
            }
        });
    }

    @Test
    public void test1EqualToMethod() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);
        query.equalTo(NUMBER_FIELD_1, 1);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test2NotEqualToMethod() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);
        query.notEqualTo(TEXT_FIELD_1, "A");

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test3ContainedInTest() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        List<Object> valuesList = new ArrayList<>();
        valuesList.add(1);
        valuesList.add(2);
        valuesList.add(3);

        query.containedIn(NUMBER_FIELD_1, valuesList);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test4NotContainedInTest() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        List<Object> valuesList = new ArrayList<>();
        valuesList.add(-1);
        valuesList.add(3);

        query.notContainedIn(NUMBER_FIELD_1, valuesList);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test5ContainsAllTest() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        List<Object> valuesList = new ArrayList<>();
        valuesList.add(2);
        valuesList.add(3);

        query.containsAll(ARRAY_FIELD_1, valuesList);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test6GreaterThanTest() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        query.greaterThan(NUMBER_FIELD_1, -1);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test7GreaterThanOrEqualTest() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        query.greaterThenOrEqualTo(NUMBER_FIELD_1, -1);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test8LessThanTest() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        query.lessThan(NUMBER_FIELD_1, 1);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test9LessThanOrEqualToTest() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        query.lessThanOrEqualTo(NUMBER_FIELD_1, 1);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_10ExistTest() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        query.exists(TEXT_FIELD_1);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_11NotExistTest() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        query.doesNotExist(TEXT_FIELD_1);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_12ContainTest() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        query.contains(TEXT_FIELD_1, "ab", new RegexOptions());

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_13ContainTestCaseInsensitive() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        RegexOptions regexOptions = new RegexOptions();
        regexOptions.setRegexCaseInsenssitive();

        query.contains(TEXT_FIELD_1, "ab", regexOptions);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_14StartWith() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        RegexOptions regexOptions = new RegexOptions();

        query.startsWith(TEXT_FIELD_1, "ab", regexOptions);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_15StartWithWithoutOptions() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        query.startsWith(TEXT_FIELD_1, "ab");

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_16EndWith() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        RegexOptions regexOptions = new RegexOptions();

        query.endsWith(TEXT_FIELD_1, "ab", regexOptions);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_17EndWithWithoutOptions() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        query.endsWith(TEXT_FIELD_1, "ab", null);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                printError("не удалось найти файл с помощью данного запроса. " +
                        "Возможно он поврежден или файл для тестового поиска не был создан", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_18RawTest() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Query query = new Query("querytest");
        query.raw("{\"_id\": {\"$eq\": \"L8UjiQZRkj\"}}");
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                Log.d("","");
                countDownLatch.countDown();

            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d("","");
                countDownLatch.countDown();

            }
        });
        countDownLatch.await();
    }

    @Test
    public void test_19ComplexQuery() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Query query = new Query(TEST_COLLECTION_NAME);
        query.equalTo(TEXT_FIELD_1, "A");
        query.equalTo(NUMBER_FIELD_1, 105);

        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                Log.d("","");
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d("","");
            }
        });

        countDownLatch.await();

    }

    private static void createDocForEqualTest(final CountDownLatch countDownLatch) {
        Document document = new Document(TEST_COLLECTION_NAME);
        document.setField(NUMBER_FIELD_1, 1);
        document.setField(TEXT_FIELD_1, "A");

        document.saveDocument(new CallbackDocumentSaved() {
            @Override
            public void onDocumentSaved() {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
                printError("initialization error", errorCode, errorMessage);
            }
        });
    }


    private static void createDocForNotEqualTest(final CountDownLatch countDownLatch) {
        Document document = new Document(TEST_COLLECTION_NAME);
        document.setField(NUMBER_FIELD_1, -1);

        document.saveDocument(new CallbackDocumentSaved() {
            @Override
            public void onDocumentSaved() {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                printError("initialization error", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });
    }

    private static void createDocForContainsAllTest(final CountDownLatch countDownLatch) {
        Document document = new Document(TEST_COLLECTION_NAME);

        List<Object> valueList = new ArrayList<>();
        valueList.add(1);
        valueList.add(2);
        valueList.add(3);
        valueList.add(4);
        valueList.add(5);

        document.setField(ARRAY_FIELD_1, valueList);
        document.setField(TEXT_FIELD_1, "A");

        document.saveDocument(new CallbackDocumentSaved() {
            @Override
            public void onDocumentSaved() {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                printError("initialization error", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });
    }

    private static void createDocForRegexTest(final CountDownLatch countDownLatch, String textField) {
        Document document = new Document(TEST_COLLECTION_NAME);

        document.setField(TEXT_FIELD_1, textField);

        document.saveDocument(new CallbackDocumentSaved() {
            @Override
            public void onDocumentSaved() {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                printError("initialization error", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });
    }
}
