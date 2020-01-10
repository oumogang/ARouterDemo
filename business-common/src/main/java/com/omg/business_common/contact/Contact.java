package com.omg.business_common.contact;

public class Contact{
    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "number";
    public String number;
    public String name;
    public Contact() {}
    public Contact(String name,String number) {
        this.name = name;
        this.number = number;
    }
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
}
