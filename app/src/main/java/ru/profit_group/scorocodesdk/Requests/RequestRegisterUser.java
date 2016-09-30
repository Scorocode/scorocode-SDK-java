package ru.profit_group.scorocodesdk.Requests;

import java.util.HashMap;

import ru.profit_group.scorocodesdk.Objects.Doc;

/**
 * Created by peter staranchuk on 21/09/16.
 */
public class RequestRegisterUser {
    private String app;
    private String cli;
    private String username;
    private String email;
    private String password;
    private HashMap<String, String> doc;

    public RequestRegisterUser(String app, String cli, String username, String email, String password) {
        this.app = app;
        this.cli = cli;
        this.username = username;
        this.email = email;
        this.password = password;
        this.doc = null;
    }

    public RequestRegisterUser(String app, String cli, String username, String email, String password, HashMap<String, String>  doc) {
        this.app = app;
        this.cli = cli;
        this.username = username;
        this.email = email;
        this.password = password;
        this.doc = doc;
    }

    public String getApp() {
        return app;
    }

    public String getCli() {
        return cli;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<String, String>  getDoc() {
        if(doc == null) {
            return new HashMap<>();
        }
        return doc;
    }
}
