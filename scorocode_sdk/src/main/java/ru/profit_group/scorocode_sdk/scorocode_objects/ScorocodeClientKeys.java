package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public class ScorocodeClientKeys {
    private String android;
    private String ios;
    private String javascript;
    private String winphone;

    public ScorocodeClientKeys(String android, String ios, String javascript, String winphone) {
        this.android = android;
        this.ios = ios;
        this.javascript = javascript;
        this.winphone = winphone;
    }

    public String getAndroidClientKey() {
        return android;
    }

    public String getIosClientKey() {
        return ios;
    }

    public String getJavascriptClientKey() {
        return javascript;
    }

    public String getWinphoneClientKey() {
        return winphone;
    }
}
