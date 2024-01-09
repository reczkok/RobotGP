package GP;

import Nodes.MainNode;

public class ProgramFactory {
    public static Program generateRandomProgram() {
        MainNode root = new MainNode(null);
        Program program = new Program(root);
        program.initializeRandom(Parameters.maxInitialDepth, Parameters.minInitialNodes);
        return program;
    }

    public static Program copyProgram(Program program) {
        MainNode rootCopy = (MainNode) program.getRoot().copy(null);
        Program newProgram = new Program(rootCopy);
        newProgram.updateTreeInfo();
        return newProgram;
    }
}
