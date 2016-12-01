package ru.profit_group.scorocode_sdk.Responses.fields;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeField;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public class ResponseAddField extends ResponseCodes {
    private ScorocodeField field;

    public ResponseAddField(ScorocodeField field) {
        this.field = field;
    }

    public ScorocodeField getField() {
        return field;
    }
}
