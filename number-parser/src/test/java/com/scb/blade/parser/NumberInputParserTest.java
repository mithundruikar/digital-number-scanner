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
    @Mock
    private NumberLineParserRules numberLineParserRules;

    private NumberInputParser numberInputParser;


    @Before
    public void setup() {
        numberInputParser = new NumberInputParser(this.numberLineParserRules, numberMatcher);
    }

    @Test
    public void parseValidNumberLine() {
        DigitInput digit1 = getDigitInput();
        DigitInput digit2 = getDigitInput();
        DigitInput digit3 = getDigitInput();

        when(numberMatcher.getNumber(same(digit1))).thenReturn(Optional.of(Integer.valueOf(1)));
        when(numberMatcher.getNumber(same(digit2))).thenReturn(Optional.of(Integer.valueOf(2)));
        when(numberMatcher.getNumber(same(digit3))).thenReturn(Optional.of(Integer.valueOf(3)));

        when(this.numberLineParserRules.valid(any())).thenReturn(true);

        NumberLineInput numberLineInput = NumberLineInput.builder(3)
                .digitInputs(Arrays.asList(digit1, digit2, digit3))
                .build();

        ParsingResult parsingResult = new ParsingResult(Arrays.asList(Optional.of(Integer.valueOf(1)),
                Optional.of(Integer.valueOf(2)),
                Optional.of(Integer.valueOf(3))), true);
        assertEquals(parsingResult, this.numberInputParser.parse(numberLineInput));
    }


    @Test
    public void parseInvalidNumber() {
        DigitInput digit1 = getDigitInput();
        DigitInput digit2 = getDigitInput();
        DigitInput digit3 = getDigitInput();

        when(numberMatcher.getNumber(same(digit1))).thenReturn(Optional.of(Integer.valueOf(1)));
        when(numberMatcher.getNumber(same(digit2))).thenReturn(Optional.empty());
        when(numberMatcher.getNumber(same(digit3))).thenReturn(Optional.of(Integer.valueOf(3)));

        when(this.numberLineParserRules.valid(any())).thenReturn(false);

        NumberLineInput numberLineInput = NumberLineInput.builder(3)
                .digitInputs(Arrays.asList(digit1, digit2, digit3))
                .build();

        ParsingResult parsingResult = new ParsingResult(Arrays.asList(Optional.of(Integer.valueOf(1)),
                Optional.empty(),
                Optional.of(Integer.valueOf(3))), false);
        assertEquals(parsingResult, this.numberInputParser.parse(numberLineInput));
    }

    private DigitInput getDigitInput() {
        return DigitInput.builder().build();
    }

}