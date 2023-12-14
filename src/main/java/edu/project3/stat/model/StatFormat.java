package edu.project3.stat.model;

import java.util.Arrays;

public enum StatFormat {
    MARKDOWN("markdown"),
    ADOC("adoc");

    private final String format;

    StatFormat(String format) {
        this.format = format;
    }

    public static StatFormat parse(String value) {
        return Arrays.stream(values())
            .filter(format -> format.format.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Not supported fromat - " + value));
    }
}
