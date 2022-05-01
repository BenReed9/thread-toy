package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.TreeMap;

/**
 * 实现两个线程先打印 1 再打印2
 *
 * @Author: reed
 */
@Slf4j
public class OrderPrint1 {

    private static final Object lock = new Object();

    private static boolean canPrint = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                while (!canPrint) {
                    log.info(Thread.currentThread().getName() + "打印 1 没有获取到锁, 进入 waitSet!");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                }

                log.info(Thread.currentThread().getName() + "获取到锁, 开始打印 1");
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (lock) {
                log.info(Thread.currentThread().getName() + "打印 2 ");
                canPrint = true;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }

                lock.notifyAll();
            }
        }, "t2").start();

    }

}
