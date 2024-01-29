package ru.stepup.task1;

import ru.stepup.task1.annotation.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void runTest(Class c) throws Exception {
        var instance = c.getConstructor().newInstance();
        Method beforeSuite = null;
        Method afterSuite = null;
        List<Method> testMethods = new ArrayList<>();
        List<Method> beforeTestMethods = new ArrayList<>();
        List<Method> afterTestMethods = new ArrayList<>();
        for (var method : c.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeSuite.class) && Modifier.isStatic(method.getModifiers())) {
                if (beforeSuite != null) {
                    throw new IllegalArgumentException("Current class has more then one BeforeSuite method!");
                }
                beforeSuite = method;
            }
            if (method.isAnnotationPresent(AfterSuite.class) && Modifier.isStatic(method.getModifiers())) {
                if (afterSuite != null) {
                    throw new IllegalArgumentException("Current class has more then one AfterSuite method!");
                }
                afterSuite = method;
            }
            if (method.isAnnotationPresent(Test.class)) {
                if (1 > method.getAnnotation(Test.class).priority()
                        || method.getAnnotation(Test.class).priority() > 10) {
                    throw new IllegalArgumentException("Method " + method.getName() + " has priority out of range!");
                }
                testMethods.add(method);
            }
            if (method.isAnnotationPresent(BeforeTest.class)) {
                beforeTestMethods.add(method);
            }
            if (method.isAnnotationPresent(AfterTest.class)) {
                afterTestMethods.add(method);
            }
        }
        beforeSuite.invoke(null);

        testMethods.stream().sorted((Method o1, Method o2) -> Integer.compare(o2.getAnnotation(Test.class).priority(),
                o1.getAnnotation(Test.class).priority())).forEach(x -> {
            invoke(instance, beforeTestMethods);

            try {
                if (x.isAnnotationPresent(CsvSource.class)) {
                    x.invoke(instance, parseCsv(x));
                } else {
                    x.invoke(instance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            invoke(instance, afterTestMethods);
        });

        afterSuite.invoke(null);
    }

    private static Object[] parseCsv(Method method) throws Exception {
        int i = 0;
        var strings = method.getAnnotation(CsvSource.class).value().split(", ");
        var types = method.getGenericParameterTypes();
        if (strings.length != types.length) {
            throw new IllegalArgumentException("The number of parameters differs from the method parameters!");
        }
        Object[] objs = new Object[types.length];
        for (var type : types) {
            var cc = Class.forName(type.getTypeName().replace("int", "java.lang.Integer")
                    .replace("boolean", "java.lang.Boolean"));
            var parameter = cc.getConstructor(String.class).newInstance(strings[i]);
            objs[i++] = parameter;
        }
        return objs;
    }

    public static void invoke(Object instance, List<Method> methods) {
        methods.forEach(a -> {
            try {
                a.invoke(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
