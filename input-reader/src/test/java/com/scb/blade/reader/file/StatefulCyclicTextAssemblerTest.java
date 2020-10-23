package com.scb.blade.reader.file;

import com.scb.blade.reader.buffer.model.DigitInput;
import com.scb.blade.reader.buffer.model.DigitSegmentInput;
import com.scb.blade.reader.buffer.model.NumberLineInput;
import com.scb.blade.reader.file.rules.NumberTextAssemblerRules;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class StatefulCyclicTextAssemblerTest {
    private NumberTextAssemblerRules numberTextAssemblerRules;
    private StatefulCyclicTextAssembler statefulCyclicTextAssembler;

    @Before
    public void setup() {
        this.numberTextAssemblerRules = new NumberTextAssemblerRules(3, 2, false);
        this.statefulCyclicTextAssembler = new StatefulCyclicTextAssembler(numberTextAssemblerRules);
    }

    @Test
    public void init() {
        this.statefulCyclicTextAssembler.init();
        assertNotNull(this.statefulCyclicTextAssembler.getNumberState());
    }

    @Test
    public void consume() {
        String oneLine1 = "   ";
        String oneLine2 = "  |";
        String oneLine3 = "  |";

        String twoLine1 = " _ ";
        String twoLine2 = " _|";
        String twoLine3 = "|_ ";

        String endOfMessageLine = "      ";

        this.statefulCyclicTextAssembler.init();

        Optional<NumberLineInput> consume1 = this.statefulCyclicTextAssembler.consume(oneLine1 + twoLine1);
        assertFalse(consume1.isPresent());
        Optional<NumberLineInput> consume2 = this.statefulCyclicTextAssembler.consume(oneLine2 + twoLine2);
        assertFalse(consume2.isPresent());
        Optional<NumberLineInput> consume3 = this.statefulCyclicTextAssembler.consume(oneLine3 + twoLine3);
        assertFalse(consume3.isPresent());
        Optional<NumberLineInput> consume4 = this.statefulCyclicTextAssembler.consume(endOfMessageLine);
        assertTrue(consume4.isPresent());

        NumberLineInput numberLineInput = consume4.get();
        assertFalse(numberLineInput.isNumberLineTrimmed());

        assertEquals(2, numberLineInput.getDigitInputs().size());
        DigitInput digitInputOne = numberLineInput.getDigitInputs().get(0);
        DigitInput digitInputTwo = numberLineInput.getDigitInputs().get(1);

        assertEquals(from(Arrays.asList(oneLine1, oneLine2, oneLine3)), digitInputOne);
        assertEquals(from(Arrays.asList(twoLine1, twoLine2, twoLine3)), digitInputTwo);
    }


    @Test
    public void consumeWithInvalidChars() {
        String oneLine1 = "  |";
        String oneLine2 = "  |";
        String oneLine3 = "  |";

        String twoLine1 = " _ ";
        String twoLine2 = "|_|";
        String twoLine3 = "|_ ";

        String endOfMessageLine = "      ";

        this.statefulCyclicTextAssembler.init();

        Optional<NumberLineInput> consume1 = this.statefulCyclicTextAssembler.consume(oneLine1 + twoLine1);
        assertFalse(consume1.isPresent());
        Optional<NumberLineInput> consume2 = this.statefulCyclicTextAssembler.consume(oneLine2 + twoLine2);
        assertFalse(consume2.isPresent());
        Optional<NumberLineInput> consume3 = this.statefulCyclicTextAssembler.consume(oneLine3 + twoLine3);
        assertFalse(consume3.isPresent());
        Optional<NumberLineInput> consume4 = this.statefulCyclicTextAssembler.consume(endOfMessageLine);
        assertTrue(consume4.isPresent());

        NumberLineInput numberLineInput = consume4.get();
        assertFalse(numberLineInput.isNumberLineTrimmed());

        assertEquals(2, numberLineInput.getDigitInputs().size());
        DigitInput digitInputOne = numberLineInput.getDigitInputs().get(0);
        DigitInput digitInputTwo = numberLineInput.getDigitInputs().get(1);

        assertEquals(from(Arrays.asList(oneLine1, oneLine2, oneLine3)), digitInputOne);
        assertEquals(from(Arrays.asList(twoLine1, twoLine2, twoLine3)), digitInputTwo);
    }

    @Test
    public void consumeWithIMoreCharsThanMaxLength() {
        String oneLine1 = "   |";
        String oneLine2 = "   |";
        String oneLine3 = "   |";

        String twoLine1 = " _ ";
        String twoLine2 = "|_|";
        String twoLine3 = "|_ ";

        String endOfMessageLine = "      ";

        this.statefulCyclicTextAssembler.init();

        Optional<NumberLineInput> consume1 = this.statefulCyclicTextAssembler.consume(oneLine1 + twoLine1);
        assertFalse(consume1.isPresent());
        Optional<NumberLineInput> consume2 = this.statefulCyclicTextAssembler.consume(oneLine2 + twoLine2);
        assertFalse(consume2.isPresent());
        Optional<NumberLineInput> consume3 = this.statefulCyclicTextAssembler.consume(oneLine3 + twoLine3);
        assertFalse(consume3.isPresent());
        Optional<NumberLineInput> consume4 = this.statefulCyclicTextAssembler.consume(endOfMessageLine);
        assertTrue(consume4.isPresent());

        NumberLineInput numberLineInput = consume4.get();
        assertTrue(numberLineInput.isNumberLineTrimmed());

        assertEquals(2, numberLineInput.getDigitInputs().size());
        DigitInput digitInputOne = numberLineInput.getDigitInputs().get(0);
        DigitInput digitInputTwo = numberLineInput.getDigitInputs().get(1);

        assertEquals(from(Arrays.asList(oneLine1.substring(0, 3), oneLine2.substring(0, 3), oneLine3.substring(0, 3))), digitInputOne);
        assertEquals(from(Arrays.asList(oneLine1.charAt(3)+twoLine1.substring(0,2), oneLine2.charAt(3)+twoLine2.substring(0,2),
                oneLine3.charAt(3)+twoLine3.substring(0,2))), digitInputTwo);
    }


    private DigitInput from(List<String> lines) {
        DigitInput.DigitInputBuilder builder = DigitInput.builder();
        lines.forEach( line -> {
            builder.addSegment(DigitSegmentInput.builder(this.numberTextAssemblerRules.getCellWidth()).chars(line.toCharArray()).build());
        });
        return builder.build();
    }

}