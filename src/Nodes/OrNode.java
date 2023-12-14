package Nodes;

public class OrNode extends TwoArgOpNode{
    public OrNode(Node parent) {
        super(parent, ControlStructures.OR);
    }

    @Override
    public void printAtIndent(int indent) {
        System.out.print("(");
        if(this.left != null) {
            this.left.printAtIndent(indent);
        }
        System.out.print(" OR ");
        if(this.right != null) {
            this.right.printAtIndent(indent);
        }
        System.out.print(")");
    }
}
