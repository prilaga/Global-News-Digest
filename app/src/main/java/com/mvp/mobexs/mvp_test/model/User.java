package com.mvp.mobexs.mvp_test.model;

/**
 * Created by Oleg Tarashkevich on 27/03/2017.
 */

public class User implements IUser {

    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean validate(String name, String password) {
        return name != null && password != null && name.equalsIgnoreCase(getName()) && password.equalsIgnoreCase(getPassword());
    }
}
