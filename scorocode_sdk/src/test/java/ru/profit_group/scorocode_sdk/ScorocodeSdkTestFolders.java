package ru.profit_group.scorocode_sdk;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateNewFolder;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFolder;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetFoldersList;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeFolder;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public class ScorocodeSdkTestFolders {

    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(ScorocodeTestHelper.getAppId(), ScorocodeTestHelper.getClientKey(), ScorocodeTestHelper.getMasterKey(), null, null, null, null);
    }

    @Test
    public void test1GetFoldersList() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.getFoldersList("", new CallbackGetFoldersList() {
            @Override
            public void onRequestSucceed(List<ScorocodeFolder> folderList) {
                countDownLatch.countDown();
            }

            @Override
            public void onRequestFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
                ScorocodeTestHelper.printError("test failed", errorCode, errorMessage);
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test2CreateNewFolder() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.createFolder("test_folder", new CallbackCreateNewFolder() {
            @Override
            public void onFolderCreated() {
                countDownLatch.countDown();
            }

            @Override
            public void onCreationFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
                ScorocodeTestHelper.printError("test failed", errorCode, errorMessage);
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test3DeleteFolder() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.deleteFolder("test_folder", new CallbackDeleteFolder() {
            @Override
            public void onFolderDeleted() {
                countDownLatch.countDown();
            }

            @Override
            public void onDeletionFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
                ScorocodeTestHelper.printError("test failed", errorCode, errorMessage);
            }
        });

        countDownLatch.await();
    }
}
