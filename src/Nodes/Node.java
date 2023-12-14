package Nodes;

import java.util.ArrayList;
import java.util.List;

public interface Node {
    Node getParent();
    ControlStructures getControlStructure();
    List<Node> getChildrenByControlStructure(ControlStructures controlStructure);
    boolean isLiteral();
    void initializeRandom(int maxDepth);
    void addChild(Node child);
    List<Node> getChildren();
    void printAtIndent(int indent);
    void setDepth(int depth);
    int getDepth();
    Node copy(Node parent);
}
