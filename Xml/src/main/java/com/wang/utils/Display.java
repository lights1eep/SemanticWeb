package com.wang.utils;

import org.dom4j.DocumentException;
import org.dom4j.Node;

import java.util.List;
import java.util.Scanner;

/**
 * 命令行展示
 */
public class Display {
    /**
     *  查询xml展示
     * @param xmlFileName       xml文件名
     * @param newXmlFileName    结果存储文件名
     */
    public static void xmlDisplay(String xmlFileName, String newXmlFileName) {
        MyXPath myXPath = null;
        try {
            myXPath = new MyXPath(xmlFileName);
        } catch (DocumentException e) {
            System.out.println("xml文件不存在!");
            System.exit(-1);
        }
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("请输入XPath语句(exit退出):");
            String s = scanner.nextLine();
            if("exit".equals(s)) {
                break;
            }
            List<Node> nodes = myXPath.myXPath(s);
            if(nodes.size() == 0) {
                System.out.println("XPath语句错误或结果不存在!");
            } else {
                MyFileWriter.writeNode(nodes, newXmlFileName);
                for(Node n : nodes) {
                    System.out.println(n.asXML());
                }
            }
            System.out.println("*****************************************");
        }
    }
}
