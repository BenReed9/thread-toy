package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLock9 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                // 如果没有竞争, 这个方法会拿到 lock 对象锁
                // 如果有竞争就进入阻塞队列,可以被其他线程用 interrupt 方法打断
                log.info("尝试获取锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
                return;
            }

            try {
                log.info("获取到锁了");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();
    }

}
