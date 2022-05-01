package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class Atomic9 {

    private Long count = 0L;
    private static final AtomicLong newCount = new AtomicLong(0);

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                long l = newCount.addAndGet(1);
                log.info(Thread.currentThread().getName() + " " + l);
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                long l = newCount.addAndGet(1);
                log.info(Thread.currentThread().getName() + " " + l);
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("结果信息是: {}", newCount.get());
    }

}
