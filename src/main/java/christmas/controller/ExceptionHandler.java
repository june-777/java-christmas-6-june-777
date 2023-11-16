package christmas.controller;

import java.util.function.Supplier;

public class ExceptionHandler {
    public static <T> T handle(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
