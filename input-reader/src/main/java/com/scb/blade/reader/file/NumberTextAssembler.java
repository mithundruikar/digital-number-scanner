package com.scb.blade.reader.file;

import com.scb.blade.reader.buffer.model.NumberLineInput;
import com.scb.blade.reader.file.rules.NumberTextAssemblerRules;
import com.scb.blade.reader.file.scanner.NumberTextScanner;
import rx.Observable;
import rx.subjects.ReplaySubject;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class NumberTextAssembler {
    private StatefulCyclicTextAssembler statefulCyclicTextAssembler;
    private ScheduledExecutorService scheduledExecutorService;

    public NumberTextAssembler(NumberTextAssemblerRules numberTextAssemblerRules) {
        this.statefulCyclicTextAssembler = new StatefulCyclicTextAssembler(numberTextAssemblerRules);
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

    public Observable<NumberLineInput> start(String filePath) {
        this.statefulCyclicTextAssembler.init();
        ReplaySubject<NumberLineInput> lineInputTopic = ReplaySubject.create();
        Observable<NumberLineInput> numberLineInputFlowable = lineInputTopic.asObservable().serialize();

        return numberLineInputFlowable.doOnSubscribe(() ->
            startAsyncReader(filePath, lineInputTopic)
        );
    }

    private void startAsyncReader(String filePath, ReplaySubject<NumberLineInput> lineInputTopic) {
        NumberTextScanner numberTextScanner = new NumberTextScanner(filePath);
        try {
            Observable<String> textStringInputStream = numberTextScanner.observe().serialize();
            textStringInputStream
                    .doOnNext(nextLine -> this.consumeLine(nextLine, lineInputTopic))
                    .doOnError(t -> lineInputTopic.onError(t))
                    .doOnCompleted(() -> {
                        consumeLine("", lineInputTopic);
                        lineInputTopic.onCompleted();
                    })
                    .subscribe();
        } catch(IOException io){
            io.printStackTrace();
        }
    }


    protected void consumeLine(String nextLine, ReplaySubject<NumberLineInput> lineInputTopic) {
        Optional<NumberLineInput> consume = this.statefulCyclicTextAssembler.consume(nextLine);
        if(consume.isPresent()) {
            lineInputTopic.onNext(consume.get());
        }
    }
}
