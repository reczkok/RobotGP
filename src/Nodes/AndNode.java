package Nodes;

public class AndNode extends TwoArgOpNode{
    public AndNode(Node parent) {
        super(parent, ControlStructures.AND);
    }

    @Override
    public void printAtIndent(int indent) {
        System.out.print("(");
        if(this.left != null) {
            this.left.printAtIndent(indent);
        }
        System.out.print(" AND ");
        if(this.right != null) {
            this.right.printAtIndent(indent);
        }
        System.out.print(")");
    }
}
