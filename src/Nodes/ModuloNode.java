package Nodes;

public class ModuloNode extends TwoArgOpNode {
    public ModuloNode(Node parent) {
        super(parent, ControlStructures.MODULO);
    }

    @Override
    public void printAtIndent(int indent) {
        System.out.print("(");
        if(this.left != null) {
            this.left.printAtIndent(indent);
        }
        System.out.print(" % ");
        if(this.right != null) {
            this.right.printAtIndent(indent);
        }
        System.out.print(")");
    }
}
