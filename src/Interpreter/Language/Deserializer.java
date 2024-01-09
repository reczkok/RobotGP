package Interpreter.Language;

import Nodes.Node;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;

public class Deserializer {
    public Deserializer() {
    }

    public static Node deserialize(String serialized) {
        File inputFile = new File(serialized);
        try {
            CharStream input = CharStreams.fromFileName(serialized);
            Lexer lexer = new grammar_robotLexer(input);
            TokenStream tokens = new CommonTokenStream(lexer);
            grammar_robotParser parser = new grammar_robotParser(tokens);
            ParseTree tree = parser.program();
            ProgramVisitor programVisitor = new ProgramVisitor();
            return programVisitor.visit(tree);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
