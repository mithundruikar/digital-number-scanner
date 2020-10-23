package com.scb.blade.reader.file.scanner;

import org.junit.Test;
import rx.Observable;

import java.io.IOException;

import static org.junit.Assert.*;

public class NumberTextScannerTest {

    @Test
    public void init() throws IOException {
        NumberTextScanner numberTextScanner = new NumberTextScanner("multipleChunks");
        numberTextScanner.init();

        assertNotNull(numberTextScanner.getBufferedReader());
        numberTextScanner.getBufferedReader().close();
    }

    @Test
    public void scanner() throws IOException {
        NumberTextScanner numberTextScanner = new NumberTextScanner("multipleChunks");
        numberTextScanner.init();

        StringBuilder sb = new StringBuilder();
        String nextLine = null;
        while((nextLine = numberTextScanner.readNextLine()) != null) {
            sb.append(nextLine+"\n");
        }

        String expectedContent = "    _  _     _  _  _  _  _\n" +
                "  | _| _||_||_ |_   ||_||_|\n" +
                "  ||_  _|  | _||_|  ||_| _|\n" +
                "\n" +
                "    _  _     _  _  _  _  _\n" +
                "  | _| _||_||_ |_   ||_||_|\n" +
                "  ||_  _|  | _||_|  ||_| _|\n" +
                "\n" +
                "    _  _     _  _  _  _  _\n" +
                "  | _| _||_||_ |_   ||_||_|\n" +
                "  ||_  _|  | _||_|  ||_| _|\n" +
                "";

        assertEquals(expectedContent, sb.toString());
        numberTextScanner.getBufferedReader().close();
    }

    @Test
    public void scannerWithObservable() throws IOException {
        String expectedContent = "    _  _     _  _  _  _  _\n" +
                "  | _| _||_||_ |_   ||_||_|\n" +
                "  ||_  _|  | _||_|  ||_| _|\n" +
                "\n" +
                "    _  _     _  _  _  _  _\n" +
                "  | _| _||_||_ |_   ||_||_|\n" +
                "  ||_  _|  | _||_|  ||_| _|\n" +
                "\n" +
                "    _  _     _  _  _  _  _\n" +
                "  | _| _||_||_ |_   ||_||_|\n" +
                "  ||_  _|  | _||_|  ||_| _|\n" +
                "";


        NumberTextScanner numberTextScanner = new NumberTextScanner("multipleChunks");
        Observable<String> observe = numberTextScanner.observe();

        StringBuilder sb = new StringBuilder();
        observe
                .doOnCompleted(() -> {
                    assertEquals(expectedContent, sb.toString());
                    try {
                        numberTextScanner.getBufferedReader().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        fail();
                    }
                })
                .subscribe(line ->  sb.append(line+"\n"));



    }
}