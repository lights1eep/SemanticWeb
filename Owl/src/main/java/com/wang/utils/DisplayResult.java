package com.wang.utils;

import java.util.Scanner;

/**
 * 结果显示
 */
public class DisplayResult {
    final static String prefixs = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX wt: <http://www.semanticweb.org/10265/ontologies/2021/11/untitled-ontology-14#>\n";

    /**
     * SparQL结果展示
     * @param rdfFilePath   rdf文件路径
     */
    public static void displaySparQLResult(String rdfFilePath) {
        MySparQL mySparQL = new MySparQL(rdfFilePath);
        Scanner scanner = new Scanner(System.in);
        System.out.println("已经设置相关前缀如下!");
        System.out.println(prefixs);
        while(true) {
            System.out.println("请输入查询语句(exit退出):");
            StringBuffer stringBuffer = new StringBuffer();
            String s = null;
            if(scanner.hasNextLine()) {
                stringBuffer.append(prefixs);
                s = scanner.nextLine();
                if("exit".equals(s)) {
                    scanner.close();
                    System.exit(0);
                }
                stringBuffer.append(s);
            }
            mySparQL.mySparQLQuery(stringBuffer.toString());
        }
    }

    /**
     * 推理结果显示
     * @param owlFilePath   owl文件路径
     * @param ruleFilePath  rule文件路径
     */
    public static void displayReasonerResult(String owlFilePath, String ruleFilePath) {
        MyReasoner myReasoner = new MyReasoner(owlFilePath, ruleFilePath);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("请输入人物姓名(exit退出):");
            String name = scanner.nextLine();
            if("exit".equals(name)) {
                break;
            }
            System.out.print("请输入人物关系(exit退出):");
            String relation = scanner.nextLine();
            if("exit".equals(relation)) {
                break;
            }
            myReasoner.getNameByRelation(name, relation);
        }
    }
}
