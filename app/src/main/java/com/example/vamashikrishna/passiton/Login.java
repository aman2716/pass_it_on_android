package com.example.vamashikrishna.passiton;

/**
 * Created by Vamashikrishna on 2/26/2016.
 */
public class Login {
    private long id;
    private String user;
    private String password;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Will be used by the ArrayAdapter in the ListView


}
