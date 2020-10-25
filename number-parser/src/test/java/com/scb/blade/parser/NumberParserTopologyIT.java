package com.scb.blade.parser;

import com.scb.blade.parser.config.NumberParserTestConfig;
import com.scb.blade.parser.rules.NumberLineParserRules;
import com.scb.blade.presentation.ThreeCellNumberPresentation;
import com.scb.blade.reader.buffer.model.DigitInput;
import com.scb.blade.reader.buffer.model.NumberLineInput;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rx.subjects.BehaviorSubject;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {NumberParserTestConfig.class})
public class NumberParserTopologyIT {
    @Autowired
    private NumberParserTopology numberParserTopology;
    @Autowired
    private ThreeCellNumberPresentation digitalNumberPresentation;
    @Autowired
    private NumberLineParserRules numberLineParserRules;

    @Test
    public void topology() {
        List<String> outputList = new ArrayList<>();
        BehaviorSubject<NumberLineInput> lineInputTopic = BehaviorSubject.create();
        numberParserTopology.start(lineInputTopic.asObservable())
                .doOnNext(outputList::add)
                .subscribe();

        lineInputTopic.onNext(NumberLineInput.builder(numberLineParserRules.getMaxDigits())
                .digitInputs(Arrays.asList(getDigitalInput(1, digitalNumberPresentation), getDigitalInput(2, digitalNumberPresentation),
                        getDigitalInput(3, digitalNumberPresentation),
                        getDigitalInput(4, digitalNumberPresentation), getDigitalInput(5, digitalNumberPresentation),
                        getDigitalInput(6, digitalNumberPresentation),
                        getDigitalInput(7, digitalNumberPresentation), getDigitalInput(8, digitalNumberPresentation),
                        getDigitalInput(9, digitalNumberPresentation)))
                .build());

        lineInputTopic.onNext(NumberLineInput.builder(numberLineParserRules.getMaxDigits())
                .digitInputs(Arrays.asList(getDigitalInput(2, digitalNumberPresentation), getDigitalInput(2, digitalNumberPresentation),
                        getDigitalInput(1, digitalNumberPresentation)))
                .build());

        lineInputTopic.onNext(NumberLineInput.builder(numberLineParserRules.getMaxDigits())
                .digitInputs(Arrays.asList(getDigitalInput(1, digitalNumberPresentation), getDigitalInput(2, digitalNumberPresentation),
                        DigitInput.builder().build(),
                        getDigitalInput(4, digitalNumberPresentation), getDigitalInput(5, digitalNumberPresentation),
                        getDigitalInput(6, digitalNumberPresentation),
                        getDigitalInput(7, digitalNumberPresentation), getDigitalInput(8, digitalNumberPresentation),
                        getDigitalInput(9, digitalNumberPresentation)))
                .build());

        Assert.assertEquals(Arrays.asList("123456789", "221ILL", "12?456789ILL"), outputList);
    }

    private DigitInput getDigitalInput(Integer digit, ThreeCellNumberPresentation digitalNumberPresentation) {
        Map<DigitInput, Integer> presentation = digitalNumberPresentation.getPresentation();
        Optional<Map.Entry<DigitInput, Integer>> any = presentation.entrySet().stream().filter(entry -> entry.getValue().equals(digit)).findAny();
        if(any.isPresent()) {
            return any.get().getKey();
        }
        return null;
    }

}
