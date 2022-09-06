package com.wang.utils;

import org.dom4j.Node;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 查询结果写入文件
 */
public class MyFileWriter {
    /**
     * 写入节点信息
     * @param nodes 节点信息
     * @param newXmlFileName 结果文件
     */
    public static void writeNode(List<Node> nodes, String newXmlFileName) {
        File newXmlFile = new File(newXmlFileName);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(newXmlFile);
            fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            fileWriter.write("<?xml-stylesheet type=\"text/xsl\" href=\"index.xsl\"?>\n");
            fileWriter.write("<courses>\n");
            for(Node node : nodes) {
                fileWriter.write(node.asXML());
            }
            fileWriter.write("\n</courses>");
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
