package com.kunning.commons.java8;

import java.util.*;
import java.util.stream.Collectors;

public class Java8Tester {

    public static void main(String[] args) {

        List<String> names1 = new ArrayList<>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");

        List<String> names2 = new ArrayList<>();
        names2.add("Google ");
        names2.add("Runoob ");
        names2.add("Taobao ");
        names2.add("Baidu ");
        names2.add("Sina ");

        Java8Tester tester = new Java8Tester();
        System.out.println("使用 Java 7 语法: ");

        tester.sortUsingJava7(names1);
        System.out.println(names1);
        System.out.println("使用 Java 8 语法: ");

        tester.sortUsingJava8(names2);
        System.out.println(names2);


        List<PersonModel> list = new ArrayList<>();
        list.add(new PersonModel("wu qi", 18, "男"));
        list.add(new PersonModel("zhang san", 19, "男"));
        list.add(new PersonModel("wang si", 20, "女"));
        list.add(new PersonModel("zhao wu", 23, "男"));
        list.add(new PersonModel("chen liu", 18, "女"));

//		System.out.println("【过滤所有男性】" + list.stream().filter(person -> "男".equals(person.getSex())));
        System.out.println("【过滤出所有男性】" + list.stream().filter(person -> "男".equals(person.getSex())).collect(Collectors.toList()));
        System.out.println("【过滤出小于20岁的人】" + list.stream().filter(person -> person.getAge() < 20).collect(Collectors.toList()));
        System.out.println("【过滤出小于20岁的男人】" + list.stream().filter(person -> (person.getAge() < 20) && ("男".equals(person.getSex()))).collect(Collectors.toList()));
        System.out.println("【过滤出所有用户的名字，方式一】" + list.stream().map(person -> person.getName()).collect(Collectors.toList()));
        System.out.println("【过滤出所有用户的名字，方式二】" + list.stream().map(PersonModel::getName).collect(Collectors.toList()));
        System.out.println("【过滤出所有用户的名字，方式三】" + list.stream().map(person -> {
            // System.out.println(person.getName());
            return person.getName();
        }).collect(Collectors.toList()));

		System.out.println();

		System.out.println("【过滤出】" + list.stream().flatMap(person -> Arrays.stream(person.getName().split(" "))).collect(Collectors.toList()));
		System.out.println("【过滤出】" + list.stream().map(person -> Arrays.stream(person.getName().split(" "))).collect(Collectors.toList()));

		System.out.println("【过滤出】" + list.stream().map(person ->person.getName().split(" ")).flatMap(Arrays::stream).collect(Collectors.toList()));
		System.out.println("【过滤出】" + list.stream().map(person ->person.getName().split(" ")).flatMap(str -> Arrays.asList(str).stream()).collect(Collectors.toList()));
    }

    // 使用 java 7 排序
    private void sortUsingJava7(List<String> names) {
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
    }

    // 使用 java 8 排序
    private void sortUsingJava8(List<String> names) {
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
    }
}
