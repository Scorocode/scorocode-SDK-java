package ru.profit_group.scorocode_sdk;

import android.support.annotation.NonNull;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetCollection;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetCollectionsList;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateCollection;
import ru.profit_group.scorocode_sdk.scorocode_objects.Index;
import ru.profit_group.scorocode_sdk.scorocode_objects.IndexField;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeACL;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeCollection;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeField;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeTriggers;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeTypes;
import ru.profit_group.scorocode_sdk.scorocode_objects.Trigger;

import static org.junit.Assert.fail;

/**
 * Created by Peter Staranchuk on 11/28/16
 */

public class ScorocodeSdkTestCollections {

    public static final String COLLECTION_NAME = "testcollection";

    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(ScorocodeTestHelper.getAppId(), ScorocodeTestHelper.getClientKey(), ScorocodeTestHelper.getMasterKey(), null, null, null, null);
    }

    @Test
    public void test1GetCollectionsList() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.getCollectionsList(new CallbackGetCollectionsList() {
            @Override
            public void onRequestSucceed(List<ScorocodeCollection> collections) {
                countDownLatch.countDown();
            }

            @Override
            public void onRequestFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }

    @Test
    public void test2GetCollectionByName() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.getCollectionByName("devices", new CallbackGetCollection() {
            @Override
            public void onRequestSucceed(ScorocodeCollection collection) {
                countDownLatch.countDown();
            }

            @Override
            public void onRequestFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }

    @Test
    public void test3CreateCollection() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.createCollection("testcollection", false, getTestACL() , new CallbackCreateCollection() {
            @Override
            public void onCollectionCreated(ScorocodeCollection collection) {
                countDownLatch.countDown();
            }

            @Override
            public void onCreationFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }


    @Test
    public void test4UpdateCollection() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.getCollectionByName(COLLECTION_NAME, new CallbackGetCollection() {
            @Override
            public void onRequestSucceed(ScorocodeCollection collection) {
                ScorocodeSdk.updateCollection(collection.getCollectionName(), collection.getCollectionName()+"changed", false, getTestACL(), new CallbackUpdateCollection() {
                    @Override
                    public void onCollectionUpdated(ScorocodeCollection collection) {
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onUpdateFailed(String errorCode, String errorMessage) {
                        countDownLatch.countDown();
                    }
                });
            }

            @Override
            public void onRequestFailed(String errorCode, String errorMessage) {
                fail("can't perform test");
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }


    @Test
    public void test5DeleteCollection() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.getCollectionsList(new CallbackGetCollectionsList() {
            @Override
            public void onRequestSucceed(List<ScorocodeCollection> collections) {
                String id = "";
                for(ScorocodeCollection collection : collections) {
                    if(!collection.isSystemCollection()) {
                        id = collection.getCollectionId();
                        break;
                    }
                }

                if(id.isEmpty()) {
                    fail("there are no any collection to delete");
                }

                ScorocodeSdk.deleteCollection(id, new CallbackDeleteCollection() {
                    @Override
                    public void onCollectionDeleted() {
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onDetelionFailed(String errorCodes, String errorMessage) {
                        countDownLatch.countDown();
                    }
                });
            }

            @Override
            public void onRequestFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
                fail("can't perform test");
            }
        });

        countDownLatch.await();
    }

    @NonNull
    private ScorocodeCollection getTestCollection() {
        getTestACL();
        ScorocodeCollection collection = new ScorocodeCollection();
        collection.setCollectionName("newcollectionfortest11");
        collection.setSystem(false);
        collection.setACL(getTestACL());
        collection.setUseDocsACL(false);
        collection.setFields(getTestFields());
        collection.setTriggers(getTriggers());
        collection.setIndexes(getTestIndexes());
        return collection;
    }

    private ScorocodeACL getTestACL() {
        List<String> acl = new ArrayList<>();
        acl.add("*");

        return new ScorocodeACL(acl, acl, acl, acl);
    }

    @NonNull
    private List<Index> getTestIndexes() {
        List<IndexField> indexFields1 = new ArrayList<>();
        IndexField indexField11 = new IndexField("_id", 1);
        IndexField indexField12 = new IndexField("readACL", 1);
        indexFields1.add(indexField11);
        indexFields1.add(indexField12);
        Index index1 = new Index("anyIndex1", indexFields1);


        List<IndexField> indexFields2 = new ArrayList<>();
        IndexField indexField21 = new IndexField("updateACL", 1);
        IndexField indexField22 = new IndexField("removeACL", 1);
        indexFields2.add(indexField21);
        indexFields2.add(indexField22);
        Index index2 = new Index("anyIndex2", indexFields2);

        List<Index> indices = new ArrayList<>();
        indices.add(index1);
        indices.add(index2);

        return indices;
    }

    @NonNull
    private List<ScorocodeField> getTestFields() {
        List<ScorocodeField> fields = new ArrayList<>();
        ScorocodeField field1 = new ScorocodeField("dateField", ScorocodeTypes.TypeDate, "", false, false, false);
        ScorocodeField field2 = new ScorocodeField("booleanField", ScorocodeTypes.TypeBoolean, "", false, false, false);
        ScorocodeField field3 = new ScorocodeField("stringField", ScorocodeTypes.TypeString, "", false, false, false);
        ScorocodeField field4 = new ScorocodeField("fileField", ScorocodeTypes.TypeFile, "", false, false, false);
        ScorocodeField field5 = new ScorocodeField("numberField", ScorocodeTypes.TypeNumber, "", false, false, false);
        ScorocodeField field6 = new ScorocodeField("passwordField", ScorocodeTypes.TypePassword, "", false, false, false);
        ScorocodeField field7 = new ScorocodeField("arrayField", ScorocodeTypes.TypeArray, "", false, false, false);
        ScorocodeField field8 = new ScorocodeField("objectField", ScorocodeTypes.TypeObject, "", false, false, false);
        ScorocodeField field9 = new ScorocodeField("realtionField", ScorocodeTypes.TypeRelation, "", false, false, false);
        ScorocodeField field10 = new ScorocodeField("poinerField", ScorocodeTypes.TypePointer, "", false, false, false);

        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        fields.add(field4);
        fields.add(field5);
        fields.add(field6);
        fields.add(field7);
        fields.add(field8);
        fields.add(field9);
        fields.add(field10);
        return fields;
    }

    @NonNull
    private ScorocodeTriggers getTriggers() {
        ScorocodeTriggers triggers = new ScorocodeTriggers();

        triggers.setBeforeInsert(new Trigger("//code1", false))
                .setAfterInsert(new Trigger("//code2", false))
                .setBeforeUpdate(new Trigger("//code3", false))
                .setAfterUpdate(new Trigger("//code4", false))
                .setBeforeRemove(new Trigger("//code5", false))
                .setAfterRemove(new Trigger("//code6", false));

        return triggers;
    }
}
