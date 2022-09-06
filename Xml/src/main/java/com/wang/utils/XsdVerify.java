package com.wang.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.util.XMLErrorHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

/**
 * 使用xsd校验xml
 */
public class XsdVerify {
    public static void xsdVerify(String xmlFileName, String xsdFileName) {
        try {
            //创建默认的XML错误处理器
            XMLErrorHandler errorHandler = new XMLErrorHandler();
            //获取基于 SAX 的解析器的实例
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //解析器在解析时验证 XML 内容。
            factory.setValidating(true);
            //指定由此代码生成的解析器将提供对 XML 名称空间的支持。
            factory.setNamespaceAware(true);
            //使用当前配置的工厂参数创建 SAXParser 的一个新实例。
            SAXParser parser = factory.newSAXParser();
            //创建一个读取工具
            SAXReader xmlReader = new SAXReader();
            //获取要校验xml文档实例
            Document xmlDocument = (Document) xmlReader.read(new File(xmlFileName));
            //设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在 [url]http://sax.sourceforge.net/?selected=get-set[/url] 中找到。
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaSource", "file:" + xsdFileName);
            //创建一个SAXValidator校验工具，并设置校验工具的属性
            SAXValidator validator = new SAXValidator(parser.getXMLReader());
            //设置校验工具的错误处理器，当发生错误时，可以从处理器对象中得到错误信息。
            validator.setErrorHandler(errorHandler);
            //校验
            validator.validate(xmlDocument);

            //XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
            //如果错误信息不为空，说明校验失败，打印错误信息
            if (errorHandler.getErrors().hasContent()) {
                Element e = errorHandler.getErrors();
                List<Node> list = e.content();
                int count = 0;
                String result = "";
                for (Node node : list) {
                    String error = node.getText().split(":")[1];
                    if (error.contains("元素") && error.contains("无效")) {
                        count++;
                        result += error + "\n";
                    }
                }
                System.err.println("共有" + count + "处错误");
                System.err.println(result);
            } else {
                System.err.println("Good! XML文件通过XSD文件校验成功！");
            }
        } catch (Exception ex) {
            System.err.println("XML文件: " + xmlFileName + " 通过XSD文件:" + xsdFileName + "检验失败。\n原因： " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
