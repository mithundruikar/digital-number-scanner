package com.scb.blade.number.scanner.application;

import com.scb.blade.config.NumberParserConfig;
import com.scb.blade.parser.NumberParserTopology;
import com.scb.blade.reader.file.NumberTextAssembler;
import com.scb.blade.reader.file.config.NumberTextReaderConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({NumberTextReaderConfig.class, NumberParserConfig.class})
public class NumberScannerConfig {

    @Bean
    public MainScanner mainScanner(NumberTextAssembler numberTextAssembler, NumberParserTopology numberParserTopology) {
        return new MainScanner(numberTextAssembler, numberParserTopology);
    }
}
