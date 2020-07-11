package com.kunning.javase.Test;

/**
 * 虚拟机参数：-verbose:gc
 */
public class ReferenceCountingGC
{
    private Object instance = null;
    private static final int _1MB = 1024 * 1024;
    
    /** 这个成员属性唯一的作用就是占用一点内存 */
    private byte[] bigSize = new byte[2 * _1MB];
    
    public static void main(String[] args)
    {
        ReferenceCountingGC objectA = new ReferenceCountingGC();
        System.out.println(objectA);
        ReferenceCountingGC objectB = new ReferenceCountingGC();
        System.out.println(objectB);
        objectA.instance = objectB;
        System.out.println(objectA);
        objectB.instance = objectA;
        System.out.println(objectB);
        objectA = null;
        objectB = null;
        
        System.gc();
        
        
    }
}