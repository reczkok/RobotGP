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
        System.out.print(" + ");
        if(this.right != null) {
            this.right.printAtIndent(indent);
        }
        System.out.print(")");
    }

    @Override
    public Node copy(Node parent) {
        PlusNode newNode = new PlusNode(parent);
        Node leftCopy = this.left.copy(newNode);
        Node rightCopy = this.right.copy(newNode);
        newNode.addChild(leftCopy);
        newNode.addChild(rightCopy);
        newNode.left = leftCopy;
        newNode.right = rightCopy;
        return newNode;
    }
}
