package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Interrupt9 {

    // 打算阻塞状态线程 sleep / wait / join 的线程
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                // 两种打断方式,竟然不是一样的结果
//                TimeUnit.SECONDS.sleep(3);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        });

        Thread thread2 = new Thread(() -> {
            while(true) {
                Thread thread1 = Thread.currentThread();
                boolean interrupted = thread1.isInterrupted();
                if (interrupted) {
                    log.info("当前线程" + interrupted);
                }
            }
        });

        thread.start();
        thread2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("interrupt");
        thread.interrupt();
        thread2.interrupt();
        log.info("打断标记? isInterrupt: " + thread.isInterrupted());
    }

}
