package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class ScorocodeScript {
    private String _id;
    private String name;
    private String path;
    private String description;
    private String code;
    private boolean isActiveJob;
    private String jobStartAt;
    private String jobType;
    private Repeat repeat;
    private String nextRun;
    private List<String> ACL;

    public ScorocodeScript() {}

    public ScorocodeScript(String name, String path, String description, String code,
                           boolean isActiveJob, String jobStartAt, JobType jobType, Repeat repeat, String nextRun, List<String> ACL) {
        this.name = name;
        this.path = path;
        this.description = description;
        this.code = code;
        this.isActiveJob = isActiveJob;
        this.jobType = jobType.getName();
        this.repeat = repeat;
        this.jobStartAt = jobStartAt;
        this.nextRun = nextRun;
        this.ACL = ACL;
    }

    @NonNull
    public ScorocodeScript setScriptId(String _id) {
        this._id = _id;
        return this;
    }

    @NonNull
    public ScorocodeScript setScriptName(String name) {
        this.name = name;
        return this;
    }

    @NonNull
    public ScorocodeScript setScriptPath(String path) {
        this.path = path;
        return this;
    }

    @NonNull
    public ScorocodeScript setScriptDescription(String description) {
        this.description = description;
        return this;
    }

    @NonNull
    public ScorocodeScript setScriptSourceCode(String code) {
        this.code = code;
        return this;
    }

    @NonNull
    public ScorocodeScript setIsActiveJob(boolean activeJob) {
        isActiveJob = activeJob;
        return this;
    }

    @NonNull
    public ScorocodeScript setJobStartAt(String jobStartAt) {
        this.jobStartAt = jobStartAt;
        return this;
    }

    @NonNull
    public ScorocodeScript setJobType(String jobType) {
        this.jobType = jobType;
        return this;
    }

    @NonNull
    public ScorocodeScript setRepeat(Repeat repeat) {
        this.repeat = repeat;
        return this;
    }

    @NonNull
    public ScorocodeScript setNextRun(String nextRun) {
        this.nextRun = nextRun;
        return this;
    }

    @NonNull
    public ScorocodeScript setScriptACL(List<String> ACL) {
        this.ACL = ACL;
        return this;
    }

    @NonNull
    public String getScriptName() {
        return name == null? "" : name;
    }

    @NonNull
    public String getScriptPath() {
        return path == null? " " : path;
    }

    @NonNull
    public String getScriptDescription() {
        return description == null? "" : description;
    }

    @NonNull
    public String getScriptSourceCode() {
        return code == null? "" : code;
    }

    public boolean isActiveJob() {
        return isActiveJob;
    }

    @NonNull
    public String getJobStartAt() {
        return jobStartAt == null? "" : jobStartAt;
    }

    @NonNull
    public String getJobType() {
        return jobType == null? "" : jobType;
    }

    @NonNull
    public Repeat getRepeat() {
        return repeat == null? (new Repeat()) : repeat;
    }

    @NonNull
    public String getNextRun() {
        return nextRun;
    }

    @NonNull
    public List<String> getACL() {
        return ACL == null? new ArrayList<String>() : ACL;
    }

    @NonNull
    public String getScriptId() {
        return _id == null? "" : _id;
    }

    private enum JobType {
        CUSTOM(TypeCustom.getName()),
        DAILY(TypeDaily.getName()),
        MOTHLY(TypeMonthly.getName());

        private String name;
        JobType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
