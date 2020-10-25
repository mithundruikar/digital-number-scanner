package com.scb.blade.parser.config;

import com.scb.blade.config.NumberParserConfig;
import com.scb.blade.number.sink.NumberOutputSink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NumberParserTestConfig extends NumberParserConfig {

    @Bean
    public NumberOutputSink consoleOutputSink() {
        return line -> System.out.println(line);
    }
}
