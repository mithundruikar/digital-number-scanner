package com.scb.blade.presentation;

import com.scb.blade.reader.buffer.model.DigitInput;

import java.util.Map;

public interface DigitalNumberPresentation {
    Map<DigitInput, Integer> getPresentation();
}
