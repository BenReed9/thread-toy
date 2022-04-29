package com.reed.thread.lock9;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StaticObj {

    public StaticObj() {
        log.info("开始初始化静态变量了 , 当前对象地址为: " + this.toString());
    }

    public void sayHello() {
        log.info(Thread.currentThread().getName() + " sayHello");
    }

}
