package com.kunning.commons.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ArrayDemo {
	public static void main(String[] args) {
		String[] str = {"a","e","f","g","h","i","b","c","d"};
        
        System.out.println(".toString="+Arrays.toString(str));//打印出数组中所有数据
        System.out.println(".asList="+Arrays.asList(str));
        
        Arrays.sort(str);//对数组进行排序，使用的是快速排序法
        System.out.println(".toString="+Arrays.toString(str));//打印排序后数组中所有数据

        Arrays.sort(str, Collections.reverseOrder());//对数组进行 倒序
        System.out.println(".asList="+Arrays.toString(str));
        
        int flag = Arrays.binarySearch(str, "b");//查找数组中 元素 的位置(数组下标从 0 开始)
        System.out.println("b的所在位置："+flag);
        
        String[] str2 = new String[4];
        Arrays.fill(str2, "w");//为数组中每个数据同初值
        
        System.out.println("str2[]="+Arrays.toString(str2));
        
        String[][] s1 = {{"a","b","c","d"},{"a","b","e","f"}};
        
        System.out.println("s1[][]="+Arrays.deepToString(s1));//打印出二维数组中的全部数据
        
        String [] s2 = {"a","b","c",};
        String [] s3 = {"c","d","e","f","w","g","q"};
        List<String> s4 = new ArrayList<String>();
        
        for (int i = 0; i < s2.length; i++) {        //取出两个数组的交集
            for (int j = 0; j < s3.length; j++) {
                if (s2[i].equals(s3[j])) {
                    s4.add(s2[i]);
                }
            }
        }
        System.out.println("s2[]与s3[]的交集="+s4.toString());
        
        
        
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("AA", 65);
        map.put("BB", 76);
        map.put("CC", 89);
        Set<Entry<String, Integer>> set = map.entrySet();
        for(Entry<String, Integer> o : set) {
        	System.out.println(o.getKey()  + "-------->" + o.getValue());
        }
        
        
        
        
    }

}