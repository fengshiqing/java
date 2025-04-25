/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.DesignPatterns.singleton;

/**
 * 功能描述：讲解 volatile
 *
 * @author 冯仕清
 * @since 2020/00/05
 */
public class Singleton_volatile {

    static class T {
        int m = 8;
    }

    public static void main(String[] args) {
        T t = new T();
    }

}

// 用到 Idea插件：jclasslib Bytecode viewer，用来查看字节码文件
// 早此之前，需要知道的知识：指令重排序

// 线程可见性
// 禁止指令重排序

// 内存屏障，屏障两端的指令不能重排

// 对象初始化时，在刚分配内存时，非静态的
// 使用了半初始化的对象，实际上只给属性赋了默认值，此时就指令重排，先执行了引用，后执行了赋值。这样的指令重排就会导致别的线程拿到的对象是没有正确赋值的。
