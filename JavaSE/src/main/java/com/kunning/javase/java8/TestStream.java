package com.kunning.javase.java8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TestStream {


    @Test
    public void test() {

        //  在 Java 8 中, 集合接口有两个方法来生成流：
        // 1、stream() − 为集合创建串行流。
        // 2、parallelStream() − 为集合创建并行流。
        List<String> strList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered1 = strList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        int num = (int) strList.stream().filter(string -> !string.isEmpty()).count();
        // 并行（parallel）程序
        // parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
        int count = (int) strList.parallelStream().filter(string -> string.isEmpty()).count(); // 获取空字符串的数量
        System.out.println(filtered1);
        System.out.println("【非空元素数量】" + num);

        Random random = new Random();

        // limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：
        // forEach：Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数：
        random.ints().limit(10).forEach(System.out::println);

        // map方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
        List<Integer> numList = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList1 = numList.stream().map(i -> i * i).collect(Collectors.toList()); // 不去重
        List<Integer> squaresList2 = numList.stream().map(i -> i * i).distinct().collect(Collectors.toList()); // 去重
        System.out.println("【map流stream】" + squaresList1);
        System.out.println("【map流stream】" + squaresList2);

        // sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
        random.ints().limit(10).sorted().forEach(System.out::println);

        // Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
        List<String> filtered = strList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("【筛选列表：】" + filtered);
        String mergedString = strList.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("【合并字符串：】" + mergedString);
    }

}
