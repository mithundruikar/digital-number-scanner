package com.scb.blade.number.sink;

public class ConsoleOutputSink implements NumberOutputSink{
    @Override
    public void consume(String numberString) {
        System.out.println(numberString);
    }
}
