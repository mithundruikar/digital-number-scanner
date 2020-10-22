package com.scb.blade.parser;

import com.scb.blade.number.sink.NumberOutputSink;
import com.scb.blade.number.sink.SinkFormatter;
import com.scb.blade.reader.buffer.model.NumberLineInput;
import io.reactivex.Flowable;


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

    public Flowable<String> start(Flowable<NumberLineInput> lineInputFlowable) {
        return lineInputFlowable.map(numberInputParser::parse)
                .map(sinkFormatter::format)
                .doOnNext(numberOutputSink::consume);
    }
}
