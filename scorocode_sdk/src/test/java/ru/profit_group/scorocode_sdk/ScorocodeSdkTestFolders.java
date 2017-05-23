package ru.profit_group.scorocode_sdk;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateNewFolder;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFolder;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetFoldersList;
import ru.profit_group.scorocode_sdk.scorocode_objects.Folders;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeFolder;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 12/1/16
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScorocodeSdkTestFolders {

    public static final String TEST_FOLDER = "test_folder";

    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(ScorocodeTestHelper.getAppId(), ScorocodeTestHelper.getClientKey(), ScorocodeTestHelper.getMasterKey(), null, null, null, null);
        if(BuildConfig.DEBUG) {
            ScorocodeSdkStateHolder.setBaseURL("https://94.126.157.202");
        }
    }

    @Test
    public void test1CreateNewFolder() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.createFolder(TEST_FOLDER, new CallbackCreateNewFolder() {
            @Override
            public void onFolderCreated() {
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
    public void test2GetFoldersList() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.getFoldersList("", new CallbackGetFoldersList() {
            @Override
            public void onRequestSucceed(List<ScorocodeFolder> folderList) {
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
    public void test3DeleteFolder() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.deleteFolder(TEST_FOLDER, new CallbackDeleteFolder() {
            @Override
            public void onFolderDeleted() {
                countDownLatch.countDown();
            }

            @Override
            public void onDeletionFailed(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("test failed", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test  @Ignore //TODO check
    public void test4GetFoldersListWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Folders folders = new Folders();
        folders.getFoldersList(TEST_FOLDER, new CallbackGetFoldersList() {
            @Override
            public void onRequestSucceed(List<ScorocodeFolder> folderList) {
                //sdk returned list of folders
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
    public void test5CreateNewFolderWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Folders folders = new Folders();
        folders.createFolder("test_path", new CallbackCreateNewFolder() {
            @Override
            public void onFolderCreated() {
                //folder created
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
    public void test6DeleteFolderWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Folders folders = new Folders();
        folders.deleteFolder("test_path", new CallbackDeleteFolder() {
            @Override
            public void onFolderDeleted() {
                //folder deleted
                countDownLatch.countDown();
            }

            @Override
            public void onDeletionFailed(String errorCode, String errorMessage) {
                //error during request
                ScorocodeTestHelper.printError("test failed", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }
}
