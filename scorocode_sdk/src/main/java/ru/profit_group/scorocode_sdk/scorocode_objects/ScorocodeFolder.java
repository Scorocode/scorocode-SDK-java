package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public class ScorocodeFolder {
    private String _id;
    private String name;
    private String path;
    private boolean isScript;

    public ScorocodeFolder(String _id, String name, String path, boolean isScript) {
        this._id = _id;
        this.name = name;
        this.path = path;
        this.isScript = isScript;
    }

    @NonNull
    public String getFolderId() {
        return _id == null? "" : _id;
    }

    @NonNull
    public String getFolderName() {
        return name == null? "" : name;
    }

    @NonNull
    public String getFolderPath() {
        return path == null? "" : path;
    }

    @NonNull
    public boolean isFolderContainScript() {
        return isScript;
    }
}
