package ru.stepup.task3;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPool {
    private final Queue<Runnable> workQueue;
    private Deque<Runnable> waitingQueue;
    private boolean isShutdown;
    private final int poolSize;
    private final AtomicInteger workingThreadsCount;

    public MyThreadPool(int poolSize) {
        this.poolSize = poolSize;
        workingThreadsCount = new AtomicInteger();
        this.workQueue = new LinkedList<>();
        this.waitingQueue = new LinkedList<>();
        for (int i = 0; i < poolSize; i++) {
            new Thread(this::run).start();
        }
    }

    private void run() {
        while (!isShutdown) {
            try {
                Thread.sleep(100);
                Runnable runnable;
                synchronized (this) {
                    runnable = workQueue.poll();
                }
                if (runnable == null) {
                    continue;
                }
                workingThreadsCount.incrementAndGet();
                runnable.run();
                workingThreadsCount.decrementAndGet();

                synchronized (this) {
                    if (!isShutdown && !waitingQueue.isEmpty()) {
                        workQueue.offer(waitingQueue.removeFirst());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void execute(Runnable command) {
        if (isShutdown) {
            throw new IllegalStateException("Запуск execute после остановки пула потоков!");
        }
        if (command == null) {
            throw new NullPointerException("Метод execute не принимает значение null!");
        }
        synchronized (this) {
            if (workQueue.size() < poolSize) {
                workQueue.offer(command);
            } else {
                waitingQueue.addLast(command);
            }
        }
    }

    public synchronized void shutdown() {
        isShutdown = true;

        for (int i = workingThreadsCount.get(); i < poolSize; i++) {
            workQueue.offer(() -> System.out.println(Thread.currentThread().getName() + " был остановлен!"));
        }
    }

    public void awaitTermination() throws InterruptedException {
        if (!isShutdown) {
            throw new IllegalStateException("Метод awaitTermination запускается только после остановки пула потоков!");
        }
        while (workingThreadsCount.get() != 0) {
            Thread.sleep(100);
        }
    }
}
