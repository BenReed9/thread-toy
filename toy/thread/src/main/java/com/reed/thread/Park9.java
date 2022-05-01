package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Park9 {

    /**
     * isInterrupted 线程是否中断,不会将标记改变
     * interrupted 会将标记记录改变
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("park...");
            // 让当前线程停下来
            LockSupport.park();
            log.debug("unPark...");
            log.debug("打断状态:{}", Thread.currentThread().isInterrupted());

            LockSupport.park();
            log.debug("打断状态:{}", Thread.interrupted());
        }, "t1");
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        t1.interrupt();
    }

}
