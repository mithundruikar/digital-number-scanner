package com.scb.blade.reader.file.config;

import com.scb.blade.reader.file.NumberTextAssembler;
import com.scb.blade.reader.file.rules.NumberTextAssemblerRules;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NumberTextReaderConfig {
    @Value("${digital.number.scanner.input.digits.perline:9}")
    private int digitsPerLine;

    @Value("${digital.number.scanner.input.digits.cellwidth:3}")
    private int digitsCellWidth;

    @Bean
    public NumberTextAssemblerRules numberTextAssemblerRules() {
        return new NumberTextAssemblerRules(digitsCellWidth, digitsPerLine, false);
    }

    @Bean
    public NumberTextAssembler numberTextAssembler(NumberTextAssemblerRules numberTextAssemblerRules) {
        return new NumberTextAssembler(numberTextAssemblerRules);
    }
}
