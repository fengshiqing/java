/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: 花样多边形.java
 * Author:   kun
 * Date:     2018年4月30日 下午10:49:43
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.kunning.basics;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author kun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class 花样多边形 {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {

        System.out.println("左直角三角形");// 直角三角形
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }
        
        System.out.println("倒直角三角形");
        for (int i = 9; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        System.out.println("等腰三角形");// 等腰三角形
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                System.out.print(" ");
            }
            for (int z = 0; z < 2 * i + 1; z++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        System.out.println("右直角三角形");// 右直角三角形
        for (int i = 9; i >= 1; i--) {
            for (int j = 1; j < i; j++) {
                System.out.print(" ");
            }
            for (int z = i; z <= 9; z++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        System.out.println("右直角三角形");// 右直角三角形
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                System.out.print(" ");
            }

            for (int z = 0; z < i + 1; z++) {
                System.out.print("*");
                // System.out.print(" ");
            }
            System.out.print("\n");
        }

        System.out.println("三角帆：");// 三角帆：
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j < i; j++) {
                System.out.print(" ");
            }
            for (int z = i; z <= 9; z++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        // 矩形
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        // 平行四边形
        for (int i = 9; i >= 1; i--) {
            for (int z = 1; z <= i; z++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= 9; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        // 左直角梯形
        for (int i = 0; i < 9; i++) {
            for (int j = 1; j <= 9 + i; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        // 右直角梯形
        for (int i = 0; i < 9; i++) {
            for (int z = 9 - i; z > 1; z--) {
                System.out.print(" ");
            }

            for (int j = 1; j <= 9 + i; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        // 等腰梯形
        for (int i = 0; i < 9; i++) {
            for (int z = 9 - i; z > 1; z--) {
                System.out.print(" ");
            }

            for (int j = 1; j <= 9 + 2 * i; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }

    }

}
