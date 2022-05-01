package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 两阶段终止模式
 * 在一个线程中如何优雅终止线程 T2
 *
 * @Author: reed
 */
@Slf4j
public class TwoPhaseTermination9 {

    public static void main(String[] args) {
        TwoPhaseTermination termination = new TwoPhaseTermination();
        termination.start();
        try {
            Thread.sleep(3500);
            termination.stop();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

}

@Slf4j
class TwoPhaseTermination {
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread currentThread = Thread.currentThread();
                if (currentThread.isInterrupted()) {
                    log.info("被终止后的操作!");
                    break;
                }

                try {
                    // 每秒执行一次监控
                    Thread.sleep(1000);
                    log.info("开始执行监控!");
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                    currentThread.interrupt();
                }
            }
        });

        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }

}
