package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class Validator {
    public static final String MASSAGE_ERROR = "Отрицательное значение не может быть";

    private Validator() {

    }

    public static Set<ValidationError> valid(Animal animal) {
        HashSet<ValidationError> errors = new HashSet<>();
        if (animal.age() <= 0) {
            errors.add(new ValidationError(MASSAGE_ERROR, "age"));
        }
        if (animal.height() <= 0) {
            errors.add(new ValidationError(MASSAGE_ERROR, "height"));
        }
        if (animal.weight() <= 0) {
            errors.add(new ValidationError(MASSAGE_ERROR, "weight"));
        }
        if (animal.paws() < 0) {
            errors.add(new ValidationError(MASSAGE_ERROR, "paws"));
        }

        return errors;
    }
}
