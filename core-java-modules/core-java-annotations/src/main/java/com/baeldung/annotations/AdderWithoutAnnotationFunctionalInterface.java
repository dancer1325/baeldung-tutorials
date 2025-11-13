package com.baeldung.annotations;

public interface AdderWithoutAnnotationFunctionalInterface {
    int add(int a, int b);

    // if you add NEXT method -> ERROR | ANY lambda usage
    //int multiply(int a, int b); // Breaks ALL lambda usages
}
