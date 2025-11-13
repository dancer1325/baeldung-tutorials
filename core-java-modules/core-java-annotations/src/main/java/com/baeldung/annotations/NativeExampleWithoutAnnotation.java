package com.baeldung.annotations;

public class NativeExampleWithoutAnnotation {
    
    public static final int SUCCESS = 0;
    public static final int ERROR = -1;
    public static final int TIMEOUT = 1;
    
    private native int processData(String data);
}
