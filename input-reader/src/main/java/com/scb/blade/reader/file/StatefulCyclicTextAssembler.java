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

/**
 * Stateful assembler of input digit texts. It maintains a buffer of number line input assembled so far.
 * it optionally  returns completed number line input upon end of message reception.
 */
public class StatefulCyclicTextAssembler {
    private NumberLineInput.NumberLineInputBuilder numberState;
    private NumberTextAssemblerRules numberTextAssemblerRules;
    private boolean empty;
    private int bufferedLineCounter;
    private int skippedEmptyLinesInaRow;

    public StatefulCyclicTextAssembler(NumberTextAssemblerRules numberTextAssemblerRules) {
        this.numberTextAssemblerRules = numberTextAssemblerRules;
        this.bufferedLineCounter = 0;
        this.skippedEmptyLinesInaRow = 0;
    }

    public void init() {
        this.numberState = NumberLineInput.builder(this.numberTextAssemblerRules.getMaxDigits());
        this.empty = true;
    }

    public Optional<NumberLineInput> consume(String line) {
        boolean endMessage = numberTextAssemblerRules.endOfMessage(line);
        if(endMessage && this.empty) {
            if(this.skippedEmptyLinesInaRow++ >= numberTextAssemblerRules.getMaxEmptyLines()) {
                throw new IllegalStateException("More than allowed empty lines observed. Ignoring further");
            }
        }
        else if(endMessage) {
            Optional<NumberLineInput> state = Optional.of(numberState.build());
            init();
            this.bufferedLineCounter++;
            return state;
        }

        if(!endMessage) {
            if(this.bufferedLineCounter >= numberTextAssemblerRules.getMaxLines()) {
                throw new IllegalStateException("More than allowed lines reached. Ignoring further");
            }

            List<DigitSegmentInput> segmentInputs = getDigitSegmentInput(line);

            IntStream.range(0, segmentInputs.size())
                    .boxed()
                    .forEach(index -> {
                        this.empty = false;
                        numberState.addDigitSegment(index, segmentInputs.get(index));
                    });
        }
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
