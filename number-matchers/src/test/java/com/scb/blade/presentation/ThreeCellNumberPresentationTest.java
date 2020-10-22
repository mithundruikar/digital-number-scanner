package com.scb.blade.presentation;

import com.scb.blade.reader.buffer.model.DigitInput;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;
import static com.scb.blade.presentation.ThreeCellNumberPresentation.*;

public class ThreeCellNumberPresentationTest {

    @Test
    public void getPresentationMapSize() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        Map<DigitInput, Integer> presentation = threeCellNumberPresentation.getPresentation();
        assertEquals(10, presentation.size());
    }

    @Test
    public void numberOnePresentation() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        Map<DigitInput, Integer> presentation = threeCellNumberPresentation.getPresentation();
        DigitInput expectedDigitalInput = DigitInput.builder().segments(Arrays.asList(EMPTY_LINE, RIGHT_LINE, RIGHT_LINE)).build();
        assertEquals(1, presentation.get(expectedDigitalInput).intValue());
    }

    @Test
    public void numberTwoPresentation() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        Map<DigitInput, Integer> presentation = threeCellNumberPresentation.getPresentation();
        DigitInput expectedDigitalInput = DigitInput.builder().segments(Arrays.asList(LINE, RIGHT_ANGLE, LEFT_ANGLE)).build();
        assertEquals(2, presentation.get(expectedDigitalInput).intValue());
    }

    @Test
    public void numberThreePresentation() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        Map<DigitInput, Integer> presentation = threeCellNumberPresentation.getPresentation();
        DigitInput expectedDigitalInput = DigitInput.builder().segments(Arrays.asList(LINE, RIGHT_ANGLE, RIGHT_ANGLE)).build();
        assertEquals(3, presentation.get(expectedDigitalInput).intValue());
    }


    @Test
    public void numberFourPresentation() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        Map<DigitInput, Integer> presentation = threeCellNumberPresentation.getPresentation();
        DigitInput expectedDigitalInput = DigitInput.builder().segments(Arrays.asList(EMPTY_LINE, CUP, RIGHT_LINE)).build();
        assertEquals(4, presentation.get(expectedDigitalInput).intValue());
    }


    @Test
    public void numberFivePresentation() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        Map<DigitInput, Integer> presentation = threeCellNumberPresentation.getPresentation();
        DigitInput expectedDigitalInput = DigitInput.builder().segments(Arrays.asList(LINE, LEFT_ANGLE, RIGHT_ANGLE)).build();
        assertEquals(5, presentation.get(expectedDigitalInput).intValue());
    }


    @Test
    public void numberSixPresentation() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        Map<DigitInput, Integer> presentation = threeCellNumberPresentation.getPresentation();
        DigitInput expectedDigitalInput = DigitInput.builder().segments(Arrays.asList(LINE, LEFT_ANGLE, CUP)).build();
        assertEquals(6, presentation.get(expectedDigitalInput).intValue());
    }


    @Test
    public void numberSevenPresentation() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        Map<DigitInput, Integer> presentation = threeCellNumberPresentation.getPresentation();
        DigitInput expectedDigitalInput = DigitInput.builder().segments(Arrays.asList(LINE, RIGHT_LINE, RIGHT_LINE)).build();
        assertEquals(7, presentation.get(expectedDigitalInput).intValue());
    }


    @Test
    public void numberEightPresentation() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        Map<DigitInput, Integer> presentation = threeCellNumberPresentation.getPresentation();
        DigitInput expectedDigitalInput = DigitInput.builder().segments(Arrays.asList(LINE, CUP, CUP)).build();
        assertEquals(8, presentation.get(expectedDigitalInput).intValue());
    }


    @Test
    public void numberNinePresentation() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        Map<DigitInput, Integer> presentation = threeCellNumberPresentation.getPresentation();
        DigitInput expectedDigitalInput = DigitInput.builder().segments(Arrays.asList(LINE, CUP, RIGHT_ANGLE)).build();
        assertEquals(9, presentation.get(expectedDigitalInput).intValue());
    }

    @Test
    public void numberZeroPresentation() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        Map<DigitInput, Integer> presentation = threeCellNumberPresentation.getPresentation();
        DigitInput expectedDigitalInput = DigitInput.builder().segments(Arrays.asList(LINE, SIDE_LINE, LINE)).build();
        assertEquals(0, presentation.get(expectedDigitalInput).intValue());
    }

}