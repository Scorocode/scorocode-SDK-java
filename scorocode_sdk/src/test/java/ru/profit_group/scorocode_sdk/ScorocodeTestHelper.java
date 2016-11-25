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
    private static final String TEST_APP_ID = "f1b9af901260558d55a4bc0125fdb46f"; // enter your applicationId
    private static final String TEST_CLIENT_KEY = "2df5deb956228be2fecef6ff21ab7d6e"; // enter your clientKey
    private static final String TEST_MASTER_KEY = "c78973d53a6b7bd856e2432e89abfbf5"; // enter your master key

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
