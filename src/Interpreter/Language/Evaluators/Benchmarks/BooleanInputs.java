package Interpreter.Language.Evaluators.Benchmarks;

import Interpreter.Language.ExpressionResult;

import java.util.ArrayList;
import java.util.List;

public class BooleanInputs {
    public static List<List<ExpressionResult>> inputs;

    public static void generateInputs(int k){
        List<List<ExpressionResult>> inputs = new ArrayList<>();
        int iterations = (int)Math.pow(2, k);
        for(int i = 0; i < iterations; i++){
            List<ExpressionResult> row = new ArrayList<>();
            String binary = Integer.toBinaryString(i);
            while(binary.length() < k){
                binary = "0" + binary;
            }
            for(int j = 0; j < binary.length(); j++){
                if(binary.charAt(j) == '0'){
                    row.add(new ExpressionResult(false));
                } else {
                    row.add(new ExpressionResult(true));
                }
            }
            // add random value for output
            row.add(new ExpressionResult(Math.random() < 0.5));
            inputs.add(row);
        }
        BooleanInputs.inputs = inputs;
    }

    public static void printBoolTable(){
        for(List<ExpressionResult> row : inputs){
            for(ExpressionResult result : row){
                System.out.print(result + " ");
            }
            System.out.println();
        }
    }
}
