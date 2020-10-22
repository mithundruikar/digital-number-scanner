package com.scb.blade.reader.buffer.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@Builder
@EqualsAndHashCode
public class NumberLineInput {
    private List<DigitInput> digitInputs;
}
