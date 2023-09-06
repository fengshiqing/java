package com.kunning.springboot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能描述：XML工具类。
 *
 * @author 冯仕清
 * @since 2019/12/21
 */
@Slf4j
public class XmlUtil {

    /**
     * 私有化构造方法
     */
    private XmlUtil() {
    }

    /**
     * 功能描述: 将对象转为XML
     *
     * @param obj 需要转换的对象
     *
     * @return xml字符串
     */
    public static String objectToXml(Object obj) {
        // XStream xStream = new XStream(new DomDriver("UTF-8"));
        // Xstream有个Bug，例如@XStreamAlias("T_RESULT")在解析的时候，下划线会变成双下划线__
        // 参考 https://www.jianshu.com/p/2889e066dc4d
        XStream xStream = new XStream(new Xpp3Driver(new NoNameCoder()));
        xStream.processAnnotations(obj.getClass()); // 解析 XML 的根标签，加这个，根标签是这样：<com.kunning.springboot.pojo.XmlObject>
        return xStream.toXML(obj);
    }

    /**
     * 功能描述: 将XML转为对象
     *
     * @param xmlStr xml字符串
     * @param clazz 对象类型
     *
     * @return 转换后的对象
     */
    @SuppressWarnings("rawtypes")
    public static Object xmlToObject(String xmlStr, Class clazz) {
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(clazz);
        return xStream.fromXML(xmlStr);
    }

    /**
     * 功能描述: 格式化XML字符串
     *
     * @param xmlStr XML字符串
     *
     * @return 格式化后的XML字符串
     */
    public static String formatXmlStr(String xmlStr) {
        return null;
    }

    /**
     * 功能描述: 去除字符串中的空格 获取没有空格的String
     *
     * @param str 字符串
     *
     * @return 没有空格的字符串
     */
    public static String getStringNoBlank(String str) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

}
