package Nodes;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public interface Node {
    Node getParent();
    ControlStructures getControlStructure();
    boolean isLiteral();
    void initializeRandom(int maxDepth);
    void addChild(Node child);
    List<Node> getChildren();
    void printAtIndent(int indent);
    void setDepth(int depth);
    int getDepth();
    Node copy(Node parent);
    void replaceChild(Node oldChild, Node newChild);
    List<ControlStructures> getLegalAlternatives(Node child, int depth);
    void printAtIndent(int i, PrintWriter printWriter);
    void printAtIndent(int i, StringBuilder stringBuilder);
    void setParent(Node parent);
}
