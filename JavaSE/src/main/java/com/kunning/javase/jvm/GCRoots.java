package com.kunning.javase.jvm;

/**
 * 功能描述：可行性分析，根搜索算法
 */
public class GCRoots {


    // 引用计数算法，存在“循环引用”的问题，

    // 标记清除算法，先标记，再清楚。这个算法有个致命问题，
    // 效率低，每次都要STW，
    // 还会内存碎片化，内存碎片化会进一步降低效率，必须记录下内存哪些再用，哪些空闲，给大对象分配内存空间时，也不容易。

    // 标记整理算法



}
