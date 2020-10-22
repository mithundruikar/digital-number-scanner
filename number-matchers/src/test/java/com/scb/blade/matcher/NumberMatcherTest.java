package com.scb.blade.matcher;

import com.scb.blade.presentation.NumberPresentationRepository;
import com.scb.blade.presentation.ThreeCellNumberPresentation;
import com.scb.blade.reader.buffer.model.DigitInput;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static com.scb.blade.presentation.ThreeCellNumberPresentation.*;

public class NumberMatcherTest {
    private NumberMatcher numberMatcher;

    @Before
    public void setup() {
        this.numberMatcher = new NumberMatcher(3, new NumberPresentationRepository(Collections.singletonList(new ThreeCellNumberPresentation())));
    }


    @Test
    public void matchingNumber() {
        DigitInput validInput = DigitInput.builder().segments(Arrays.asList(LINE, RIGHT_ANGLE, LEFT_ANGLE)).build();
        Optional<Integer> numberMatch = this.numberMatcher.getNumber(validInput);

        assertTrue(numberMatch.isPresent());
        assertEquals(2, numberMatch.get().intValue());
    }

    @Test
    public void invalidNumber() {
        DigitInput inValidInput = DigitInput.builder().segments(Arrays.asList(LINE, LINE, LINE)).build();
        Optional<Integer> numberMatch = this.numberMatcher.getNumber(inValidInput);

        assertFalse(numberMatch.isPresent());
    }
}