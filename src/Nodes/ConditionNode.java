package Nodes;

import java.io.PrintWriter;
import java.util.*;

public class ConditionNode extends TwoArgOpNode{
    private ComparisonOperators operator;
    public ConditionNode(Node parent) {
        super(parent, ControlStructures.CONDITION);
    }

    public ConditionNode(Node parent, ComparisonOperators operator){
        super(parent, ControlStructures.CONDITION);
        this.operator = operator;
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

    @Override
    public Node copy(Node parent) {
        ConditionNode newNode = new ConditionNode(parent);
        Node leftCopy = this.left.copy(newNode);
        Node rightCopy = this.right.copy(newNode);
        newNode.addChild(leftCopy);
        newNode.addChild(rightCopy);
        newNode.left = leftCopy;
        newNode.right = rightCopy;
        newNode.operator = this.operator;
        return newNode;
    }

    @Override
    public void printAtIndent(int i, PrintWriter printWriter) {
        if (this.left != null) {
            this.left.printAtIndent(i, printWriter);
        }
        switch (this.operator){
            case EQUALS:
                printWriter.print(" == ");
                break;
            case GREATER_THAN:
                printWriter.print(" > ");
                break;
            case GREATER_THAN_OR_EQUAL:
                printWriter.print(" >= ");
                break;
            case LESS_THAN:
                printWriter.print(" < ");
                break;
            case LESS_THAN_OR_EQUAL:
                printWriter.print(" <= ");
                break;
            case NOT_EQUALS:
                printWriter.print(" != ");
                break;
        }
        if (this.right != null) {
            this.right.printAtIndent(i, printWriter);
        }
    }

    @Override
    public void printAtIndent(int i, StringBuilder stringBuilder) {
        if (this.left != null) {
            this.left.printAtIndent(i, stringBuilder);
        }
        switch (this.operator){
            case EQUALS:
                stringBuilder.append(" == ");
                break;
            case GREATER_THAN:
                stringBuilder.append(" > ");
                break;
            case GREATER_THAN_OR_EQUAL:
                stringBuilder.append(" >= ");
                break;
            case LESS_THAN:
                stringBuilder.append(" < ");
                break;
            case LESS_THAN_OR_EQUAL:
                stringBuilder.append(" <= ");
                break;
            case NOT_EQUALS:
                stringBuilder.append(" != ");
                break;
        }
        if (this.right != null) {
            this.right.printAtIndent(i, stringBuilder);
        }
    }
}
