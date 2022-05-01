package com.reed.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;
import sun.security.provider.certpath.PKIXExtendedParameters;

import java.lang.reflect.Field;

@Slf4j
public class Unsafe9 {

    public static void main(String[] args) {
        try {
            Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
            unsafe.setAccessible(true);

            Unsafe u = (Unsafe) unsafe.get(null);

            long idOffset = u.objectFieldOffset(People.class.getDeclaredField("id"));
            long nameOffset = u.objectFieldOffset(People.class.getDeclaredField("name"));

            People p = new People();
            p.setId(0l);
            p.setName("");

            u.compareAndSwapLong(p, idOffset, 0l, 1l);
            u.compareAndSwapObject(p, nameOffset, "", "时间");

            System.out.println(p);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}

@Data
class People {
    public volatile long id;
    public volatile String name;
}