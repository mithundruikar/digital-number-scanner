package com.scb.blade.parser.rules;

import com.scb.blade.reader.buffer.model.DigitInput;
import com.scb.blade.reader.buffer.model.NumberLineInput;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class NumberLineParserRulesTest {

    @Test
    public void validDigitsWithStrictLength() {
        NumberLineParserRules numberLineParserRules = new NumberLineParserRules(3, false);
        DigitInput digit1 = getDigitInput();
        DigitInput digit2 = getDigitInput();
        DigitInput digit3 = getDigitInput();

        NumberLineInput numberLineInput = NumberLineInput.builder()
                .digitInputs(Arrays.asList(digit1, digit2, digit3))
                .build();

        assertTrue(numberLineParserRules.valid(numberLineInput));
    }

    @Test
    public void validDigitsWithVariableLegth() {
        NumberLineParserRules numberLineParserRules = new NumberLineParserRules(3, true);
        DigitInput digit1 = getDigitInput();
        DigitInput digit2 = getDigitInput();

        NumberLineInput numberLineInput = NumberLineInput.builder()
                .digitInputs(Arrays.asList(digit1, digit2))
                .build();

        assertTrue(numberLineParserRules.valid(numberLineInput));
    }

    @Test
    public void moreDigits() {
        NumberLineParserRules numberLineParserRules = new NumberLineParserRules(3, false);
        DigitInput digit1 = getDigitInput();
        DigitInput digit2 = getDigitInput();

        NumberLineInput numberLineInput = NumberLineInput.builder()
                .digitInputs(Arrays.asList(digit1, digit2, getDigitInput(), getDigitInput()))
                .build();

        assertFalse(numberLineParserRules.valid(numberLineInput));
    }

    @Test
    public void moreDigitsWithVariableLegth() {
        NumberLineParserRules numberLineParserRules = new NumberLineParserRules(3, true);
        DigitInput digit1 = getDigitInput();
        DigitInput digit2 = getDigitInput();

        NumberLineInput numberLineInput = NumberLineInput.builder()
                .digitInputs(Arrays.asList(digit1, digit2, getDigitInput(), getDigitInput()))
                .build();

        assertFalse(numberLineParserRules.valid(numberLineInput));
    }


    @Test
    public void noDigits() {
        NumberLineParserRules numberLineParserRules = new NumberLineParserRules(3, false);
        NumberLineInput numberLineInput = NumberLineInput.builder()
                .digitInputs(Collections.emptyList())
                .build();
        assertFalse(numberLineParserRules.valid(numberLineInput));
    }


    private DigitInput getDigitInput() {
        return DigitInput.builder().build();
    }

}