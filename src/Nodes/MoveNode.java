package Nodes;

import java.util.ArrayList;
import java.util.List;

public class MoveNode implements Node{
    Node parent;
    ControlStructures controlStructure;
    Directions direction;
    public MoveNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.MOVE;
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
        int random = (int) (Math.random() * 8);
        switch (random) {
            case 0:
                this.direction = Directions.UP;
                break;
            case 1:
                this.direction = Directions.DOWN;
                break;
            case 2:
                this.direction = Directions.LEFT;
                break;
            case 3:
                this.direction = Directions.RIGHT;
                break;
            case 4:
                this.direction = Directions.TOP_RIGHT;
                break;
            case 5:
                this.direction = Directions.TOP_LEFT;
                break;
            case 6:
                this.direction = Directions.BOTTOM_RIGHT;
                break;
            case 7:
                this.direction = Directions.BOTTOM_LEFT;
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
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.print("move(" + this.direction + ");\n");
    }
}
