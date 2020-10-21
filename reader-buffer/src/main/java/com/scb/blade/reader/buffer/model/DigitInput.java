package com.scb.blade.reader.buffer.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class DigitInput {
    private List<DigitSegmentInput> segments;
}
