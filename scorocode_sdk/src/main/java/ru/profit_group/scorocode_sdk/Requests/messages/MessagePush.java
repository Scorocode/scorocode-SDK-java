package ru.profit_group.scorocode_sdk.Requests.messages;

import java.util.HashMap;

/**
 * Created by peter on 30/09/2016.
 */

public class MessagePush {
    private String text;
    private HashMap<String, Object> data;

    public MessagePush(String text, HashMap<String, Object> data) {
        this.text = text;
        this.data = data;
    }
}
