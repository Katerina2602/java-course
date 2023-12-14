package edu.hw10.task1.model;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;

public record Cat(
    @Max(value = 10)
    @Min(value = 1)
    @NotNull
    String name,
    String ownerName,
    @Max(value = 20)
    @Min(value = 0)
    @NotNull
    Integer age
) {
    public static Cat create(String name, String ownerName, Integer age) {
        return new Cat(name, ownerName, age);
    }
}
