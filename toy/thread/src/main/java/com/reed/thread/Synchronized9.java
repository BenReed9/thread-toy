package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 每个实例都对应有自己的一把锁(this),不同实例之间互不影响；
 * 例外：锁对象是*.class以及synchronized修饰的是static方法的时候，所有对象公用同一把锁
 */
@Slf4j
public class Synchronized9 {

    /**
     * 静态方法,锁是Class对象
     */
    public static synchronized void inStaticMethod() {
        log.info(Thread.currentThread().getName() + " say inStaticMethod");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 普通方法,锁是实例对象
     */
    private synchronized void inMethod() {
        log.info(Thread.currentThread().getName() + " say inMethod");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 方法代码块里面使用 Class 对象作为锁对象
     */
    private void inBlockUseClass() {
        synchronized (Synchronized9.class) {
            log.info(Thread.currentThread().getName() + "say inBlockUseClass");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void inBlockUseInstance() {
        synchronized (this) {
            log.info(Thread.currentThread().getName() + "say inBlockUseClass");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
//        new Thread(SynchronizedInStaticMethod::inStaticMethod)
//                .start();
//        new Thread(SynchronizedInStaticMethod::inStaticMethod)
//                .start();

//        new Thread(new SynchronizedInStaticMethod()::inMethod)
//                .start();
//        new Thread(new SynchronizedInStaticMethod()::inMethod)
//                .start();
//
//        new Thread(new Synchronized9()::inBlockUseClass)
//                .start();
//        new Thread(new Synchronized9()::inBlockUseClass)
//                .start();

        Synchronized9 synchronized9 = new Synchronized9();
        new Thread(synchronized9::inBlockUseInstance)
                .start();
        new Thread(synchronized9::inBlockUseInstance)
                .start();
        new Thread(new Synchronized9()::inBlockUseInstance)
                .start();
    }

}
