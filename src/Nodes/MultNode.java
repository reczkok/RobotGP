package Nodes;

public class MultNode extends TwoArgOpNode{
    public MultNode(Node parent) {
        super(parent, ControlStructures.MULTIPLY);
    }

    @Override
    public void printAtIndent(int indent) {
        System.out.print("(");
        if(this.left != null) {
            this.left.printAtIndent(indent);
        }
        System.out.print(" * ");
        if(this.right != null) {
            this.right.printAtIndent(indent);
        }
        System.out.print(")");
    }
}
