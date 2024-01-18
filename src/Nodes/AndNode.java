package Nodes;

import java.io.PrintWriter;

public class AndNode extends LogicalComparison{
    public AndNode(Node parent) {
        super(parent, ControlStructures.AND);
    }
    public AndNode(Node parent, Node left, Node right){
        super(parent, ControlStructures.AND, left, right);
    }

    @Override
    public void printAtIndent(int indent) {
        System.out.print("(");
        if(this.left != null) {
            this.left.printAtIndent(indent);
        }
        System.out.print(")");
        System.out.print(" AND ");
        System.out.print("(");
        if(this.right != null) {
            this.right.printAtIndent(indent);
        }
        System.out.print(")");
    }

    @Override
    public Node copy(Node parent) {
        AndNode newNode = new AndNode(parent);
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
        printWriter.print(")");
        printWriter.print(" AND ");
        printWriter.print("(");
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
        stringBuilder.append(")");
        stringBuilder.append(" AND ");
        stringBuilder.append("(");
        if(this.right != null) {
            this.right.printAtIndent(i, stringBuilder);
        }
        stringBuilder.append(")");
    }
}
