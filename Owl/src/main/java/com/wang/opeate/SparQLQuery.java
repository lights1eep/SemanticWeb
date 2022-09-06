package com.wang.opeate;

import com.wang.utils.DisplayResult;


/**
 * 显示SparQl查询
 */
public class SparQLQuery {
    public static void main(String[] args) {
        String rdfFileName = "Owl/src/main/resources/owl.owl";
        DisplayResult.displaySparQLResult(rdfFileName);
    }
}
