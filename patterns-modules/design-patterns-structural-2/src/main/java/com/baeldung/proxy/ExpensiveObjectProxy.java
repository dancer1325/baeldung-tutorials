package com.baeldung.proxy;

// proxy pattern class
//      1. intermediary -- to -- ExpensiveObjectImpl
//      2. hide ExpensiveObjectImpl's complexity
public class ExpensiveObjectProxy implements ExpensiveObject{
    private static ExpensiveObject object;

    @Override
    public void process() {
        // initial configuration
        if(object == null) {
            object = new ExpensiveObjectImpl();
        }
        object.process();
    }
}
