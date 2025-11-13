package com.baeldung.annotations;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FunctionalInterfaceUnitTest {

    @Test
    public void testAdder() {
        Adder adder = (a, b) -> a+b;    //  interface's method -- as -- lambda expression
        int result = adder.add(4,5);
        assertThat(result, is(9));
    }

    @Test
    public void testAdderWithoutFunctionalInterface() {
        AdderWithoutAnnotationFunctionalInterface adder1 = (a, b) -> a+b;
        adder1.add(2,4);
        AdderWithoutAnnotationFunctionalInterface adder2 = (a, b) -> a+b+2;
        adder2.add(2,4);
        AdderWithoutAnnotationFunctionalInterface adder3 = (a, b) -> Math.max(a,b);
        adder3.add(2,4);
    }
}
