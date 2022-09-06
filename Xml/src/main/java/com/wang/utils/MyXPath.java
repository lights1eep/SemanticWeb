package com.wang.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * XPath查询类
 */
public class MyXPath {
    private Document document;

    /**
     * 构造函数
     * @param fileName xml文件名
     * @throws DocumentException
     */
    public MyXPath(String fileName) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        this.document = saxReader.read(fileName);
    }

    /**
     * XPath查询
     * @param path  XPath语句
     * @return 查询结果
     */
    public List<Node> myXPath(String path) {
        List nodes = document.selectNodes(path);
        return nodes;
    }
}
