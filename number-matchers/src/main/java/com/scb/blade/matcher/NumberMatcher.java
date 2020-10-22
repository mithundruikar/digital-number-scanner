package com.scb.blade.matcher;

import com.scb.blade.presentation.DigitalNumberPresentation;
import com.scb.blade.presentation.NumberPresentationRepository;
import com.scb.blade.reader.buffer.model.DigitInput;

import java.util.Optional;

public class NumberMatcher {

    private NumberPresentationRepository presentationRepository;
    private int cellWidth;

    public NumberMatcher(int cellWidth, NumberPresentationRepository presentationRepository) {
        this.cellWidth = cellWidth;
        this.presentationRepository = presentationRepository;
    }

    public Optional<Integer> getNumber(DigitInput digitInput) {
        DigitalNumberPresentation digitalNumberPresentation = presentationRepository.digitalNumberPresentation(cellWidth);
        Integer integer = digitalNumberPresentation.getPresentation().get(digitInput);
        return Optional.ofNullable(integer);
    }
}
