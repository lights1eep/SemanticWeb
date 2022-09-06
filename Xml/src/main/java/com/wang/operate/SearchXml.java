package com.wang.operate;

import com.wang.utils.Display;

/**
 * xpath查询xml
 */
public class SearchXml {
    public static void main(String[] args) {
        String xmlFileName = "Xml/src/main/resources/courses.xml";
        String newXmlFileName = "Xml/src/main/resources/index.xml";
        Display.xmlDisplay(xmlFileName, newXmlFileName);
    }
}
