package com.wang.opeate;

import com.wang.utils.MySparQL;

import java.util.Scanner;

/**
 * 显示SparQl查询
 */
public class SparQLQuery {
    // 前缀
    final String prefixs = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX wt: <http://www.semanticweb.org/10265/ontologies/2021/11/untitled-ontology-11#>\n";

    public static void main(String[] args) {
        String rdfFileName = "Rdf/src/main/resources/rdf2.owl";
        SparQLQuery sparQLQuery = new SparQLQuery();
        sparQLQuery.display(rdfFileName);
    }

    /**
     *  内容显示
     * @param rdfFilePath   rdf文件路径
     */
    private void display(String rdfFilePath) {
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

            MySparQL mySparQL = new MySparQL(rdfFilePath);
            mySparQL.mySparQLQuery(stringBuffer.toString());
        }
    }
}
