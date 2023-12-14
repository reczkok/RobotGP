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

    @Override
    public Node copy(Node parent) {
        OrNode newNode = new OrNode(parent);
        Node leftCopy = this.left.copy(newNode);
        Node rightCopy = this.right.copy(newNode);
        newNode.addChild(leftCopy);
        newNode.addChild(rightCopy);
        newNode.left = leftCopy;
        newNode.right = rightCopy;
        return newNode;
    }
}
