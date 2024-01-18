package Nodes;

import java.io.PrintWriter;
import java.util.*;

public class MinusNode extends TwoArgOpNode{
    public MinusNode(Node parent) {
        super(parent, ControlStructures.MINUS);
    }

    @Override
    public void printAtIndent(int indent) {
        System.out.print("(");
        if(this.left != null) {
            this.left.printAtIndent(indent);
        }
        System.out.print(" - ");
        if(this.right != null) {
            this.right.printAtIndent(indent);
        }
        System.out.print(")");
    }

    @Override
    public Node copy(Node parent) {
        MinusNode newNode = new MinusNode(parent);
        Node leftCopy = this.left.copy(newNode);
        Node rightCopy = this.right.copy(newNode);
        newNode.addChild(leftCopy);
        newNode.addChild(rightCopy);
        newNode.left = leftCopy;
        newNode.right = rightCopy;
        return newNode;
    }

    @Override
    public void printAtIndent(int i, PrintWriter printWriter) {
        printWriter.print("(");
        if(this.left != null) {
            this.left.printAtIndent(i, printWriter);
        }
        printWriter.print(" - ");
        if(this.right != null) {
            this.right.printAtIndent(i, printWriter);
        }
        printWriter.print(")");
    }

    @Override
    public void printAtIndent(int i, StringBuilder stringBuilder) {
        stringBuilder.append("(");
        if(this.left != null) {
            this.left.printAtIndent(i, stringBuilder);
        }
        stringBuilder.append(" - ");
        if(this.right != null) {
            this.right.printAtIndent(i, stringBuilder);
        }
        stringBuilder.append(")");
    }
}
