package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class Runnable9 {

    public static void main(String[] args) {

        new Thread(new MyThread())
                .start();

        new MyThread2()
                .start();

        new Thread(() -> {
            log.info(Thread.currentThread().getName() + "Lambda开始执行任务");
        }).start();

        FutureTask<String> task =
                // 需要一个 Callable 实现类
                new FutureTask<>(() -> {
                    log.info("running...");
                    return Thread.currentThread().getName() + "实现FutureTask开始执行任务";
                });
        new Thread(task).start();

        try {
            log.info(task.get(1, TimeUnit.SECONDS));
        } catch (TimeoutException | ExecutionException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }

    }

    private static class MyThread implements Runnable {
        @Override
        public void run() {
            log.info(Thread.currentThread().getName() + "实现Runnable接口开始执行任务");
        }
    }

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            log.info(Thread.currentThread().getName() + "继承Thread类开始执行任务");
        }
    }
}
