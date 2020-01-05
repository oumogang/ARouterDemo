package com.omg.arouter;

public class Contact {
    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "number";
    String number;
    String name;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact(String number, String name) {
        this.number = number;
        this.name = name;
    }
}
