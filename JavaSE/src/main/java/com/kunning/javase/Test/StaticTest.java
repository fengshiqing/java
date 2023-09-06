package com.kunning.javase.Test;

import org.junit.jupiter.api.Test;

class Father {
    public static int m = 33;
    static {
        System.out.println("父类被初始化");
    }
}

class Child extends Father {
    static {
        System.out.println("子类被初始化");
    }
}

public class StaticTest {
    @Test
    public void main11() {
        System.out.println(Child.m);
    }
}