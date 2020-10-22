package com.scb.blade.parser;

import com.scb.blade.matcher.NumberMatcher;
import com.scb.blade.parser.rules.NumberLineParserRules;
import com.scb.blade.reader.buffer.model.DigitInput;
import com.scb.blade.reader.buffer.model.NumberLineInput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NumberInputParserTest {
    @Mock
    private NumberMatcher numberMatcher;

    private NumberInputParser numberInputParser;
    private NumberLineParserRules numberLineParserRules;

    @Before
    public void setup() {
        this.numberLineParserRules = new NumberLineParserRules(3, true);
        numberInputParser = new NumberInputParser(this.numberLineParserRules, numberMatcher);
    }

    @Test
    public void parseValidNumberLine() {
        DigitInput digit1 = mock(DigitInput.class);
        DigitInput digit2 = mock(DigitInput.class);
        DigitInput digit3 = mock(DigitInput.class);

        when(numberMatcher.getNumber(eq(digit1))).thenReturn(Optional.of(Integer.valueOf(1)));
        when(numberMatcher.getNumber(eq(digit2))).thenReturn(Optional.of(Integer.valueOf(2)));
        when(numberMatcher.getNumber(eq(digit3))).thenReturn(Optional.of(Integer.valueOf(3)));

        NumberLineInput numberLineInput = NumberLineInput.builder()
                .digitInputs(Arrays.asList(digit1, digit2, digit3))
                .build();

        String parse = this.numberInputParser.parse(numberLineInput);
        assertEquals("123", parse);
    }

    @Test
    public void parseShortNumber() {
        DigitInput digit1 = mock(DigitInput.class);
        DigitInput digit2 = mock(DigitInput.class);

        when(numberMatcher.getNumber(eq(digit1))).thenReturn(Optional.of(Integer.valueOf(1)));
        when(numberMatcher.getNumber(eq(digit2))).thenReturn(Optional.of(Integer.valueOf(2)));

        NumberLineInput numberLineInput = NumberLineInput.builder()
                .digitInputs(Arrays.asList(digit1, digit2))
                .build();

        String parse = this.numberInputParser.parse(numberLineInput);
        assertEquals("12ILL", parse);
    }

    @Test
    public void parseInvalidNumber() {
        DigitInput digit1 = mock(DigitInput.class);
        DigitInput digit2 = mock(DigitInput.class);
        DigitInput digit3 = mock(DigitInput.class);

        when(numberMatcher.getNumber(eq(digit1))).thenReturn(Optional.of(Integer.valueOf(1)));
        when(numberMatcher.getNumber(eq(digit2))).thenReturn(Optional.empty());
        when(numberMatcher.getNumber(eq(digit3))).thenReturn(Optional.of(Integer.valueOf(3)));

        NumberLineInput numberLineInput = NumberLineInput.builder()
                .digitInputs(Arrays.asList(digit1, digit2, digit3))
                .build();

        String parse = this.numberInputParser.parse(numberLineInput);
        assertEquals("1?3ILL", parse);
    }

    @Test
    public void parseEmptyNumberLine() {
        NumberLineInput numberLineInput = NumberLineInput.builder()
                .build();

        String parse = this.numberInputParser.parse(numberLineInput);
        assertEquals("ILL", parse);
    }
}