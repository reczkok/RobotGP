package Nodes;

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
}
