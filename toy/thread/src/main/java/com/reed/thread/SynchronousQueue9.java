package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;

@Slf4j
public class SynchronousQueue9 {

    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                log.info("putting {}", "test");
                queue.put("test");
                log.info("{} , putted", "test");

                log.info("putting {}", "test2");
                queue.put("test2");
                log.info("{} , putted", "test2");

            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }, "t1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        new Thread(() -> {
            try {
                log.info("taking {}", 1);
                queue.take();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }, "t1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        new Thread(() -> {
            try {
                log.info("taking {}", 2);
                queue.take();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }, "t1").start();
    }

}
