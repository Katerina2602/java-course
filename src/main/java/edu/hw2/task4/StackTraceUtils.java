package edu.hw2.task4;

public class StackTraceUtils {
    private StackTraceUtils() {

    }

    public static CallingInfo callingInfo(Exception ex) {
        String fullClassName = ex.getStackTrace()[0].getClassName();

        return new CallingInfo(
            fullClassName.substring(fullClassName.lastIndexOf(".") + 1),
            ex.getStackTrace()[0].getMethodName()
        );
    }
}
