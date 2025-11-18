package com.baeldung.dydependencyinjection;

import com.baeldung.dependencyinjection.imagefileeditors.PngFileEditor;
import com.baeldung.dependencyinjection.loggers.TimeLogger;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ImageProcessorFactory {
    public static ImageFileProcessorDYDI createProcessor() {
        // create MANUALLY the dependencies
        PngFileEditor editor = new PngFileEditor();
        TimeLogger logger = new TimeLogger(new SimpleDateFormat("HH:mm"), Calendar.getInstance());
        
        return new ImageFileProcessorDYDI(editor, logger);
    }
}
