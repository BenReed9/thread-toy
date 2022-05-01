package com.reed.thread;

import lombok.Data;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicFieldUpdater9 {

    public static void main(String[] args) {
        User user = new User();
        user.setAge(0);

        AtomicIntegerFieldUpdater<User> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        fieldUpdater.compareAndSet(user, 0, 10);

        System.out.println(user);
    }

}

@Data
class User {
    volatile String name;
    volatile int age;
    volatile Long amount;
}