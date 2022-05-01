package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Yield9 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int count = 0;
            for (; ; ) {
                log.info("1" + count++);
            }
        });

        Thread t2 = new Thread(() -> {
            int count = 0;
            for (; ; ) {
                log.info("      2 " + count++);
            }
        });

        t1.setPriority(1);
        t2.setPriority(10);

        t1.start();
        t2.start();
    }

}
