package com.scb.blade.reader.file.config;

import com.scb.blade.reader.file.NumberTextAssemblerTopology;
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

    @Value("${digital.number.scanner.input.max.lines:400}")
    private int maxNumberLines;

    @Value("${digital.number.scanner.input.max.emptyLinesInRow:4}")
    private int maxEmptyLinesInRow;

    @Bean
    public NumberTextAssemblerRules numberTextAssemblerRules() {
        return new NumberTextAssemblerRules(digitsCellWidth, digitsPerLine, maxNumberLines, maxEmptyLinesInRow,false);
    }

    @Bean
    public NumberTextAssemblerTopology numberTextAssembler(NumberTextAssemblerRules numberTextAssemblerRules) {
        return new NumberTextAssemblerTopology(numberTextAssemblerRules);
    }
}
