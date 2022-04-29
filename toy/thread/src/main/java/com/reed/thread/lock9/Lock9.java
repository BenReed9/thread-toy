package com.reed.thread.lock9;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Lock9 {

    // 静态变量在类初始化的时候放到了方法区里面, 所以是同一个对象
    private static Lock staticLock = new ReentrantLock();

    // 成员变量在类初始化的时候加载, 不是同一个对象
    private Lock lock = new ReentrantLock();

    private void testLock() {
        if (staticLock.tryLock()) {
            try {
                log.info(Thread.currentThread().getName() + " testLock");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                staticLock.unlock();
            }
        }
    }

    private void testStaticLockWait() {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                log.info(Thread.currentThread().getName() + " testLock");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    private void testLockWait() {
        try {
            if (staticLock.tryLock(3, TimeUnit.SECONDS)) {
                log.info(Thread.currentThread().getName() + " testLock");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            staticLock.unlock();
        }
    }

    public static void main(String[] args) {
//        new Thread(() -> new Lock9().testLock())
//                .start();
//        new Thread(() -> new Lock9().testLock())
//                .start();

//        new Thread(() -> new Lock9().testStaticLockWait())
//                .start();
//        new Thread(() -> new Lock9().testStaticLockWait())
//                .start();

//        new Thread(() -> new Lock9().testStaticLockWait())
//                .start();
//        new Thread(() -> new Lock9().testStaticLockWait())
//                .start();

    }

}
