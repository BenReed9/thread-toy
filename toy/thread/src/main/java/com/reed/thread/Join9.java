package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Join9 {
    static int r = 0;

    public static void main(String[] args) {
        log.info("开始");
        Thread t1 = new Thread(() -> {
            log.info("开始");
            try {
                Thread.sleep(1);
                log.info("结束");
                r = 10;
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }, "t1");

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }

        log.info("结果为:{}", r);
    }

}
