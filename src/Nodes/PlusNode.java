package Nodes;

import java.util.*;

public class PlusNode extends TwoArgOpNode{
    public PlusNode(Node parent) {
        super(parent, ControlStructures.PLUS);
    }

    @Override
    public void printAtIndent(int indent) {
        System.out.print("(");
        if(this.left != null) {
            this.left.printAtIndent(indent);
        }
        System.out.print(" PLUS ");
        if(this.right != null) {
            this.right.printAtIndent(indent);
        }
        System.out.print(")");
    }
}
