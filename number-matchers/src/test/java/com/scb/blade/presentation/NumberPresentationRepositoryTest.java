package com.scb.blade.presentation;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class NumberPresentationRepositoryTest {

    @Test
    public void getRepresentationByCellWidth() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        NumberPresentationRepository numberPresentationRepository = new NumberPresentationRepository(Collections.singletonList(threeCellNumberPresentation));
        DigitalNumberPresentation digitalNumberPresentation = numberPresentationRepository.digitalNumberPresentation(3);
        assertSame(threeCellNumberPresentation, digitalNumberPresentation);
    }

    @Test
    public void getRepresentationOfNotSupportedCellWidth() {
        ThreeCellNumberPresentation threeCellNumberPresentation = new ThreeCellNumberPresentation();
        NumberPresentationRepository numberPresentationRepository = new NumberPresentationRepository(Collections.singletonList(threeCellNumberPresentation));
        DigitalNumberPresentation digitalNumberPresentation = numberPresentationRepository.digitalNumberPresentation(4);
        assertNull(digitalNumberPresentation);
    }
}