package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.Date;
import java.util.List;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class CloudCode {
    private String name;
    private String path;
    private String description;
    private String code;
    private String datetime;
    private boolean isActiveJob;
    private String jobType;
    private Repeat repeat;

    public CloudCode(String name, String path, String description, String code,
                     String datetime, boolean isActiveJob, JobType jobType, Repeat repeat) {
        this.name = name;
        this.path = path;
        this.description = description;
        this.code = code;
        this.datetime = datetime;
        this.isActiveJob = isActiveJob;
        this.jobType = jobType.getName();
        this.repeat = repeat;
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
