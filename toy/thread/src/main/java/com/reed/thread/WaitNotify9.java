package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class WaitNotify9 {

    public static void main(String[] args) {
        GuarderObject object = new GuarderObject();

        new Thread(() -> {
            log.info("等待结果");
            // 当前线程从 object 里面获取到了结果!
            List<String> getResult = (List<String>) object.get(1000);
            log.info(Thread.currentThread().getName() + "从对应的 object 对象里面获取到了结果! " + getResult);

        }, "t1").start();

        new Thread(() -> {
            log.info("开始执行一个任务!");
            // 执行了某种操作, 返回了一个 list 结果信息
            List<String> result = Arrays.asList("1", "2", "3");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            object.complete(result);
        }).start();
    }

}

@Slf4j
class GuarderObject {
    private Object response;

    /**
     * 获取结果
     *
     * @return 获取结果信息
     */
    public Object get() {
        synchronized (this) {
            // 使用 while 可以解决虚假唤醒
            while (response == null) {
                log.info(Thread.currentThread().getName() + "还是在等待中..");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    /**
     * 获取结果
     *
     * @param timeout 超时时间
     * @return 获取结果信息
     */
    public Object get(long timeout) {
        synchronized (this) {

            long begin = System.currentTimeMillis();

            long passedTime = 0;

            // 使用 while 解决唤醒之后没有结果.
            while (response == null) {

                // 经历时间超过了最大超时时间
                if (passedTime >= timeout) {
                    break;
                }

                log.info(Thread.currentThread().getName() + "还是在等待中..");
                try {
                    this.wait(timeout - passedTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                passedTime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}