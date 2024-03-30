package com.sda.datingapp.middleware;

public interface Middleware {
    void setNext(Middleware next);
    boolean check(String username, String password);
}


