package ru.profit_group.scorocode_sdk.Responses.data;

import java.util.List;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;

/**
 * Created by Peter Staranchuk on 5/10/16
 */

public class ResponseUpdate extends ResponseCodes{
    private Result result;

    public ResponseUpdate(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public class Result {
        private int count;
        private List<String> docs;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<String> getDocs() {
            return docs;
        }

        public void setDocs(List<String> docs) {
            this.docs = docs;
        }

    }
}
