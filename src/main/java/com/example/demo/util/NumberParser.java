package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component
public class NumberParser {

    public Integer parsYear(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
