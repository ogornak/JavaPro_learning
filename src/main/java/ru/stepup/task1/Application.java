package ru.stepup.task1;

public class Application {
    public static void main(String[] args) {
        try {
            TestRunner.runTest(SimpleClass.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
