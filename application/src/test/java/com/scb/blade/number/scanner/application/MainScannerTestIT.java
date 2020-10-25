package com.scb.blade.number.scanner.application;

import com.scb.blade.number.scanner.application.config.NumberScannerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NumberScannerConfig.class)
public class MainScannerTestIT {

    @Autowired
    private MainScanner mainScanner;

    @Test
    public void startWithValidFile() {
        List<String> multipleChunks = mainScanner.start("multipleChunks");
        assertEquals(Arrays.asList("123456789", "123456789", "123456789"), multipleChunks);
    }

    @Test
    public void startWithFileWithInvalidLetters() {
        List<String> multipleChunks = mainScanner.start("multipleChunksWithIllegalRow");
        assertEquals(Arrays.asList("123456789", "123456?89ILL", "123456789"), multipleChunks);
    }

    @Test
    public void startWithSingleLineChunk() {
        List<String> multipleChunks = mainScanner.start("singleChunk");
        assertEquals(Arrays.asList("000000000"), multipleChunks);
    }

    @Test
    public void startWithEmptyFile() {
        List<String> multipleChunks = mainScanner.start("emptyChunk");
        assertTrue(multipleChunks.isEmpty());
    }

    @Test
    public void startWithInvalidFilePath() {
        List<String> multipleChunks = mainScanner.start("invalidFilePath");
        assertTrue(multipleChunks.isEmpty());
    }

}