package com.scb.blade.parser;

import com.scb.blade.number.sink.NumberOutputSink;
import com.scb.blade.number.sink.SinkFormatter;
import com.scb.blade.reader.buffer.model.NumberLineInput;
import rx.Observable;

/**
 * Streaming topology definition for parsing flow.
 * {@link SinkFormatter} can be outside of this topology. However for ease of use and assertion on returned result,
 * I prefered to use string output from the parser instead of internal {@link ParsingResult}
 */
public class NumberParserTopology {
    private NumberInputParser numberInputParser;
    private NumberOutputSink numberOutputSink;
    private SinkFormatter sinkFormatter;

    public NumberParserTopology(NumberInputParser numberInputParser,
                                NumberOutputSink numberOutputSink,
                                SinkFormatter sinkFormatter) {
        this.numberInputParser = numberInputParser;
        this.numberOutputSink = numberOutputSink;
        this.sinkFormatter = sinkFormatter;
    }

    public Observable<String> start(Observable<NumberLineInput> lineInputFlowable) {
        return lineInputFlowable.map(numberInputParser::parse)
                .map(sinkFormatter::format)
                .doOnNext(numberOutputSink::consume);
    }
}
