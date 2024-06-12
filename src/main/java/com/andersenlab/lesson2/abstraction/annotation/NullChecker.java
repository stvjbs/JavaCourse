package com.andersenlab.lesson2.abstraction.annotation;

import java.lang.reflect.Field;

public class NullChecker {
    public static void checkObject(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getAnnotation(NullableWarning.class) != null) {
                field.setAccessible(true);
                try {
                    Object value = field.get(object);
                    if (value == null) {
                        System.out.printf("Variable [{%s}] is null in [%s]\n",
                                field.getName(), object.getClass().getSimpleName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
