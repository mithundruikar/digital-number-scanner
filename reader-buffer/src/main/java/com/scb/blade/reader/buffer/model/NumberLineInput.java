package com.scb.blade.reader.buffer.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value
@EqualsAndHashCode
public final class NumberLineInput {
    private final List<DigitInput> digitInputs;
    private final boolean numberLineTrimmed;

    private NumberLineInput(List<DigitInput> digitInputs) {
        this(digitInputs, false);
    }

    private NumberLineInput(List<DigitInput> digitInputs, boolean numberLineTrimmed) {
        this.digitInputs = digitInputs;
        this.numberLineTrimmed = numberLineTrimmed;
    }

    public static NumberLineInputBuilder builder(int capacity) {
        return new NumberLineInputBuilder(capacity);
    }

    public static class NumberLineInputBuilder {
        private List<DigitInput> digitInputs;
        private DigitInput.DigitInputBuilder[] digitInputsBuilders;
        private boolean numberLineTrimmed;
        private int capacity;

        NumberLineInputBuilder(int capacity) {
            this.capacity = capacity;
            digitInputsBuilders = new DigitInput.DigitInputBuilder[capacity];
        }

        public NumberLineInputBuilder digitInputs(List<DigitInput> digitInputs) {
            if(digitInputs.size() > capacity) {
                this.digitInputs = IntStream.range(0, capacity).boxed().map(digitInputs::get).collect(Collectors.toList());
                this.numberLineTrimmed = true;
            } else {
                this.digitInputs = digitInputs;
            }
            return this;
        }

        public NumberLineInputBuilder addDigitSegment(int position, DigitSegmentInput  segmentInput) {
            if(position > digitInputsBuilders.length - 1) {
                numberLineTrimmed = true;
                return this;
            }
            DigitInput.DigitInputBuilder digitInputsBuilder = this.digitInputsBuilders[position];
            if(digitInputsBuilder == null) {
                this.digitInputsBuilders[position] = DigitInput.builder();
            }
            this.digitInputsBuilders[position].addSegment(segmentInput);
            return this;
        }

        public NumberLineInput build() {
            if(Objects.nonNull(digitInputs)) {
                return new NumberLineInput(digitInputs, numberLineTrimmed);
            }

            return new NumberLineInput(Arrays.stream(this.digitInputsBuilders)
                    .filter(Objects::nonNull)
                    .map(DigitInput.DigitInputBuilder::build)
                    .collect(Collectors.toList()), numberLineTrimmed);
        }

        public String toString() {
            if(Objects.nonNull(digitInputs)) {
                return "NumberLineInput.NumberLineInputBuilder(digitInputs=" + this.digitInputs + ")";
            }
            return "NumberLineInput.NumberLineInputBuilder(digitInputsBuilders=" + this.digitInputsBuilders + ")";
        }
    }
}
