package com.scb.blade.number.scanner.application;

import com.scb.blade.parser.NumberParserTopology;
import com.scb.blade.reader.buffer.model.NumberLineInput;
import com.scb.blade.reader.file.NumberTextAssembler;
import rx.Observable;


public class MainScanner {
    private NumberTextAssembler numberTextAssembler;
    private NumberParserTopology numberParserTopology;

    public MainScanner(NumberTextAssembler numberTextAssembler, NumberParserTopology numberParserTopology) {
        this.numberTextAssembler = numberTextAssembler;
        this.numberParserTopology = numberParserTopology;
    }

    public void start(String filePath) {
        Observable<NumberLineInput> start = this.numberTextAssembler.start(filePath);
        this.numberParserTopology.start(start)
                .toList()
                .toBlocking()
                .single();

    }
}
