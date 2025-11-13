package com.baeldung.proxy;

public class ProxyPatternDriver {
    public static void main(String[] args) {
        ExpensiveObject object = new ExpensiveObjectProxy();    // proxy object instance
        object.process();
        object.process();
    }
}
