package com.scb.blade.matcher;

import com.scb.blade.presentation.DigitalNumberPresentation;
import com.scb.blade.reader.buffer.model.DigitInput;

import java.util.Optional;

public class NumberMatcher {

    private DigitalNumberPresentation digitalNumberPresentation;

    public NumberMatcher(DigitalNumberPresentation digitalNumberPresentation) {
        this.digitalNumberPresentation = digitalNumberPresentation;
    }

    public Optional<Integer> getNumber(DigitInput digitInput) {
        Integer integer = this.digitalNumberPresentation.getPresentation().get(digitInput);
        return Optional.ofNullable(integer);
    }
}
