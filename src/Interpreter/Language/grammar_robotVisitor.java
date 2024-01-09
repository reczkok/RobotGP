// Generated from C:/Users/User/IdeaProjects/RobotGP/RobotGP/src/Language/grammar_robot.g4 by ANTLR 4.13.1
package Interpreter.Language;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link grammar_robotParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface grammar_robotVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(grammar_robotParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(grammar_robotParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(grammar_robotParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInput(grammar_robotParser.InputContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#output}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutput(grammar_robotParser.OutputContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(grammar_robotParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(grammar_robotParser.LoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#checker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChecker(grammar_robotParser.CheckerContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#else_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_block(grammar_robotParser.Else_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#conditions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditions(grammar_robotParser.ConditionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#logical_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_operator(grammar_robotParser.Logical_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(grammar_robotParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#comparison_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison_operator(grammar_robotParser.Comparison_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(grammar_robotParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammar_robotParser#boolean}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean(grammar_robotParser.BooleanContext ctx);
}