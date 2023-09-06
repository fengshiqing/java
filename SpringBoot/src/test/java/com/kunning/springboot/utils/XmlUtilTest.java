/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.utils;

import com.kunning.springboot.pojo.XmlObject;
import org.junit.jupiter.api.Test;

class XmlUtilTest {

    @Test
    void objectToXml() {
        XmlObject xmlObject = new XmlObject("111", "222", "333", "444");
        System.out.println(XmlUtil.objectToXml(xmlObject));
    }

    @Test
    void xmlToObject() {
        String xmlStr = "<XmlObject>\n" +
                "  <param_1>111</param_1>\n" +
                "  <param_2>222</param_2>\n" +
                "  <param_3>333</param_3>\n" +
                "  <param_4>444</param_4>\n" +
                "</XmlObject>";
        System.out.println(XmlUtil.xmlToObject(xmlStr, XmlObject.class));
    }

    @Test
    void formatXmlStr() {
        String xmlStr = "<XmlObject><param_1>111</param_1><param_2>222</param_2><param_3>333</param_3><param_4>444</param_4></XmlObject>";
        System.out.println(XmlUtil.formatXmlStr(xmlStr));
    }

    @Test
    void getStringNoBlank() {
        String xmlStr = "<XmlObject>\n" +
                "  <param_1>111</param_1>\n" +
                "  <param_2>222</param_2>\n" +
                "  <param_3>333</param_3>\n" +
                "  <param_4>444</param_4>\n" +
                "</XmlObject>";
        System.out.println(XmlUtil.getStringNoBlank(xmlStr));
    }

}