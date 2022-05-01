package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock
 *
 * @Author: reed
 */
@Slf4j
public class Lock9 {

    // 静态变量在类初始化的时候放到了方法区里面, 所以是同一个对象
    private static final Lock STATIC_LOCK = new ReentrantLock();

    // 成员变量在类初始化的时候加载, 不是同一个对象
    private final Lock LOCK = new ReentrantLock();

    private void testLock() {
        if (STATIC_LOCK.tryLock()) {
            try {
                log.info(Thread.currentThread().getName() + " testLock");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                STATIC_LOCK.unlock();
            }
        }
    }

    private void testStaticLockWait() {
        try {
            if (LOCK.tryLock(3, TimeUnit.SECONDS)) {
                log.info(Thread.currentThread().getName() + " testLock");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.unlock();
        }
    }

    private void testLockWait() {
        try {
            if (STATIC_LOCK.tryLock(3, TimeUnit.SECONDS)) {
                log.info(Thread.currentThread().getName() + " testLock");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            STATIC_LOCK.unlock();
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
