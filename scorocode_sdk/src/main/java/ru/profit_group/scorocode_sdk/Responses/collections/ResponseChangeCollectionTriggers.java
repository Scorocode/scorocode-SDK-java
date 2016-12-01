package ru.profit_group.scorocode_sdk.Responses.collections;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeTriggers;

/**
 * Created by Peter Staranchuk on 12/1/16
 */

public class ResponseChangeCollectionTriggers extends ResponseCodes {
    private ScorocodeTriggers triggers;

    public ResponseChangeCollectionTriggers(ScorocodeTriggers triggers) {
        this.triggers = triggers;
    }

    public ScorocodeTriggers getTriggers() {
        return triggers;
    }
}
