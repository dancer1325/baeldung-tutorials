package com.baeldung.annotations;

import java.lang.annotation.Native;

public class NativeExample {
    
    @Native
    public static final int SUCCESS = 0;
    @Native public static final int ERROR = -1;
    @Native public static final int TIMEOUT = 1;
    
    // Native method that would use these constants
    private native int processData(String data);
    
    static {
        // Load native library (commented out as we don't have actual .so/.dll)
        // System.loadLibrary("nativeexample");
    }
}
