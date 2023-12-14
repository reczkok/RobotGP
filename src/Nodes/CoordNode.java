package Nodes;

import java.util.ArrayList;
import java.util.List;

public class CoordNode implements Node{
    Node parent;
    ControlStructures controlStructure;
    CommandTargets target;
    private int depth;

    public CoordNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.GET_COORDINATE;
    }

    @Override
    public Node getParent() {
        return this.parent;
    }

    @Override
    public ControlStructures getControlStructure() {
        return this.controlStructure;
    }

    @Override
    public List<Node> getChildrenByControlStructure(ControlStructures controlStructure) {
        return null;
    }

    @Override
    public boolean isLiteral() {
        return true;
    }

    @Override
    public void initializeRandom(int maxDepth) {
        int random = (int) (Math.random() * 4);
        switch (random) {
            case 0:
                this.target = CommandTargets.BOX_Y;
                break;
            case 1:
                this.target = CommandTargets.BOX_X;
                break;
            case 2:
                this.target = CommandTargets.SELF_Y;
                break;
            case 3:
                this.target = CommandTargets.SELF_X;
                break;
        }
    }

    @Override
    public void addChild(Node child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Node> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public void printAtIndent(int indent) {
        System.out.print("getCoord(" + this.target + ")");
    }

    @Override
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public int getDepth() {
        return this.depth;
    }

    @Override
    public Node copy(Node parent) {
        CoordNode copy = new CoordNode(parent);
        copy.target = this.target;
        return copy;
    }
}
