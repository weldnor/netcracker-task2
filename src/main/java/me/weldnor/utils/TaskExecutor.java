package me.weldnor.utils;

import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Log
public class TaskExecutor {
    private final Stack<Runnable> tasks = new Stack<>();
    private final List<Thread> threads = new ArrayList<>();
    private final Object lock = new Object();

    public TaskExecutor(int threadsCount) {
        for (int i = 0; i < threadsCount; i++) {
            TaskExecutorThread thread = new TaskExecutorThread();
            threads.add(thread);
            thread.start();
        }
    }

    public void add(Runnable runnable) {
        synchronized (lock) {
            log.info("add task");
            tasks.add(runnable);
        }
    }

    private Runnable tryGetTask() {
        synchronized (lock) {
            if (tasks.size() > 0) {
                return tasks.pop();
            }
            return null;
        }
    }

    public void stop() {
        for (Thread thread : threads) {
            try {
                thread.interrupt();
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private final class TaskExecutorThread extends Thread {
        @Override
        public void run() {
            while (!interrupted()) {
                Runnable runnable = tryGetTask();
                if (runnable != null) {
                    runnable.run();
                }
            }
            log.info("thread " + getName() + " stopped");
        }
    }
}
