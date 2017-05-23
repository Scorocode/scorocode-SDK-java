package ru.profit_group.scorocode_sdk;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
import ru.profit_group.scorocode_sdk.scorocode_objects.Collections;
import ru.profit_group.scorocode_sdk.scorocode_objects.Index;
import ru.profit_group.scorocode_sdk.scorocode_objects.IndexField;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeField;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeTriggers;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeTypes;
import ru.profit_group.scorocode_sdk.scorocode_objects.Trigger;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by Peter Staranchuk on 11/28/16
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScorocodeSdkTestCollections {

    private static final String COLLECTION_NAME = "scorocodeTestCollectionName";
    private static ScorocodeCollection testCollection;
    private final String INDEX_NAME = "testIndex";

    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(ScorocodeTestHelper.getAppId(), ScorocodeTestHelper.getClientKey(), ScorocodeTestHelper.getMasterKey(), null, null, null, null);
        if(BuildConfig.DEBUG) {
            ScorocodeSdkStateHolder.setBaseURL("https://94.126.157.202");
        }
    }

    @Test
    public void test1CreateCollection() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.createCollection(COLLECTION_NAME, false, ScorocodeTestHelper.getTestACL() , new CallbackCreateCollection() {
            @Override
            public void onCollectionCreated(ScorocodeCollection collection) {
                testCollection = collection;
                countDownLatch.countDown();
            }

            @Override
            public void onCreationFailed(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("test failed", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }

    @Test
    public void test2GetCollectionsList() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.getCollectionsList(new CallbackGetCollectionsList() {
            @Override
            public void onRequestSucceed(List<ScorocodeCollection> collections) {
                assertNotEquals("There is must be at least one collection", collections.size(), 0);
                countDownLatch.countDown();
            }

            @Override
            public void onRequestFailed(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("test failed", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test3GetCollectionByName() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.getCollectionByName(testCollection.getCollectionName(), new CallbackGetCollection() {
            @Override
            public void onRequestSucceed(ScorocodeCollection collection) {
                countDownLatch.countDown();
            }

            @Override
            public void onRequestFailed(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("test failed", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }


    @Test
    public void test4UpdateCollection() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.updateCollection(testCollection.getCollectionId(), "and_updated"+ testCollection.getCollectionName(),
                false, ScorocodeTestHelper.getTestACL(), new CallbackUpdateCollection() {
            @Override
            public void onCollectionUpdated(ScorocodeCollection collection) {
                testCollection = collection;
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("wrong test conditions", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }


    @Test
    public void test5CloneCollection() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final String oldCollectionId = testCollection.getCollectionId();

        ScorocodeSdk.cloneCollection(testCollection.getCollectionId(), "and_clonned" + testCollection.getCollectionName(), new CallbackCloneCollection() {
            @Override
            public void onCollectionCloned(ScorocodeCollection collection) {
                testCollection = collection;

                ScorocodeSdk.deleteCollection(oldCollectionId, new CallbackDeleteCollection() {
                    @Override
                    public void onCollectionDeleted() {
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onDeletionFailed(String errorCodes, String errorMessage) {
                        countDownLatch.countDown();
                    }
                });

            }

            @Override
            public void onCloneOperationFailed(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("fail", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }


    @Test
    public void test6CreateCollectionIndex() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        List<IndexField> indexFields = new ArrayList<>();
        indexFields.add(new IndexField("readACL", 1));

        Index index = new Index(INDEX_NAME, indexFields);

        ScorocodeSdk.createCollectionIndex(testCollection.getCollectionName(), index, new CallbackCreateCollectionIndex() {
            @Override
            public void onIndexCreated() {
                countDownLatch.countDown();
            }

            @Override
            public void onIndexCreationFailed(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("fail", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }


    @Test
    public void test7DeleteCollectionIndex() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.deleteCollectionIndex(testCollection.getCollectionName(), INDEX_NAME, new CallbackDeleteCollectionIndex() {
            @Override
            public void onIndexDeleted() {
                countDownLatch.countDown();
            }

            @Override
            public void onIndexDeletionFailed(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("fail", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test8UpdateCollectionTriggers() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        boolean isActive = false;
        ScorocodeTriggers triggers = new ScorocodeTriggers();
        triggers.setBeforeInsert(new Trigger("BFI code", isActive));
        triggers.setAfterInsert(new Trigger("AFI code", isActive));
        triggers.setBeforeRemove(new Trigger("BFR code", isActive));
        triggers.setAfterRemove(new Trigger("AFR code", isActive));
        triggers.setBeforeUpdate(new Trigger("BFU code", isActive));
        triggers.setAfterUpdate(new Trigger("AFU code", isActive));

        ScorocodeSdk.updateCollectionTriggers(testCollection.getCollectionName(), triggers, new CallbackUpdateCollectionTriggers() {
            @Override
            public void onTriggersUpdated(ScorocodeTriggers triggers) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("fail", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test9AddFieldInCollection() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeField field = new ScorocodeField("testNumberField".toLowerCase(), ScorocodeTypes.TypeNumber, "", false, false, false);

        ScorocodeSdk.createCollectionField(testCollection.getCollectionName(), field, new CallbackAddField() {
            @Override
            public void onFieldAdded(ScorocodeField field) {
                countDownLatch.countDown();
            }

            @Override
            public void onAddFieldFailed(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("fail", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_10DeleteFieldFromCollection() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.deleteCollectionField(testCollection.getCollectionName(), "testNumberField".toLowerCase(), new CallbackDeleteField() {
            @Override
            public void onFieldDeleted(ScorocodeCollection collection) {
                countDownLatch.countDown();
            }

            @Override
            public void onDeletionFailed(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("fail", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_11DeleteCollection() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.deleteCollection(testCollection.getCollectionId(), new CallbackDeleteCollection() {
            @Override
            public void onCollectionDeleted() {
                countDownLatch.countDown();
            }

            @Override
            public void onDeletionFailed(String errorCodes, String errorMessage) {
                ScorocodeTestHelper.printError("wrong test conditions", errorCodes, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_12CreateCollectionWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeCollection newCollection = new ScorocodeCollection()
                .setCollectionName(COLLECTION_NAME)
                .setUseDocsACL(false)
                .setACL(ScorocodeTestHelper.getTestACL());

        Collections collections = new Collections();
        collections.createCollection(newCollection, new CallbackCreateCollection() {
            @Override
            public void onCollectionCreated(ScorocodeCollection collection) {
                //collection created
                testCollection = collection;
                countDownLatch.countDown();
            }

            @Override
            public void onCreationFailed(String errorCode, String errorMessage) {
                //error during request
                ScorocodeTestHelper.printError("test failed", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_13GetCollectionsListWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Collections collections = new Collections();
        collections.getCollectionsList(new CallbackGetCollectionsList() {
            @Override
            public void onRequestSucceed(List<ScorocodeCollection> collections) {
                //sdk returned collections list
            }

            @Override
            public void onRequestFailed(String errorCode, String errorMessage) {
                //error during request
            }
        });

        ScorocodeSdk.getCollectionsList(new CallbackGetCollectionsList() {
            @Override
            public void onRequestSucceed(List<ScorocodeCollection> collections) {
                assertNotEquals("There is must be at least one collection", collections.size(), 0);
                countDownLatch.countDown();
            }

            @Override
            public void onRequestFailed(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("test failed", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_14GetCollectionByNameWithClass() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Collections collections = new Collections();
        collections.getCollectionByName(testCollection.getCollectionName(), new CallbackGetCollection() {
            @Override
            public void onRequestSucceed(ScorocodeCollection collection) {
                //sdk returned the collection
                countDownLatch.countDown();
            }

            @Override
            public void onRequestFailed(String errorCode, String errorMessage) {
                //error during request
                ScorocodeTestHelper.printError("test failed", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_15UpdateCollectionWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Collections collections = new Collections();

        ScorocodeCollection collection = new ScorocodeCollection()
                .setCollectionName("and_updated"+ testCollection.getCollectionName())
                .setUseDocsACL(false)
                .setACL(ScorocodeTestHelper.getTestACL());

        collections.updateCollection(testCollection.getCollectionId(), collection, new CallbackUpdateCollection() {
            @Override
            public void onCollectionUpdated(ScorocodeCollection collection) {
                //collection updated
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                //error during request
            }
        });

        ScorocodeSdk.updateCollection(testCollection.getCollectionId(), "and_updated"+ testCollection.getCollectionName(),
                false, ScorocodeTestHelper.getTestACL(), new CallbackUpdateCollection() {
                    @Override
                    public void onCollectionUpdated(ScorocodeCollection collection) {
                        testCollection = collection;
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onUpdateFailed(String errorCode, String errorMessage) {
                        ScorocodeTestHelper.printError("wrong test conditions", errorCode, errorMessage);
                        countDownLatch.countDown();
                    }
                });

        countDownLatch.await();
    }

    @Test
    public void test_16CloneCollectionWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final String oldCollectionId = testCollection.getCollectionId();

        Collections collections = new Collections();
        collections.cloneCollection(testCollection.getCollectionId(), "and_clonned" + testCollection.getCollectionName(), new CallbackCloneCollection() {
            @Override
            public void onCollectionCloned(ScorocodeCollection collection) {
                //collection cloned
                testCollection = collection;

                ScorocodeSdk.deleteCollection(oldCollectionId, new CallbackDeleteCollection() {
                    @Override
                    public void onCollectionDeleted() {
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onDeletionFailed(String errorCodes, String errorMessage) {
                        countDownLatch.countDown();
                    }
                });
            }

            @Override
            public void onCloneOperationFailed(String errorCode, String errorMessage) {
                //error during request
                ScorocodeTestHelper.printError("fail", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_17CreateCollectionIndexWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        List<IndexField> indexFields = new ArrayList<>();
        indexFields.add(new IndexField("readACL", 1));

        Index index = new Index(INDEX_NAME, indexFields);

        Collections collections = new Collections();
        collections.createCollectionIndex(testCollection.getCollectionName(), index, new CallbackCreateCollectionIndex() {
            @Override
            public void onIndexCreated() {
                //index created
                countDownLatch.countDown();
            }

            @Override
            public void onIndexCreationFailed(String errorCode, String errorMessage) {
                //error during request
                ScorocodeTestHelper.printError("fail", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }


    @Test
    public void test_18DeleteCollectionIndexWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Collections collections = new Collections();
        collections.deleteCollectionIndex(testCollection.getCollectionName(), INDEX_NAME, new CallbackDeleteCollectionIndex() {
            @Override
            public void onIndexDeleted() {
                countDownLatch.countDown();
                //index deleted
            }

            @Override
            public void onIndexDeletionFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
                //error during request
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_19UpdateCollectionTriggersWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        boolean isActive = false;
        ScorocodeTriggers triggers = new ScorocodeTriggers();
        triggers.setBeforeInsert(new Trigger("BFI code", isActive));
        triggers.setAfterInsert(new Trigger("AFI code", isActive));
        triggers.setBeforeRemove(new Trigger("BFR code", isActive));
        triggers.setAfterRemove(new Trigger("AFR code", isActive));
        triggers.setBeforeUpdate(new Trigger("BFU code", isActive));
        triggers.setAfterUpdate(new Trigger("AFU code", isActive));

        Collections collections = new Collections();
        collections.updateCollectionTriggers(testCollection.getCollectionName(), triggers, new CallbackUpdateCollectionTriggers() {
            @Override
            public void onTriggersUpdated(ScorocodeTriggers triggers) {
                //trigger updated
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                //error during request
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_20AddFieldInCollectionWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeField field = new ScorocodeField("testNumberField".toLowerCase(), ScorocodeTypes.TypeNumber, "", false, false, false);

        Collections collections = new Collections();
        collections.createCollectionField(testCollection.getCollectionName(), field, new CallbackAddField() {
            @Override
            public void onFieldAdded(ScorocodeField field) {
                //field created
                countDownLatch.countDown();
            }

            @Override
            public void onAddFieldFailed(String errorCode, String errorMessage) {
                //error during request
                ScorocodeTestHelper.printError("fail", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_21DeleteFieldFromCollection() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Collections collections = new Collections();
        collections.deleteCollectionField(testCollection.getCollectionName(), "testnumberfield", new CallbackDeleteField() {
            @Override
            public void onFieldDeleted(ScorocodeCollection collection) {
                //field deleted
                countDownLatch.countDown();
            }

            @Override
            public void onDeletionFailed(String errorCode, String errorMessage) {
                //error during request
                ScorocodeTestHelper.printError("fail", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test_22DeleteCollection() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Collections collections = new Collections();
        collections.deleteCollection(testCollection.getCollectionId(), new CallbackDeleteCollection() {
            @Override
            public void onCollectionDeleted() {
                //collection deleted
                countDownLatch.countDown();
            }

            @Override
            public void onDeletionFailed(String errorCodes, String errorMessage) {
                //error during request
                ScorocodeTestHelper.printError("wrong test conditions", errorCodes, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }


}
