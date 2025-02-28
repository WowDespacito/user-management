package com.wowdespacito.user_management.utils;

public class ThreadLocalUtil {
    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static void set(Object value) {
        threadLocal.set(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get() {
        return (T) threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
