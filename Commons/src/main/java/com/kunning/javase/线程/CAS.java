package com.kunning.javase.线程;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class CAS {

    @Test
    public  void casTest() {
        Object object = new Object();

        System.out.println(ClassLayout.parseInstance(object));
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

}
