package com.baeldung.annotations;

@FunctionalInterface
public interface Adder {
    int add(int a, int b);

    // ONLY 1! method is valid
    // if you add -> error | here
    //int div(int a, int b);
}
