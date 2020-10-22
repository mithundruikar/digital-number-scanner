package com.scb.blade.reader.buffer.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode
public class DigitSegmentInput {
    public static final char WHITESPACE = ' ';
    public static final char UNDERSCORE = '_';
    public static final char PIPE = '|';

    private char[] chars;
}
