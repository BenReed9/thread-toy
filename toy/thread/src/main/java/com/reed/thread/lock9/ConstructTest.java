package com.reed.thread.lock9;

public class ConstructTest {

    private static StaticObj staticObj = new StaticObj();

    private NoStaticObject noStaticObject = new NoStaticObject();

    private void testMethod() {
        staticObj.sayHello();

        noStaticObject.sayHello();
    }

    public static void main(String[] args) {
        new Thread(() -> new ConstructTest()
                .testMethod())
                .start();
        new Thread(() -> new ConstructTest()
                .testMethod())
                .start();
    }

}
