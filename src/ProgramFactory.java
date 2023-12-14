import Nodes.ControlStructures;
import Nodes.MainNode;
import Nodes.Node;

public class ProgramFactory {
    public static Program generateRandomProgram() {
        MainNode root = new MainNode(null);
        Program program = new Program(root);
        program.initializeRandom(Parameters.maxInitialDepth);
        return program;
    }
}
