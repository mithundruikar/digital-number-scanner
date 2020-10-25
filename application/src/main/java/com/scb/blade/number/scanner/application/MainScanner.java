package com.scb.blade.number.scanner.application;

import com.scb.blade.parser.NumberParserTopology;
import com.scb.blade.reader.buffer.model.NumberLineInput;
import com.scb.blade.reader.file.NumberTextAssemblerTopology;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import java.util.Collections;
import java.util.List;

/**
 * Main scanner to scan the input file and produce output in list of string.
 * It also additionally emits output to configured Sink
 */
@Slf4j
public class MainScanner {
    private NumberTextAssemblerTopology numberTextAssemblerTopology;
    private NumberParserTopology numberParserTopology;

    public MainScanner(NumberTextAssemblerTopology numberTextAssemblerTopology, NumberParserTopology numberParserTopology) {
        this.numberTextAssemblerTopology = numberTextAssemblerTopology;
        this.numberParserTopology = numberParserTopology;
    }

    public List<String> start(String filePath) {
        Observable<NumberLineInput> start = this.numberTextAssemblerTopology.observe(filePath);
        try {
            return this.numberParserTopology.start(start)
                    .doOnError(t -> {
                        throw new IllegalStateException(t);
                    })
                    .toList()
                    .toBlocking()
                    .single();
        } catch(RuntimeException re) {
            log.error("Error while processing file {}", filePath, re);
        }
        return Collections.emptyList();
    }
}
