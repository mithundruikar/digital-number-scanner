package com.scb.blade.presentation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NumberPresentationRepository {
    Map<Integer, DigitalNumberPresentation> digitalNumberPresentationMap;

    public NumberPresentationRepository(List<DigitalNumberPresentation> digitalNumberPresentations) {
        digitalNumberPresentationMap = digitalNumberPresentations.stream()
                .collect(Collectors.toMap(DigitalNumberPresentation::getCellWidth, Function.identity()));
    }

    public DigitalNumberPresentation digitalNumberPresentation(int cellWidth) {
        return digitalNumberPresentationMap.get(cellWidth);
    }
}
