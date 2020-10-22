package com.scb.blade.presentation;

import com.scb.blade.reader.buffer.model.DigitInput;
import com.scb.blade.reader.buffer.model.DigitSegmentInput;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static com.scb.blade.reader.buffer.model.DigitSegmentInput.*;

public class ThreeCellNumberPresentation implements DigitalNumberPresentation {
    public static final DigitSegmentInput EMPTY_LINE = DigitSegmentInput.builder().chars(new char[] {WHITESPACE, WHITESPACE, WHITESPACE}).build();
    public static final DigitSegmentInput LINE = DigitSegmentInput.builder().chars(new char[] {WHITESPACE, UNDERSCORE, WHITESPACE}).build();
    public static final DigitSegmentInput RIGHT_LINE = DigitSegmentInput.builder().chars(new char[] {WHITESPACE, WHITESPACE, PIPE}).build();
    public static final DigitSegmentInput SIDE_LINE = DigitSegmentInput.builder().chars(new char[] {PIPE, WHITESPACE, PIPE}).build();
    public static final DigitSegmentInput CUP = DigitSegmentInput.builder().chars(new char[] {PIPE, UNDERSCORE, PIPE}).build();
    public static final DigitSegmentInput LEFT_ANGLE = DigitSegmentInput.builder().chars(new char[] {PIPE, UNDERSCORE, WHITESPACE}).build();
    public static final DigitSegmentInput RIGHT_ANGLE = DigitSegmentInput.builder().chars(new char[] {WHITESPACE, UNDERSCORE, PIPE}).build();

    private final Map<DigitInput, Integer> presentation;

    public ThreeCellNumberPresentation() {
        HashMap<DigitInput, Integer> integerDigitInputHashMap = new HashMap();

        integerDigitInputHashMap.put(DigitInput.builder().segments(Arrays.asList(LINE, SIDE_LINE, LINE)).build(), 0);
        integerDigitInputHashMap.put(DigitInput.builder().segments(Arrays.asList(EMPTY_LINE, RIGHT_LINE, RIGHT_LINE)).build(), 1);
        integerDigitInputHashMap.put(DigitInput.builder().segments(Arrays.asList(LINE, RIGHT_ANGLE, LEFT_ANGLE)).build(), 2);
        integerDigitInputHashMap.put(DigitInput.builder().segments(Arrays.asList(LINE, RIGHT_ANGLE, RIGHT_ANGLE)).build(), 3);
        integerDigitInputHashMap.put(DigitInput.builder().segments(Arrays.asList(EMPTY_LINE, CUP, RIGHT_LINE)).build(), 4);
        integerDigitInputHashMap.put(DigitInput.builder().segments(Arrays.asList(LINE, LEFT_ANGLE, RIGHT_ANGLE)).build(), 5);
        integerDigitInputHashMap.put(DigitInput.builder().segments(Arrays.asList(LINE, LEFT_ANGLE, CUP)).build(), 6);
        integerDigitInputHashMap.put(DigitInput.builder().segments(Arrays.asList(LINE, RIGHT_LINE, RIGHT_LINE)).build(), 7);
        integerDigitInputHashMap.put(DigitInput.builder().segments(Arrays.asList(LINE, CUP, CUP)).build(), 8);
        integerDigitInputHashMap.put(DigitInput.builder().segments(Arrays.asList(LINE, CUP, RIGHT_ANGLE)).build(), 9);

        presentation = Collections.unmodifiableMap(integerDigitInputHashMap);
    }

    public Map<DigitInput, Integer> getPresentation() {
        return presentation;
    }

    @Override
    public int getCellWidth() {
        return 3;
    }
}
