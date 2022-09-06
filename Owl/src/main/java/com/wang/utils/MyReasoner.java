package com.wang.utils;

import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.FileManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义推理机
 */
public class MyReasoner {
    private final String prefix = "http://www.semanticweb.org/10265/ontologies/2021/11/untitled-ontology-14#";
    private InfModel infModel;

    public MyReasoner(String owlFilePath, String ruleFilePath) {
        Model model = ModelFactory.createDefaultModel();
        InputStream open = FileManager.getInternal().open(owlFilePath);
        model.read(open, "");
        GenericRuleReasoner reasoner = new GenericRuleReasoner(Rule.rulesFromURL(ruleFilePath));
        infModel = ModelFactory.createInfModel(reasoner, model);
    }

    /**
     * 通过姓名和关系查询人员姓名
     * @param name      姓名
     * @param relation  关系
     */
    public void getNameByRelation(String name, String relation) {
        Resource A = infModel.getResource(prefix + name);
        Property property = infModel.getProperty(prefix + relation);
        StmtIterator stmtIterator = infModel.listStatements(A, property, (RDFNode) null);
        List<String> list = new ArrayList<String>();
        while (stmtIterator.hasNext()) {
            Resource resource = stmtIterator.nextStatement().getResource();
            list.add(resource.getProperty(new PropertyImpl(prefix + "Name")).getLiteral().toString());
        }
        printNameWithRelation(name, relation, list);
    }

    /**
     * 输出查询结果
     * @param name      姓名
     * @param relation  关系
     * @param list      结果数组
     */
    private void printNameWithRelation(String name, String relation, List<String> list) {
        System.out.print("| " + name + " | " + relation + " | ");
        int size = list.size();
        if(size > 0) {
            for(int i = 0; i < size; i++) {
                System.out.print(list.get(i) + " ");
            }
        }
        System.out.print(" | \n");
    }
}
