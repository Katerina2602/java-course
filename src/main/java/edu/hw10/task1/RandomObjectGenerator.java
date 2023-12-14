package edu.hw10.task1;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;
import edu.hw10.task1.generator.Generator;
import edu.hw10.task1.generator.Restrictions;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

public class RandomObjectGenerator {
    private final Map<Class, Generator> generators;

    public RandomObjectGenerator(Map<Class, Generator> generators) {
        this.generators = generators;
    }

    public <T> T nextObject(Class<T> type) throws Exception {
        Constructor<?> constructor = Arrays.stream(type.getDeclaredConstructors())
            .max(Comparator.comparingInt(Constructor::getParameterCount))
            .orElseThrow(() -> new IllegalArgumentException("Type '%s' has not constructors".formatted(type)));

        Parameter[] parameters = constructor.getParameters();

        Object[] values = generateValuesByParameters(parameters, type);

        Object newInstance = constructor.newInstance(values);

        return (T) newInstance;
    }

    public <T> T nextObject(Class<T> type, String factoryMethodName) throws Exception {
        Method method = Arrays.stream(type.getDeclaredMethods())
            .filter(declaredMethod -> declaredMethod.getName().equals(factoryMethodName))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException(
                    "Type '%s' has not factory method '%s'".formatted(type, factoryMethodName)
                )
            );

        Parameter[] parameters = method.getParameters();

        Object[] values = generateValuesByParameters(parameters, type);

        Object newInstance = method.invoke(type, values);

        return (T) newInstance;
    }

    private Object[] generateValuesByParameters(Parameter[] parameters, Class<?> type) {
        return Arrays.stream(parameters)
            .map(parameter -> generateValue(parameter, type))
            .toArray();
    }

    private Object generateValue(Parameter parameter, Class<?> type) {
        Generator generator = generators.get(parameter.getType());

        if (generator != null) {
            Integer min = getMin(parameter, type);
            Integer max = getMax(parameter, type);
            boolean notNull = getNotNull(parameter, type);

            return generator.generate(new Restrictions(min, max, notNull));
        } else {
            throw new UnsupportedOperationException("Generate value for class %s unsupported".formatted(parameter));
        }
    }

    private Integer getMin(Parameter parameter, Class<?> type) {
        Optional<Field> foundField = Arrays.stream(type.getDeclaredFields())
            .filter(field -> field.getName().equals(parameter.getName()))
            .findFirst();

        if (foundField.isEmpty()) {
            return null;
        }

        return Arrays.stream(foundField.get().getDeclaredAnnotations())
            .filter(annotation -> annotation.annotationType() == Min.class)
            .findFirst()
            .map(Min.class::cast)
            .map(Min::value)
            .orElse(null);
    }

    private Integer getMax(Parameter parameter, Class<?> type) {
        Optional<Field> foundField = Arrays.stream(type.getDeclaredFields())
            .filter(field -> field.getName().equals(parameter.getName()))
            .findFirst();

        if (foundField.isEmpty()) {
            return null;
        }

        return Arrays.stream(foundField.get().getDeclaredAnnotations())
            .filter(annotation -> annotation.annotationType() == Max.class)
            .findFirst()
            .map(Max.class::cast)
            .map(Max::value)
            .orElse(null);
    }

    private boolean getNotNull(Parameter parameter, Class<?> type) {
        if (parameter.getType().isPrimitive()) {
            return true;
        }

        Optional<Field> foundField = Arrays.stream(type.getDeclaredFields())
            .filter(field -> field.getName().equals(parameter.getName()))
            .findFirst();

        if (foundField.isEmpty()) {
            return false;
        }

        return Arrays.stream(foundField.get().getDeclaredAnnotations())
            .filter(annotation -> annotation.annotationType() == NotNull.class)
            .findFirst()
            .map(annotation -> true)
            .orElse(false);
    }
}
