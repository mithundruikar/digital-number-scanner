package com.scb.blade.reader.buffer.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DigitSegmentInput {
    private char[] chars;
}
