package com.scb.blade.number.scanner.application.config;

import com.scb.blade.config.NumberParserConfig;
import com.scb.blade.number.scanner.application.MainScanner;
import com.scb.blade.number.scanner.sink.ConsoleOutputSink;
import com.scb.blade.parser.NumberParserTopology;
import com.scb.blade.reader.file.NumberTextAssemblerTopology;
import com.scb.blade.reader.file.config.NumberTextReaderConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({NumberTextReaderConfig.class, NumberParserConfig.class})
public class NumberScannerConfig {

    @Bean
    public MainScanner mainScanner(NumberTextAssemblerTopology numberTextAssemblerTopology, NumberParserTopology numberParserTopology) {
        return new MainScanner(numberTextAssemblerTopology, numberParserTopology);
    }

    @Bean
    public ConsoleOutputSink consoleOutputSink() {
        return new ConsoleOutputSink();
    }
}
