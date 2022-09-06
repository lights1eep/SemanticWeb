package com.wang;

import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.FileManager;
import org.junit.Test;

import java.io.InputStream;

public class Demo {
    @Test
    public void test1() {
        String fileName = "E:/File/XML/RDF/owl.owl";
        String s = "http://www.semanticweb.org/10265/ontologies/2021/11/untitled-ontology-14#";
        Model model = ModelFactory.createDefaultModel();
        InputStream open = FileManager.getInternal().open(fileName);
        model.read(open, "");
        GenericRuleReasoner reasoner = new GenericRuleReasoner(Rule.rulesFromURL("src/main/resources/MyRule.rule"));
        InfModel infModel = ModelFactory.createInfModel(reasoner, model);
        Resource A = infModel.getResource(s + "Mary");
        Property property = infModel.getProperty(s + "isBrotherOrSisterOf");
        StmtIterator stmtIterator = infModel.listStatements(A, property, (RDFNode) null);
        while (stmtIterator.hasNext()) {
            Resource resource = stmtIterator.nextStatement().getResource();
            System.out.println(resource);
        }


    }
}
