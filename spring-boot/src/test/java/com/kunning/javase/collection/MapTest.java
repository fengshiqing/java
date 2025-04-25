/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.javase.collection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

/*
 * 功能描述：学习 Map 接口
 *
 * Map接口
 * 		|-----HashMap:Map主要实现类
 * 		|-----LinkedHashMap:使用链表维护添加进Map中的顺序。故遍历Map时，是按添加的顺序遍历的。
 * 		|-----TreeMap:按照添加进Map中的元素的key的指定属性进行排序。要求：key必须是同一个类的对象！
 * 				针对key：自然排序   vs 定制排序
 * 		|-----Hashtable:古老的实现类，线程安全，不建议使用。不允许null作为key和value
 * 			|----Properties:常用来处理属性文件。键和值都为String类型的
 *
 * HashMap：
 * 1、key使用Set来存放的，不可重复。value是用Collection来存放的，可重复
 * 一个key-value对，是一个Entry。所有的Entry是用Set存放的，也是不可重复的。
 * 2、向HashMap中添加元素时，会调用key所在类的equals方法，判断key是否相同，若相同，则后一个元素会替换前一个相同的元素。
 */
public class MapTest {

    // 使用Properties处理属性文件
    public void test6() throws FileNotFoundException, IOException {
        Properties pros = new Properties();
        pros.load(new FileInputStream(new File("1.properties")));

        String name = pros.getProperty("name");
        System.out.println(name);

        String gender = pros.getProperty("gender");
        System.out.println(gender);
    }

    // 定制排序
    public void test5() {
        Comparator<Object> com = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Customer && o2 instanceof Customer) {
                    Customer c1 = (Customer) o1;
                    Customer c2 = (Customer) o2;
                    int i = c1.getId().compareTo(c2.getId());
                    if (i == 0) {
                        return c1.getName().compareTo(c2.getName());
                    }
                    return i;
                }
                return 0;
            }
        };
        TreeMap<Object, Object> map = new TreeMap<>(com);
        map.put(new Customer("AA", 1001), 87);
        map.put(new Customer("CC", 1001), 67);
        map.put(new Customer("MM", 1004), 77);
        map.put(new Customer("GG", 1002), 97);

        Set<Object> set1 = map.keySet();
        for (Object obj : set1) {
            System.out.println(obj + "----->" + map.get(obj));
        }
    }

    // 自然排序
    public void test4() {
        Map<Object, Object> map = new TreeMap<>();
        map.put(new Person("AA", 23), 89);
        map.put(new Person("MM", 22), 79);
        map.put(new Person("GG", 23), 99);
        map.put(new Person("JJ", 13), 69);

        Set<Object> set1 = map.keySet();
        for (Object obj : set1) {
            System.out.println(obj + "----->" + map.get(obj));
        }
    }

    /**
     * 功能描述：遍历Map
     */
    public void traversalMap() {
        // HashMap：key是用Set来存放的，不可重复。value是用Collection来存放的，可重复。一个key-value对，是一个Entry。所有的Entry是用Set存放的，也是不可重复的。
        Map<String, Object> map = new HashMap<>();
        map.put("AA", 213);
        map.put("BB", 45);
        map.put("123", "CC");
        map.put(null, null); // 此处存储了两个 key 为 null，但是遍历时只有一个
        map.put(null, null); // 此处存储了两个 key 为 null，但是遍历时只有一个
        map.put("CC", new Person("DD", 23));

        // 方式一：这是最常见写法，也是大多数情况下最可取的遍历方式。键值都需要时使用。
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("【方式一遍历Map：】" + "Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        // 方法二：如果只需要map中的键或者值，你可以通过keySet或values来实现遍历，而不是用entrySet。
        // 只遍历map中的键
        for (String key : map.keySet()) {
            System.out.println("【方式二遍历Map中的key：】" + "Key = " + key);
        }
        // 只遍历map中的值
        for (Object value : map.values()) {
            System.out.println("【方式一遍历Map的value：】" + "Value = " + value);
        }

        // 方法三：使用Iterator遍历
        // 你也可以在keySet和values上应用同样的方法。
        // 该种方式看起来冗余却有其优点所在。首先，在老版本java中这是惟一遍历map的方式。
        // 另一个好处是，你可以在遍历时调用iterator.remove()来删除entries，另两个方法则不能。根据javadoc的说明，如果在for-each遍历中尝试使用此方法，结果是不可预测的。
        // 从性能方面看，该方法类同于for-each遍历（即方法二）的性能。
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            System.out.println("【方式二遍历Map：】" + "Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        // 方法四：通过键找值遍历（效率低）
        // 作为方法一的替代，这个代码看上去更加干净；但实际上它相当慢且无效率。因为从键取值是很耗时的操作（与方法一相比，在不同的Map实现中该方法慢了20%~200%）。
        // 如果你安装了FindBugs，它会做出检查并警告你关于哪些是低效率的遍历。所以尽量避免使用。
        for (String key : map.keySet()) {
            Object value = map.get(key);
            System.out.println("Key = " + key + ", Value = " + value);
        }
        // 总结：如果仅需要键(keys)或值(values)使用方法二。如果你使用的语言版本低于java 5，或是打算在遍历时删除entries，必须使用方法三。否则使用方法一(键值都要)。
    }

}
