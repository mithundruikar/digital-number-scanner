package com.scb.blade.number.scanner.sink;

import com.scb.blade.number.sink.NumberOutputSink;

public class ConsoleOutputSink implements NumberOutputSink{
    @Override
    public void consume(String numberString) {
        System.out.println(numberString);
    }
}
