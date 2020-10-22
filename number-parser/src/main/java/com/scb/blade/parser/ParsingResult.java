package com.scb.blade.parser;

import lombok.Value;

import java.util.List;
import java.util.Optional;

@Value
public class ParsingResult {
    private List<Optional<Integer>> digitMatches;
    private boolean asPerRules;
}
