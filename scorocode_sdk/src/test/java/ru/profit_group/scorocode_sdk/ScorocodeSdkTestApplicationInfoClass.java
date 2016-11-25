package ru.profit_group.scorocode_sdk;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackApplicationStatistic;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetApplicationInfo;
import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeApplicationInfo;

/**
 * Created by Peter Staranchuk on 10/20/16
 */

import org.junit.runners.MethodSorters;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScorocodeSdkTestApplicationInfoClass {

    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(ScorocodeTestHelper.getAppId(), ScorocodeTestHelper.getClientKey(), ScorocodeTestHelper.getMasterKey(), null, null, null, null);
    }

    @Test
    public void test1GetStatistic() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

            ScorocodeSdk.getApplicationStatistic(
                    new CallbackApplicationStatistic() {
                        @Override
                        public void onRequestSucceed(ResponseAppStatistic appStatistic) {
                            assertEquals(false, appStatistic.isError());
                            assertEquals(null, appStatistic.getErrCode());
                            assertEquals(null, appStatistic.getErrMsg());
                            countDownLatch.countDown();
                        }

                        @Override
                        public void onRequestFailed(String errorCode, String errorMessage) {
                            assertNotEquals(null, errorCode);
                            assertNotEquals(null, errorMessage);
                            fail("ошибка при получении статистики");
                            countDownLatch.countDown();
                        }
                    }
            );

        countDownLatch.await();
    }

    @Test
    public void test2GetApplicationInfo() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        ScorocodeSdk.getApplicationInfo(new CallbackGetApplicationInfo() {
            @Override
            public void onRequestSucceed(ScorocodeApplicationInfo appInfo) {
                countDownLatch.countDown();
            }

            @Override
            public void onRequestFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

}
