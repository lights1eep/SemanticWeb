package com.wang.opeate;

import com.wang.utils.DisplayResult;

/**
 *  推理查询
 */
public class ReasonerQuery {
    public static void main(String[] args) {
        String owlFilePath = "Owl/src/main/resources/owl.owl";
        String ruleFilePath = "Owl/src/main/resources/MyRule.rule";
        DisplayResult.displayReasonerResult(owlFilePath, ruleFilePath);
    }
}
