package com.scb.blade.number.scanner.application;

import com.scb.blade.parser.NumberParserTopology;
import com.scb.blade.reader.buffer.model.NumberLineInput;
import com.scb.blade.reader.file.NumberTextAssemblerTopology;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.ReplaySubject;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainScannerTest {

    @Mock
    private NumberTextAssemblerTopology numberTextAssemblerTopology;
    @Mock
    private NumberParserTopology numberParserTopology;

    private MainScanner mainScanner;

    @Before
    public void setup() {
        mainScanner = new MainScanner(numberTextAssemblerTopology, numberParserTopology);
    }

    @Test
    public void start() {
        ReplaySubject<NumberLineInput> lineInputTopic = ReplaySubject.create();
        ReplaySubject<String> outputLineTopic = ReplaySubject.create();

        Observable<NumberLineInput> testLineInputObservable = lineInputTopic.asObservable();
        Observable<String> stringObservable = outputLineTopic.asObservable();

        when(numberTextAssemblerTopology.observe(any())).thenReturn(testLineInputObservable);
        when(numberParserTopology.start(eq(testLineInputObservable))).thenReturn(stringObservable);

        lineInputTopic.onNext(NumberLineInput.builder(2).build());
        outputLineTopic.onNext("TEST1");
        outputLineTopic.onNext("TEST2");
        outputLineTopic.onCompleted();

        List<String> dummy = mainScanner.start("dummy");

        assertEquals(Arrays.asList("TEST1", "TEST2"), dummy);
    }


    @Test
    public void startWithError() {
        ReplaySubject<NumberLineInput> lineInputTopic = ReplaySubject.create();
        ReplaySubject<String> outputLineTopic = ReplaySubject.create();

        Observable<NumberLineInput> testLineInputObservable = lineInputTopic.asObservable();
        Observable<String> stringObservable = outputLineTopic.asObservable();

        when(numberTextAssemblerTopology.observe(any())).thenReturn(testLineInputObservable);
        when(numberParserTopology.start(eq(testLineInputObservable))).thenReturn(stringObservable);

        lineInputTopic.onNext(NumberLineInput.builder(2).build());
        outputLineTopic.onNext("TEST1");
        outputLineTopic.onNext("TEST2");
        outputLineTopic.onError(new IllegalStateException("TEST ERROR"));

        List<String> dummy = mainScanner.start("dummy");

        assertTrue(dummy.isEmpty());
    }

}