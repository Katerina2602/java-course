package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmarkMain {
    private static final int WARMUP_TIME_SECONDS = 10;
    private static final int MEASUREMENT_TIME_SECONDS = 10;
    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Function<Student, String> nameGetter;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmarkMain.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(WARMUP_TIME_SECONDS))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(MEASUREMENT_TIME_SECONDS))
            .build();

        new Runner(options).run();
    }

    @Setup
    public void setup() throws Throwable {
        String fieldName = "name";
        student = new Student("Ivan", "Ivanov");
        method = Student.class.getMethod(fieldName);

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Method nameMethod = Student.class.getDeclaredMethod(fieldName);
        nameMethod.setAccessible(true);
        methodHandle = lookup.unreflect(nameMethod);

        nameGetter = (Function<Student, String>) LambdaMetafactory.metafactory(
                lookup,
                "apply",
                MethodType.methodType(Function.class),
                MethodType.methodType(Object.class, Object.class),
                methodHandle,
                methodHandle.type()
            )
            .getTarget()
            .invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        Object name = method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandles(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        String name = nameGetter.apply(student);
        bh.consume(name);
    }
}
