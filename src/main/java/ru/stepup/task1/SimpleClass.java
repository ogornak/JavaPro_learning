package ru.stepup.task1;

import ru.stepup.task1.annotation.*;

public class SimpleClass {
    @Test
    @CsvSource("1")
    public void method1(int i) {
        System.out.println("method1 i = " + i);
    }

    @Test(priority = 7)
    @CsvSource("java, true")
    public static void method2(String name, boolean isLanguage) {
        System.out.println("method2 name = " + name + ", isLanguage = " + isLanguage);
    }

    @Test(priority = 2)
    public void method3() {
        System.out.println("method3");
    }

    @BeforeSuite
    public static void beforeSuiteMethod() {
        System.out.println("beforeSuiteMethod");
    }

    @AfterSuite
    public static void afterSuiteMethod() {
        System.out.println("afterSuiteMethod");
    }

    @BeforeTest
    public static void beforeTestMethod() {
        System.out.println("beforeTestMethod");
    }

    @AfterTest
    public static void afterTestMethod() {
        System.out.println("afterTestMethod");
    }
}
