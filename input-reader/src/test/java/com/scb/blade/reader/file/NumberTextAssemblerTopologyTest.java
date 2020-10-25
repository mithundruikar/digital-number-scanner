package com.scb.blade.reader.file;

import com.scb.blade.reader.buffer.model.NumberLineInput;
import com.scb.blade.reader.file.rules.NumberTextAssemblerRules;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

public class NumberTextAssemblerTopologyTest {

    private NumberTextAssemblerRules numberTextAssemblerRules;
    private NumberTextAssemblerTopology numberTextAssemblerTopology;

    @Before
    public void setup() {
        this.numberTextAssemblerRules = new NumberTextAssemblerRules(3, 2, 4, 4,false);
        this.numberTextAssemblerTopology = new NumberTextAssemblerTopology(numberTextAssemblerRules);
    }

    @Test
    public void assembleTestChunks() {
        Observable<NumberLineInput> testChunks = this.numberTextAssemblerTopology.observe("testChunks");

        Observable<List<NumberLineInput>> listObservable = testChunks.toList();
        List<NumberLineInput> numberLineInputs = listObservable.toBlocking().single();

        assertEquals(2, numberLineInputs.size());
    }

    @Test
    public void assembleTestChunksWithIncomplete() {
        Observable<NumberLineInput> testChunks = this.numberTextAssemblerTopology.observe("testChunksIncomplete");

        Observable<List<NumberLineInput>> listObservable = testChunks.toList();
        List<NumberLineInput> numberLineInputs = listObservable.toBlocking().single();

        assertEquals(2, numberLineInputs.size());
    }


    @Test
    public void assembleTestChunksWithDoubleSeparatorLine() {
        Observable<NumberLineInput> testChunks = this.numberTextAssemblerTopology.observe("testChunksWithExtraSeparatorLines");

        Observable<List<NumberLineInput>> listObservable = testChunks.toList();
        List<NumberLineInput> numberLineInputs = listObservable.toBlocking().single();

        assertEquals(2, numberLineInputs.size());
    }

    @Test
    public void assembleTestChunksWithMoreThanAllowedLength() {
        Observable<NumberLineInput> testChunks = this.numberTextAssemblerTopology.observe("testChunksWithVariableLength");

        Observable<List<NumberLineInput>> listObservable = testChunks.toList();
        List<NumberLineInput> numberLineInputs = listObservable.toBlocking().single();

        assertEquals(2, numberLineInputs.size());
        assertTrue(numberLineInputs.get(0).isNumberLineTrimmed());
        assertFalse(numberLineInputs.get(1).isNumberLineTrimmed());
    }

    @Test
    public void getExceptionOnInvalidFile() {
        Observable<NumberLineInput> testChunks = this.numberTextAssemblerTopology.observe("invalidFile");
        AtomicBoolean gotError = new AtomicBoolean(false);
        testChunks
                .doOnCompleted(() -> gotError.set(false))
                .doOnError( t-> gotError.set(true))
                .onErrorReturn( t-> {
                    return NumberLineInput.builder(0).build();
                })
                .subscribe();

        assertTrue(gotError.get());
    }

    @Test
    public void assembleEmptyFile() {
        AtomicBoolean gotError = new AtomicBoolean(false);
        Observable<NumberLineInput> testChunks = this.numberTextAssemblerTopology.observe("emptyChunks");
        testChunks
                .doOnCompleted(() -> gotError.set(false))
                .subscribe();
        assertFalse(gotError.get());
    }
}
