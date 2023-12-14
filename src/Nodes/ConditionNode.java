package Nodes;

import java.util.*;

public class ConditionNode extends TwoArgOpNode{
    private ComparisonOperators operator;
    public ConditionNode(Node parent) {
        super(parent, ControlStructures.CONDITION);
    }

    @Override
    public void initializeRandom(int maxDepth){
        int random = (int) (Math.random() * 6);
        switch (random){
            case 0:
                this.operator = ComparisonOperators.EQUALS;
                break;
            case 1:
                this.operator = ComparisonOperators.GREATER_THAN;
                break;
            case 2:
                this.operator = ComparisonOperators.GREATER_THAN_OR_EQUAL;
                break;
            case 3:
                this.operator = ComparisonOperators.LESS_THAN;
                break;
            case 4:
                this.operator = ComparisonOperators.LESS_THAN_OR_EQUAL;
                break;
            case 5:
                this.operator = ComparisonOperators.NOT_EQUALS;
                break;
        }
        super.initializeRandom(maxDepth);
    }

    @Override
    public void printAtIndent(int indent) {
        if (this.left != null) {
            this.left.printAtIndent(indent);
        }
        switch (this.operator){
            case EQUALS:
                System.out.print(" == ");
                break;
            case GREATER_THAN:
                System.out.print(" > ");
                break;
            case GREATER_THAN_OR_EQUAL:
                System.out.print(" >= ");
                break;
            case LESS_THAN:
                System.out.print(" < ");
                break;
            case LESS_THAN_OR_EQUAL:
                System.out.print(" <= ");
                break;
            case NOT_EQUALS:
                System.out.print(" != ");
                break;
        }
        if (this.right != null) {
            this.right.printAtIndent(indent);
        }
    }
}
