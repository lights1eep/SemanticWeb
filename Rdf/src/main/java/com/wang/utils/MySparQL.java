package com.wang.utils;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *  自定义SparQL查询
 */
public class MySparQL {
    private Model model;

    public MySparQL(String rdfFilePath) {
        model = ModelFactory.createDefaultModel();
        InputStream open = FileManager.getInternal().open(rdfFilePath);
        model.read(open, "");
    }

    /**
     * 查询
     * @param queryString   查询语句
     */
    public void mySparQLQuery(String queryString) {
        try  {
            Query query = QueryFactory.create(queryString);
            QueryExecution qexec = QueryExecutionFactory.create(query, model);
            ResultSet results = qexec.execSelect() ;
            if(results.getResultVars().size() != 0) {
                List<String> args = getArgs(queryString);
                displayQueryResult(results, args);
            } else {
                System.out.println("empty");
            }
        } catch (QueryParseException e1) {
            System.out.println("语句错误!");
        } catch (Exception e2) {
            System.out.println("错误!");
        }
    }

    /**
     * 从查询语句中分离参数
     * @param s     查询语句
     * @return      参数列表
     */
    private List<String> getArgs(String s) {
        List<String> list = new ArrayList<>();
        int n = s.length(), i = 0;
        while(i < n) {
            char c = s.charAt(i);
            if(c == '?') {
                StringBuffer sb = new StringBuffer();
                while(i < n && s.charAt(i) != ' ') {
                    sb.append(s.charAt(i));
                    i++;
                }
                list.add(sb.toString());
            } else if(c == '{') {
                break;
            } else {
                i++;
            }
        }
        return list;
    }

    /**
     * 显示查询结果
     * @param resultSet     查询结果
     * @param args          参数列表
     */
    private void displayQueryResult(ResultSet resultSet, List<String> args) {
        int n = args.size();
        System.out.print("| ");
        for(int i = 0; i < n; i++) {
            System.out.print(args.get(i));
            System.out.print(" |");
        }
        System.out.println();

        for ( ; resultSet.hasNext() ; )
        {
            QuerySolution soln = resultSet.nextSolution();

            System.out.print("| ");
            for(int i = 0; i < n; i++) {
                System.out.print(soln.get(args.get(i)));
                System.out.print(" |");
            }
            System.out.println();
        }
    }
}
