package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.Map;

/**
 * Created by Peter Staranchuk on 11/24/16
 */

public class Settings {
    private boolean emailVerified;
    private Long sessionTimeout;
    private String androidApiKey;
    private String gcmSenderId;
    private Map<String, MailTemplate> mailTemplates;
    private String smtp;

    public Settings(boolean emailVerified, Long sessionTimeout, String androidApiKey,
                    String gcmSenderId, Map<String, MailTemplate> mailTemplates, String smtp) {
        this.emailVerified = emailVerified;
        this.sessionTimeout = sessionTimeout;
        this.androidApiKey = androidApiKey;
        this.gcmSenderId = gcmSenderId;
        this.mailTemplates = mailTemplates;
        this.smtp = smtp;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public Long getSessionTimeout() {
        return sessionTimeout;
    }

    public String getAndroidApiKey() {
        return androidApiKey;
    }

    public String getGcmSenderId() {
        return gcmSenderId;
    }

    public Map<String, MailTemplate> getMailTemplates() {
        return mailTemplates;
    }

    public String getSmtp() {
        return smtp;
    }
}
