package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public class ScorocodePublicKeys {
    private String masterKey;
    private String fileKey;
    private String messageKey;
    private String scriptKey;
    private String websocketKey;

    public ScorocodePublicKeys(String masterKey, String fileKey, String messageKey, String scriptKey, String websocketKey) {
        this.masterKey = masterKey;
        this.fileKey = fileKey;
        this.messageKey = messageKey;
        this.scriptKey = scriptKey;
        this.websocketKey = websocketKey;
    }

    public String getMasterKey() {
        return masterKey;
    }

    public String getFileKey() {
        return fileKey;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getScriptKey() {
        return scriptKey;
    }

    public String getWebsocketKey() {
        return websocketKey;
    }
}
