package com.reed.thread;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.LinkedList;

@Slf4j
public class Consumer {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);

        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                queue.put(new Message(id, "值" + id));
            }, "生产者" + i).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                    Message message = queue.take();
                    log.info(Thread.currentThread().getName() + "消费到消息:" + message.toString());
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }, "消费者").start();
    }
}

@Slf4j
class MessageQueue {

    private final LinkedList<Message> list = new LinkedList<Message>();

    private final int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public Message take() {
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    log.debug("队列为空, 消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }

            Message message = list.removeFirst();
            log.debug("已消费消息 {}", message);
            list.notifyAll();
            return message;
        }
    }

    public void put(Message msg) {
        synchronized (list) {
            while (list.size() == capacity) {
                try {
                    log.debug("队列已满, 生产者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }

            list.addLast(msg);
            log.debug("已生产消息 {}", msg);
            list.notifyAll();
        }
    }
}

final class Message implements Serializable {

    private int id;

    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
