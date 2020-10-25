package com.scb.blade.reader.file.rules;

import lombok.Value;

import java.util.Objects;

@Value
public class NumberTextAssemblerRules {
    private final int cellWidth;
    private final int maxDigits;
    private final int maxLines;
    private final int maxEmptyLines;
    private final boolean variableLength;


    public boolean endOfMessage(String messageText) {
        return Objects.isNull(messageText) || messageText.trim().length() == 0;
    }
}
