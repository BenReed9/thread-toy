package com.reed.thread.lock9;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoStaticObject {
    public NoStaticObject() {
        log.info("非静态变量开始初始化 , 当前对象地址是:" + this.toString());
    }

    public void sayHello() {
        log.info(Thread.currentThread().getName() + " sayHello");
    }

}
