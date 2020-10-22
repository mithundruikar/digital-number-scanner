package com.scb.blade.parser.rules;

import lombok.Value;

@Value
public class NumberLineParserRules {
    int maxDigits;
    boolean variableLength;
}
