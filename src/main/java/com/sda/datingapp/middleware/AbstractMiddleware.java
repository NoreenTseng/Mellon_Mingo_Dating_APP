package com.sda.datingapp.middleware;

public abstract class AbstractMiddleware implements Middleware {
    protected Middleware next;

    @Override
    public void setNext(Middleware next) {
        this.next = next;
    }

    @Override
    public boolean check(String username, String password) {
        if (next == null) {
            return true;
        }
        return next.check(username, password);
    }
}

