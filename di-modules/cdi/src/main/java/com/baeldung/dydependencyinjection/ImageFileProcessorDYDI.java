package com.baeldung.dydependencyinjection;

import com.baeldung.dependencyinjection.imagefileeditors.ImageFileEditor;
import com.baeldung.dependencyinjection.loggers.TimeLogger;

// NO annotations
public class ImageFileProcessorDYDI {
    private final ImageFileEditor imageFileEditor;
    private final TimeLogger timeLogger;
    
    public ImageFileProcessorDYDI(ImageFileEditor imageFileEditor, TimeLogger timeLogger) {
        this.imageFileEditor = imageFileEditor;
        this.timeLogger = timeLogger;
    }
    
    public String openFile(String fileName) {
        return imageFileEditor.openFile(fileName) + " at: " + timeLogger.getTime();
    }
    
    public String writeFile(String fileName) {
        return imageFileEditor.writeFile(fileName) + " at: " + timeLogger.getTime();
    }
    
    public String saveFile(String fileName) {
        return imageFileEditor.saveFile(fileName) + " at: " + timeLogger.getTime();
    }
}
