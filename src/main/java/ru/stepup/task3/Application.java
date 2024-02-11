package ru.stepup.task3;

public class Application {
    public static void main(String[] args) {
        MyThreadPool threadPool = new MyThreadPool(3);
        threadPool.execute(() -> System.out.println("Задача1"));
        threadPool.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Задача2");

        });
        threadPool.execute(() -> System.out.println("Задача3"));
        threadPool.execute(() -> System.out.println("Задача4"));
        threadPool.execute(() -> System.out.println("Задача5"));
        threadPool.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Задача6");

        });
        threadPool.execute(() -> System.out.println("Задача7"));

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Конец!");
    }
}
