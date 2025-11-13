package com.baeldung.annotations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NativeExampleTest {

    @Test
    public void testNativeConstants() {
        // Prove constants are accessible and have correct values
        assertEquals(0, NativeExample.SUCCESS);
        assertEquals(-1, NativeExample.ERROR);
        assertEquals(1, NativeExample.TIMEOUT);
    }
    
    @Test
    public void testHeaderFileGenerated() {
        // The real proof: C header file was generated with #define statements
        // This demonstrates @Native's purpose for JNI integration
        java.io.File headerFile = new java.io.File("target/com_baeldung_annotations_NativeExample.h");
        assertTrue(headerFile.exists(), "@Native annotation enabled C header generation");
    }
}
