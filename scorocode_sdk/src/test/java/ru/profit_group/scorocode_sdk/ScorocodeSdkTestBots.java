package ru.profit_group.scorocode_sdk;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateBot;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetBotList;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateBot;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeBot;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public class ScorocodeSdkTestBots {

    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(ScorocodeTestHelper.getAppId(), ScorocodeTestHelper.getClientKey(), ScorocodeTestHelper.getMasterKey(), null, null, null, null);
    }

    @Test
    public void test1GetBotsList() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.getBotList(new CallbackGetBotList() {
            @Override
            public void onRequestSucceed(List<ScorocodeBot> botList) {
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
    public void test2CreateBot() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        String botName = "testBotName";
        String botId = "telegramTestBotId";
        String scriptId = "583d5b4d5a5a007958c443b4";

        ScorocodeSdk.createBot(botName, botId, scriptId, false, new CallbackCreateBot() {
            @Override
            public void onBotCreated(ScorocodeBot bot) {
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
    public void test3UpdateBot() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        String botName = "testBotName";
        String telegramTestBotId11 = "telegramTestBotId";
        String scriptId = "583d5b4d5a5a007958c443b4";

        ScorocodeBot bot = new ScorocodeBot("updated"+botName, "updated"+telegramTestBotId11, scriptId, false);

        ScorocodeSdk.updateBot("58401d165a5a0012c35b682e", bot, new CallbackUpdateBot() {
            @Override
            public void onBotUpdated(ScorocodeBot bot) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
                ScorocodeTestHelper.printError("test failed", errorCode, errorMessage);
            }
        });

        countDownLatch.await();
    }



}
