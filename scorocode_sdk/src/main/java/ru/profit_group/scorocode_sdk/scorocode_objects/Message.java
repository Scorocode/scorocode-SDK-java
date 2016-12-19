package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.io.Serializable;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendPush;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendSms;
import ru.profit_group.scorocode_sdk.Requests.messages.MessagePush;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageSms;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 10/7/16
 */

public class Message implements Serializable {

    private boolean isDebugMode;

    public Message(boolean isDebugMode) {
        this.isDebugMode = isDebugMode;
    }

    public Message() {
        this.isDebugMode = false;
    }

    public void sendPush(MessagePush messagePush, Query query, CallbackSendPush callback) throws IllegalStateException {
        ScorocodeSdk.sendPush(query.getCollectionName(), query, messagePush, isDebugMode, callback);
    }

    public void sendPush(MessagePush messagePush, CallbackSendPush callback) throws IllegalStateException {
        ScorocodeSdk.sendPush("users", null, messagePush, isDebugMode, callback);
    }

    public void sendSms(MessageSms messageSms, Query query, CallbackSendSms callback) throws IllegalStateException {
        ScorocodeSdk.sendSms(query.getCollectionName(), query, messageSms, isDebugMode, callback);
    }

    public void sendSms(MessageSms messageSms, CallbackSendSms callback) throws IllegalStateException {
        ScorocodeSdk.sendSms("users", null, messageSms, isDebugMode, callback);
    }

}
