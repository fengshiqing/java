/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.utils;

/**
 * 功能描述：雪花算法。
 * 特点：
 * 1、分布式，且全局唯一
 * 2、递增，但不是连续递增
 * 3、高可用
 * 4、高性能
 * <p>
 * 组成部分：
 * 1、第一位 占用1bit，其值始终是0，没有实际作用。
 * 2、时间戳 占用41bit，精确到毫秒，总共可以容纳约69年的时间。
 * 3、工作机器id 占用10bit，其中高位5bit是数据中心ID，低位5bit是工作节点ID，做多可以容纳1024个节点。
 * 4、序列号 占用12bit，每个节点每毫秒0开始不断累加，最多可以累加到4095，一共可以产生4096个ID。
 *
 * @author 冯仕清
 * @since 2023-08-11
 */
public class SnowflakeIdWorker {

    /**
     * 开始时间截 (2015-01-01)
     */
    private final long twepoch = 1420041600000L;

    /**
     * 机器id标识 所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * IDC机房id标识 所占的位数
     */
    private final long datacenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 序列在id中占的位数，我们设置为12位，可以自定义
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     * ^异或运算符，用的比较少，相应的位 相同为0，不相同为1
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * idc机房（互联网数据中心）ID(0~31)
     */
    private long idcId;

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     *
     * @param idcId 数据中心ID (0~31)
     * @param workerId     工作机器ID (0~31)
     */
    public SnowflakeIdWorker(long idcId, long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId can't be greater than %d or less than 0", maxWorkerId));
        }
        if (idcId > maxDatacenterId || idcId < 0) {
            throw new IllegalArgumentException(String.format("datacenterId can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.idcId = idcId;
        this.workerId = workerId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        // 如果当前时间小于上一次ID生成的时间戳，说明系统发生了 时钟回退/时钟回拨，这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("【Clock moved backwards. Refusing to generate id for %d milliseconds】", lastTimestamp - timestamp));
        }
        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else { // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
        // 上次生成ID的时间截
        lastTimestamp = timestamp;
        // 移位并通过 或运算 拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) // 时间戳部分
                | (idcId << datacenterIdShift) // idc
                | (workerId << workerIdShift) //
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) { // 阻塞到下一个毫秒
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) throws InterruptedException {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 10; i++) {
            long id = idWorker.nextId();
            Thread.sleep(1);
            System.out.println(id);
        }
    }

}
