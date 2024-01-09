// Generated from C:/Users/User/IdeaProjects/RobotGP/RobotGP/src/Language/grammar_robot.g4 by ANTLR 4.13.1
package Interpreter.Language;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link grammar_robotParser}.
 */
public interface grammar_robotListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(grammar_robotParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(grammar_robotParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(grammar_robotParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(grammar_robotParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(grammar_robotParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(grammar_robotParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#input}.
	 * @param ctx the parse tree
	 */
	void enterInput(grammar_robotParser.InputContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#input}.
	 * @param ctx the parse tree
	 */
	void exitInput(grammar_robotParser.InputContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#output}.
	 * @param ctx the parse tree
	 */
	void enterOutput(grammar_robotParser.OutputContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#output}.
	 * @param ctx the parse tree
	 */
	void exitOutput(grammar_robotParser.OutputContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(grammar_robotParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(grammar_robotParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(grammar_robotParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(grammar_robotParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#checker}.
	 * @param ctx the parse tree
	 */
	void enterChecker(grammar_robotParser.CheckerContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#checker}.
	 * @param ctx the parse tree
	 */
	void exitChecker(grammar_robotParser.CheckerContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#else_block}.
	 * @param ctx the parse tree
	 */
	void enterElse_block(grammar_robotParser.Else_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#else_block}.
	 * @param ctx the parse tree
	 */
	void exitElse_block(grammar_robotParser.Else_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#conditions}.
	 * @param ctx the parse tree
	 */
	void enterConditions(grammar_robotParser.ConditionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#conditions}.
	 * @param ctx the parse tree
	 */
	void exitConditions(grammar_robotParser.ConditionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#logical_operator}.
	 * @param ctx the parse tree
	 */
	void enterLogical_operator(grammar_robotParser.Logical_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#logical_operator}.
	 * @param ctx the parse tree
	 */
	void exitLogical_operator(grammar_robotParser.Logical_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(grammar_robotParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(grammar_robotParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#comparison_operator}.
	 * @param ctx the parse tree
	 */
	void enterComparison_operator(grammar_robotParser.Comparison_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#comparison_operator}.
	 * @param ctx the parse tree
	 */
	void exitComparison_operator(grammar_robotParser.Comparison_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(grammar_robotParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(grammar_robotParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammar_robotParser#boolean}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(grammar_robotParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammar_robotParser#boolean}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(grammar_robotParser.BooleanContext ctx);
}