import Nodes.ControlStructures;
import Nodes.Node;

public class ProgramFactory {
    public static Program generateRandomProgram() {
        Node root = new Node(null, null, ControlStructures.MAIN);
        Program program = new Program(root);
        return program;
    }
}
