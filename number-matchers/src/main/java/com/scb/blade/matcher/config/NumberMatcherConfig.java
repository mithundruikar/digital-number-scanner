package com.scb.blade.matcher.config;

import com.scb.blade.matcher.NumberMatcher;
import com.scb.blade.presentation.NumberPresentationRepository;
import com.scb.blade.presentation.ThreeCellNumberPresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class NumberMatcherConfig {

    @Value("${digital.number.scanner.input.digits.cellwidth:3}")
    private int digitsCellWidth;

    @Bean
    public ThreeCellNumberPresentation threeCellNumberPresentation() {
        return new ThreeCellNumberPresentation();
    }

    @Bean
    public NumberPresentationRepository numberPresentationRepository(ThreeCellNumberPresentation threeCellNumberPresentation) {
        return new NumberPresentationRepository(Arrays.asList(threeCellNumberPresentation));
    }
    @Bean
    public NumberMatcher numberMatcher(NumberPresentationRepository presentationRepository) {
        return new NumberMatcher(digitsCellWidth, presentationRepository);
    }
}
