package com.scb.blade.reader.buffer.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@EqualsAndHashCode
public final class DigitInput {
    private final List<DigitSegmentInput> segments;

    DigitInput(List<DigitSegmentInput> segments) {
        this.segments = segments;
    }

    public static DigitInputBuilder builder() {
        return new DigitInputBuilder();
    }

    public static class DigitInputBuilder {
        private List<DigitSegmentInput> segments;

        DigitInputBuilder() {
            this.segments = new ArrayList<>();
        }

        public DigitInputBuilder segments(List<DigitSegmentInput> segments) {
            this.segments = segments;
            return this;
        }

        public DigitInputBuilder addSegment(DigitSegmentInput segmentInput) {
            this.segments.add(segmentInput);
            return this;
        }

        public DigitInput build() {
            return new DigitInput(segments);
        }

        public String toString() {
            return "DigitInput.DigitInputBuilder(segments=" + this.segments + ")";
        }
    }
}
