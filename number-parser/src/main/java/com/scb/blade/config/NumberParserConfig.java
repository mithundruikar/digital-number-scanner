package com.scb.blade.config;

import com.scb.blade.matcher.NumberMatcher;
import com.scb.blade.matcher.config.NumberMatcherConfig;
import com.scb.blade.number.sink.NumberOutputSink;
import com.scb.blade.number.sink.SinkFormatter;
import com.scb.blade.parser.NumberInputParser;
import com.scb.blade.parser.NumberParserTopology;
import com.scb.blade.parser.rules.NumberLineParserRules;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(NumberMatcherConfig.class)
public class NumberParserConfig {

    @Value("${digital.number.scanner.input.digits.perline:9}")
    private int digitsPerLine;

    @Bean
    public NumberLineParserRules numberLineParserRules() {
        return new NumberLineParserRules(digitsPerLine, false);
    }

    @Bean
    public NumberInputParser numberInputParser(NumberMatcher numberMatcher, NumberLineParserRules numberLineParserRules) {
        return new NumberInputParser(numberLineParserRules, numberMatcher);
    }

    @Bean
    public SinkFormatter sinkFormatter() {
        return new SinkFormatter();
    }


    @Bean
    public NumberParserTopology numberParserTopology(NumberInputParser numberInputParser, NumberOutputSink numberOutputSink,
                                                     SinkFormatter sinkFormatter) {
        return new NumberParserTopology(numberInputParser, numberOutputSink, sinkFormatter);
    }
}
