package Nodes;

public class DivisionNode extends TwoArgOpNode{
    public DivisionNode(Node parent) {
        super(parent, ControlStructures.DIVISION);
    }

    @Override
    public void printAtIndent(int indent) {
        System.out.print("(");
        if(this.left != null) {
            this.left.printAtIndent(indent);
        }
        System.out.print(" / ");
        if(this.right != null) {
            this.right.printAtIndent(indent);
        }
        System.out.print(")");
    }
}
