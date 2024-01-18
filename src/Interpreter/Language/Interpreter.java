package Interpreter.Language;

import GP.Program;
import GP.ProgramVariable;
import Nodes.NodeParameters;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

public class Interpreter {
    public static double runProgram(Program program, int maxSteps, Evaluator evaluator) {
        CharStream input = CharStreams.fromString(program.toString());
        Lexer lexer = new grammar_robotLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        grammar_robotParser parser = new grammar_robotParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                                    int charPositionInLine, String msg, RecognitionException e) {
                System.out.println("Syntax error at line " + line + ":" + charPositionInLine + " " + msg);
            }
        });
        ParseTree tree = parser.program();
        ProgramVariable[] variables = new ProgramVariable[NodeParameters.varAmount];
        for (int i = 0; i < NodeParameters.varAmount; i++) {
            variables[i] = new ProgramVariable();
        }
        ProgramRunner programRunner = new ProgramRunner(maxSteps, evaluator, variables);
        double fitness = programRunner.getProgramFitness(tree);
        program.setFitness(fitness);
        return fitness;
    }

    public static ProgramOutput getProgramOutputs(Program program, int maxSteps, Evaluator evaluator) {
        CharStream input = CharStreams.fromString(program.toString());
        Lexer lexer = new grammar_robotLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        grammar_robotParser parser = new grammar_robotParser(tokens);
        ParseTree tree = parser.program();
        ProgramVariable[] variables = new ProgramVariable[NodeParameters.varAmount];
        for (int i = 0; i < NodeParameters.varAmount; i++) {
            variables[i] = new ProgramVariable();
        }
        ProgramRunner programRunner = new ProgramRunner(maxSteps, evaluator, variables);
        double fitness = programRunner.getProgramFitness(tree);
        return new ProgramOutput(programRunner.allOutputs, programRunner.allInputs);
    }
}
