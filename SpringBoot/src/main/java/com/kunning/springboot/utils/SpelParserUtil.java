/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.utils;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 功能描述：解析EL表达式
 * 学习来源：https://www.ixigua.com/6858147249923785228?id=6857788490235511308&logTag=7ALfI0okt3AcKjoypx9JY
 *
 * @author fengshiqing
 * @since 2020-08-23
 */
public class SpelParserUtil {

    /**
     * 私有化构造函数
     */
    private SpelParserUtil() {
    }

    private static final ExpressionParser parser = new SpelExpressionParser();

    /**
     * 功能描述：获取注解修饰的方法的参数
     *
     * @param key        EL表达式字符串，占位符以#开头
     * @param paramNames 形参名称，可以理解为占位符名称
     * @param args       真实参数值，可以理解为占位符真实的值
     *
     * @return 返回EL表达式经过参数替换后的字符串的值
     */
    public static String getKey(String key, String[] paramNames, Object[] args) {
        // 1、将key字符串解析为 EL表达式
        Expression expression = parser.parseExpression(key);
        // 2、将形参和形参值以配对的方式配置到上下文中
        EvaluationContext context = new StandardEvaluationContext();
        if (args.length <= 0) {
            return null;
        }

        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        // 3、根据上下文运算EL表达式
        return expression.getValue(context, String.class);
    }

}
