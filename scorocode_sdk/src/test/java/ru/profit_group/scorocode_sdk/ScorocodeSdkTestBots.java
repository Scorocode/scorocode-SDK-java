package ru.profit_group.scorocode_sdk;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateBot;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCreateScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteBot;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetBotList;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateBot;
import ru.profit_group.scorocode_sdk.scorocode_objects.Bot;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeBot;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeScript;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;
import ru.profit_group.scorocode_sdk.scorocode_objects.Script;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScorocodeSdkTestBots {

    private static String botName;
    private static String telegramBotId;
    private static String scriptId;
    private static String botId;

    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(ScorocodeTestHelper.getAppId(), ScorocodeTestHelper.getClientKey(), ScorocodeTestHelper.getMasterKey(), null, null, null, null);
        if(BuildConfig.DEBUG) {
            ScorocodeSdkStateHolder.setBaseURL("https://94.126.157.202");
        }

        botName = "scorocodeSdkTestBotName";
        telegramBotId = "scorocodeSdkTestTelegramBotId";
        createScriptForTest();
    }

    @Test
    public void test1CreateBot() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.createBot(botName, telegramBotId, scriptId, false, new CallbackCreateBot() {
            @Override
            public void onBotCreated(ScorocodeBot bot) {
                botId = bot.getBotId();
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
    public void test2GetBotsList() throws InterruptedException {
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
    public void test3UpdateBot() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);


        ScorocodeBot bot = new ScorocodeBot("updated"+botName, "updated"+ telegramBotId, scriptId, false);

        ScorocodeSdk.updateBot(botId, bot, new CallbackUpdateBot() {
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


    @Test
    public void test4DeleteBot() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.deleteBot(botId, new CallbackDeleteBot() {
            @Override
            public void onBotDeleted() {
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

    @Test
    public void test5CreateBotWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeBot botInfo = new ScorocodeBot(botName, telegramBotId, scriptId, false);
        Bot bot = new Bot();
        bot.createBot(botInfo, new CallbackCreateBot() {
            @Override
            public void onBotCreated(ScorocodeBot bot) {
                botId = bot.getBotId();
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
    public void test6GetBotsListWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Bot bot = new Bot();
        bot.getBotsList(new CallbackGetBotList() {
            @Override
            public void onRequestSucceed(List<ScorocodeBot> botList) {
                //sdk returned bot list
                countDownLatch.countDown();
            }

            @Override
            public void onRequestFailed(String errorCode, String errorMessage) {
                //error during request
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }


    @Test
    public void test7UpdateBotWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeBot newBotInfo = new ScorocodeBot("updated"+botName, "updated"+ telegramBotId, scriptId, false);

        Bot bot = new Bot();
        bot.updateBot(botId, newBotInfo, new CallbackUpdateBot() {
            @Override
            public void onBotUpdated(ScorocodeBot bot) {
                //bot updated
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
    public void test8DeleteBotWithClass() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Bot bot = new Bot();
        bot.deleteBot(botId, new CallbackDeleteBot() {
            @Override
            public void onBotDeleted() {
                //bot deleted
                countDownLatch.countDown();
            }

            @Override
            public void onDeletionFailed(String errorCode, String errorMessage) {
                //error during request
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        new Script().deleteScript(scriptId, new CallbackDeleteScript() {
            @Override
            public void onScriptDeleted() {
                countDownLatch.countDown();
            }

            @Override
            public void onDeletionFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    private static void createScriptForTest() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeScript script = new ScorocodeScript();
        script.setScriptName("botTestClassTestBot.js");
        script.setScriptPath("botTestClassTestBot.js");
        new Script().createScript(script, new CallbackCreateScript() {
            @Override
            public void onScriptCreated(ScorocodeScript script) {
                scriptId = script.getScriptId();
                countDownLatch.countDown();
            }

            @Override
            public void onCreationFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
                ScorocodeTestHelper.printError("can't create script for test", errorCode, errorMessage);
            }
        });

        countDownLatch.await();
    }

}
