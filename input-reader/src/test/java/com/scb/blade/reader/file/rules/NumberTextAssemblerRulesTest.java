package com.scb.blade.reader.file.rules;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberTextAssemblerRulesTest {

    @Test
    public void endOfMessage() {
        NumberTextAssemblerRules numberTextAssemblerRules = new NumberTextAssemblerRules(3, 5, false);
        assertTrue(numberTextAssemblerRules.endOfMessage(""));

        assertTrue(numberTextAssemblerRules.endOfMessage(null));

        assertTrue(numberTextAssemblerRules.endOfMessage("   "));
    }


    @Test
    public void noEndOfMessage() {
        NumberTextAssemblerRules numberTextAssemblerRules = new NumberTextAssemblerRules(3, 5, false);
        assertFalse(numberTextAssemblerRules.endOfMessage("ABC"));
        assertFalse(numberTextAssemblerRules.endOfMessage("1234"));
    }
}