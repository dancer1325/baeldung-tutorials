package com.baeldung.dydependencyinjection;

public class FileApplicationDYDI {
    
    public static void main(String[] args) {
        ImageFileProcessorDYDI processor = ImageProcessorFactory.createProcessor();
        
        System.out.println(processor.openFile("file1.png"));
        System.out.println(processor.writeFile("file1.png"));
        System.out.println(processor.saveFile("file1.png"));
    }
}
