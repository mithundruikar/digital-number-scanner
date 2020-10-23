package com.scb.blade.reader.buffer.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public final class DigitSegmentInput {
    public static final char WHITESPACE = ' ';
    public static final char UNDERSCORE = '_';
    public static final char PIPE = '|';

    private final char[] chars;

    DigitSegmentInput(char[] chars) {
        this.chars = chars;
    }

    public static DigitSegmentInputBuilder builder(int capacity) {
        return new DigitSegmentInputBuilder(capacity);
    }

    public static class DigitSegmentInputBuilder {
        private char[] chars;

        DigitSegmentInputBuilder(int capacity) {
            chars = new char[capacity];
        }

        public DigitSegmentInputBuilder chars(char[] chars) {
            this.chars = chars;
            return this;
        }

        public DigitSegmentInputBuilder addChar(int position, char lineChar) {
            chars[position] = lineChar;
            return this;
        }

        public DigitSegmentInput build() {
            return new DigitSegmentInput(chars);
        }

        public String toString() {
            return "DigitSegmentInput.DigitSegmentInputBuilder(chars=" + java.util.Arrays.toString(this.chars) + ")";
        }
    }
}
