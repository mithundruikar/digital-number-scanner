package com.scb.blade.number.sink;

import com.scb.blade.parser.ParsingResult;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;

public class SinkFormatterTest {

    @Test
    public void formatMatchingResult() {
        ParsingResult parsingResult = new ParsingResult(Arrays.asList(Optional.of(Integer.valueOf(1)),
                Optional.of(Integer.valueOf(2)),
                Optional.of(Integer.valueOf(3))), true);

        SinkFormatter sinkFormatter = new SinkFormatter();
        assertEquals("123", sinkFormatter.format(parsingResult));
    }

    @Test
    public void formatResultWithInvalidDigit() {
        ParsingResult parsingResult = new ParsingResult(Arrays.asList(Optional.of(Integer.valueOf(1)),
                Optional.empty(),
                Optional.of(Integer.valueOf(3))), true);

        SinkFormatter sinkFormatter = new SinkFormatter();
        assertEquals("1?3ILL", sinkFormatter.format(parsingResult));
    }

    @Test
    public void formatResultWithNoDigit() {
        ParsingResult parsingResult = new ParsingResult(Collections.emptyList(), false);

        SinkFormatter sinkFormatter = new SinkFormatter();
        assertEquals("ILL", sinkFormatter.format(parsingResult));
    }

    @Test
    public void formatResultWithMatchButFailingRules() {
        ParsingResult parsingResult = new ParsingResult(Arrays.asList(Optional.of(Integer.valueOf(1)),
                Optional.of(Integer.valueOf(2)),
                Optional.of(Integer.valueOf(3))), false);

        SinkFormatter sinkFormatter = new SinkFormatter();
        assertEquals("123ILL", sinkFormatter.format(parsingResult));
    }
}