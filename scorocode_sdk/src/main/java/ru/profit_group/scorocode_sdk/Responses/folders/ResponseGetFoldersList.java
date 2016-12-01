package ru.profit_group.scorocode_sdk.Responses.folders;

import java.util.List;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeFolder;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public class ResponseGetFoldersList extends ResponseCodes {

    private List<ScorocodeFolder> items;

    public ResponseGetFoldersList(List<ScorocodeFolder> items) {
        this.items = items;
    }

    public List<ScorocodeFolder> getFolders() {
        return items;
    }
}
