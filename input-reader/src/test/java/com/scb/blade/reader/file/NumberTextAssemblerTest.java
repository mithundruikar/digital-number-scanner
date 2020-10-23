package com.scb.blade.reader.file;

import com.scb.blade.reader.buffer.model.NumberLineInput;
import com.scb.blade.reader.file.rules.NumberTextAssemblerRules;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class NumberTextAssemblerTest {

    private NumberTextAssemblerRules numberTextAssemblerRules;
    private NumberTextAssembler numberTextAssembler;

    @Before
    public void setup() {
        this.numberTextAssemblerRules = new NumberTextAssemblerRules(3, 2, false);
        this.numberTextAssembler = new NumberTextAssembler(numberTextAssemblerRules);
    }

    @Test
    public void assembleTestChunks() throws IOException {
        Observable<NumberLineInput> testChunks = this.numberTextAssembler.start("testChunks");

        Observable<List<NumberLineInput>> listObservable = testChunks.toList();
        List<NumberLineInput> numberLineInputs = listObservable.toBlocking().single();

        assertEquals(2, numberLineInputs.size());
    }
}
