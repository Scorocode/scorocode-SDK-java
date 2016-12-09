package ru.profit_group.scorocode_sdk;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Created by Peter Staranchuk on 10/21/16
 */

public class ScorocodeTestHelper {
    //prod server
    private static final String PROD_APP_ID = "305ffd6cc32832f6819bf4e4f4707848"; // enter your applicationId
    private static final String PROD_CLIENT_KEY = "962066371eefc0d1850a76c7ab14c1dc"; // enter your clientKey
    private static final String PROD_MASTER_KEY = "383499df2748bb4560745d5da67f5e41"; // enter your master key

    //test server
    private static final String TEST_APP_ID = "f0460813a2e149608a0d62a654395da0"; // enter your applicationId
    private static final String TEST_CLIENT_KEY = "e8a8e407045041918b791e9bc336c413"; // enter your clientKey
    private static final String TEST_MASTER_KEY = "4deea14c6d264e3dba8c1866409463f4"; // enter your master key

    public static final String ANY_REAL_SCRIPT_ID = "57f65c8942d52f1ba275fb68"; // enter scriptId of your "hello world script". tests will run this script
    public static final String TEST_COLLECTION_NAME = "testscorocodesdkcollection";
    public static final String NUMBER_FIELD_1 = "numberField1";
    public static final String TEXT_FIELD_1 = "textField1";
    public static final String DATE_FIELD_1 = "dateField1";
    public static final String ARRAY_FIELD_1 = "arrayField1";

    static void printError(String message, String errorCode, String errorMessage) {
        fail(message + " Error Code: " + errorCode + " Error message: " + errorMessage);
        assertNotEquals(null, errorCode);
        assertNotEquals(null, errorMessage);
    }

    public static String getAppId() {
        return BuildConfig.DEBUG? TEST_APP_ID : PROD_APP_ID;
    }

    public static String getClientKey() {
        return BuildConfig.DEBUG? TEST_CLIENT_KEY : PROD_CLIENT_KEY;
    }

    public static String getMasterKey() {
        return BuildConfig.DEBUG? TEST_MASTER_KEY : PROD_MASTER_KEY;
    }
}
