package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

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
        if(numberString.isEmpty()) {
            return 0;
        }

        if(numberString.contains(".")) {
            return Integer.valueOf(numberString.substring(0, numberString.indexOf(".")));
        }

        return Integer.valueOf(numberString);
    }

    @Nullable
    public Date getDate(String key) {
        String date = getString(key);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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
