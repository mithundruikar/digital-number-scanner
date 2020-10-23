package com.scb.blade.parser.rules;

import com.scb.blade.reader.buffer.model.NumberLineInput;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NumberLineParserRules {
    int maxDigits;
    boolean variableLength;

    public boolean valid(NumberLineInput numberLineInput) {
        int inputDigitsLength = numberLineInput.getDigitInputs().size();
        if(inputDigitsLength  == 0 || inputDigitsLength > getMaxDigits()) {
            return false;
        }
        if(!isVariableLength() && inputDigitsLength != getMaxDigits() ) {
            return false;
        }
        if(numberLineInput.isNumberLineTrimmed()) {
            return false;
        }
        return true;
    }
}
