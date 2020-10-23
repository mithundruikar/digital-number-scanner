package com.scb.blade.reader.file;

import com.google.common.annotations.VisibleForTesting;
import com.scb.blade.reader.buffer.model.DigitSegmentInput;
import com.scb.blade.reader.buffer.model.NumberLineInput;
import com.scb.blade.reader.file.rules.NumberTextAssemblerRules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StatefulCyclicTextAssembler {
    private NumberLineInput.NumberLineInputBuilder numberState;
    private NumberTextAssemblerRules numberTextAssemblerRules;
    private boolean empty;

    public StatefulCyclicTextAssembler(NumberTextAssemblerRules numberTextAssemblerRules) {
        this.numberTextAssemblerRules = numberTextAssemblerRules;
    }

    public void init() {
        this.numberState = NumberLineInput.builder(this.numberTextAssemblerRules.getMaxDigits());
        this.empty = true;
    }

    public Optional<NumberLineInput> consume(String line) {
        if(numberTextAssemblerRules.endOfMessage(line) && !this.empty) {
            Optional<NumberLineInput> state = Optional.of(numberState.build());
            init();
            return state;
        }

        List<DigitSegmentInput> segmentInputs = getDigitSegmentInput(line);

        IntStream.range(0, segmentInputs.size())
                .boxed()
                .forEach( index -> {
                    this.empty = false;
                    numberState.addDigitSegment(index, segmentInputs.get(index));
                });

        return Optional.empty();
    }

    private List<DigitSegmentInput> getDigitSegmentInput(String line) {
        Map<Integer, DigitSegmentInput.DigitSegmentInputBuilder> segments = new HashMap<>(numberTextAssemblerRules.getMaxDigits());
        int cellWidth = numberTextAssemblerRules.getCellWidth();

        IntStream.range(0, line.length())
                .boxed()
                .forEach(charPosition -> {
                    char c = line.charAt(charPosition);
                    segments.compute(charPosition / cellWidth, (k,v) -> {
                        if(v == null) {
                            v = DigitSegmentInput.builder(cellWidth);
                        }
                        v.addChar(charPosition % cellWidth, c);
                        return v;
                    });
                });
        return segments.values().stream().map(DigitSegmentInput.DigitSegmentInputBuilder::build).collect(Collectors.toList());
    }

    @VisibleForTesting
    protected NumberLineInput.NumberLineInputBuilder getNumberState() {
        return numberState;
    }
}
