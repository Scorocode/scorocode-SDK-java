package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Peter Staranchuk on 10/17/16
 */

public class DocumentInfo implements Serializable {

    private static final String ID_KEY = "_id";
    private HashMap<String, Object> content;

    public DocumentInfo() {
        content = new HashMap<>();
    }

    public DocumentInfo(HashMap<String, Object> documentContent) {
        content = new HashMap<>();
        if(documentContent != null) {
            content.putAll(documentContent);
        }
    }

    public String getId() {
        return (String) content.get(ID_KEY);
    }

    public HashMap<String, Object> getFields() {
        HashMap<String, Object> fields = new HashMap<>();
        for(String key : content.keySet()) {
            if(!key.equalsIgnoreCase(ID_KEY)) {
                fields.put(key, content.get(key));
            }
        }

        return fields;
    }

    @NonNull
    public Object get(String key) {
        if(content != null) {
            return content.get(key);
        } else {
            return new Object();
        }
    }

    public String getString(String key) {
        return String.valueOf(get(key));
    }

    public Double getDouble(String key) {
        return Double.valueOf(getString(key));
    }

    public int getInteger(String key) {
        String numberString = getString(key);
        return Integer.valueOf(numberString.substring(0, numberString.indexOf(".")));
    }

    public long getLong(String key) {
        String numberString = getString(key);
        return Long.valueOf(numberString.substring(0, numberString.indexOf(".")));
    }

    public Boolean getBoolean(String key) {
        return Boolean.valueOf(getString(key));
    }

    public void put(String field, Object value) {
        if(content != null) {
            content.put(field, value);
        }
    }

    public HashMap<String, Object> getContent() {
        return content;
    }
}
