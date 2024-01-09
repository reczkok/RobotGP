package GP;

import Nodes.*;
import Nodes.Node;

public class NodeFactory {
    public static Node getRandomLiteralNode(Node parent) {
        int random = (int) (Math.random() * 4);
        return switch (random) {
            case 0 -> new IdNode(parent);
            case 1 -> new IntNode(parent);
            case 2 -> new FloatNode(parent);
            case 3 -> new BoolNode(parent);
            default -> throw new RuntimeException("Invalid random number");
        };
    }

    public static Node getNodeOfControlStructure(ControlStructures newControlStructure, Node parent) {
        return switch (newControlStructure) {
            case ASSIGNMENT -> new AssignmentNode(parent);
            case OUTPUT -> new OutputNode(parent);
            case PLUS -> new PlusNode(parent);
            case MINUS -> new MinusNode(parent);
            case DIVISION -> new DivisionNode(parent);
            case MULTIPLY -> new MultNode(parent);
            case MODULO -> new ModuloNode(parent);
            case INPUT -> new InputNode(parent);
            case MAIN -> new MainNode(parent);
            case IF -> new IfNode(parent);
            case FLOAT -> new FloatNode(parent);
            case ID -> new IdNode(parent);
            case LOOP -> new LoopNode(parent);
            case CONDITION -> new ConditionNode(parent);
            case AND -> new AndNode(parent);
            case OR -> new OrNode(parent);
            case NOT -> new NotNode(parent);
            case INT -> new IntNode(parent);
            case BOOL -> new BoolNode(parent);
            default -> throw new RuntimeException("Invalid control structure");
        };
    }
}
