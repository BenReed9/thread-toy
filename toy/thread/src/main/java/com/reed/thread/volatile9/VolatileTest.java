package com.reed.thread.volatile9;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VolatileTest {
    int a = 1;
    int b = 2;

    public void change(){
        a = 3;
        b = a;
    }

    public void print(){
        System.out.println("b="+b+";a="+a);
    }

    public static void main(String[] args) {
        while (true){
            final VolatileTest test = new VolatileTest();
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.change();
            }).start();
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.print();
            }).start();
        }
    }

}