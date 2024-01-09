package Interpreter.Language;

import Nodes.*;
import GP.*;

public class ProgramVisitor extends grammar_robotBaseVisitor<Node>{

    @Override
    public Node visitProgram(grammar_robotParser.ProgramContext ctx) {
        return visit(ctx.main());
    }

    @Override
    public Node visitMain(grammar_robotParser.MainContext ctx) {
        Node main = new MainNode(null);
        ctx.definition().forEach(definition -> {
            Node definitionNode = visit(definition);
            definitionNode.setParent(main);
            main.addChild(definitionNode);
        });
        return main;
    }

    @Override
    public Node visitDefinition(grammar_robotParser.DefinitionContext ctx) {
        if(ctx.assignment() != null){
            return visit(ctx.assignment());
        } else if(ctx.loop() != null){
            return visit(ctx.loop());
        } else if(ctx.checker() != null){
            return visit(ctx.checker());
        } else if(ctx.output() != null){
            return visit(ctx.output());
        } else {
            return null;
        }
    }

    @Override
    public Node visitAssignment(grammar_robotParser.AssignmentContext ctx) {
        String id = ctx.ID().getText();
        Node idNode = new IdNode(null, id);
        Node exprNode = visit(ctx.expr());
        AssignmentNode assignmentNode = new AssignmentNode(null, idNode, exprNode);
        idNode.setParent(assignmentNode);
        exprNode.setParent(assignmentNode);
        return assignmentNode;
    }

    @Override
    public Node visitExpr(grammar_robotParser.ExprContext ctx) {
        if(ctx.ID() != null){
            String id = ctx.ID().getText();
            return new IdNode(null, id);
        } else if(ctx.INT() != null){
            String intAdd = ctx.INT().getText();
            return new IntNode(null, intAdd);
        } else if(ctx.FLOAT() != null){
            String floatAdd = ctx.FLOAT().getText();
            return new FloatNode(null, floatAdd);
        } else if(ctx.boolean_() != null){
            if(ctx.boolean_().FALSE() != null){
                return new BoolNode(null, false);
            } else {
                return new BoolNode(null, true);
            }
        } else if(ctx.input() != null){
            return new InputNode(null);
        } else if(ctx.MINUS() != null){
            Node expr1 = visit(ctx.expr(0));
            Node expr2 = visit(ctx.expr(1));
            MinusNode minusNode = new MinusNode(null);
            minusNode.setLeft(expr1);
            minusNode.setRight(expr2);
            expr1.setParent(minusNode);
            expr2.setParent(minusNode);
            return minusNode;
        } else if(ctx.PLUS() != null){
            Node expr1 = visit(ctx.expr(0));
            Node expr2 = visit(ctx.expr(1));
            PlusNode plusNode = new PlusNode(null);
            plusNode.setLeft(expr1);
            plusNode.setRight(expr2);
            expr1.setParent(plusNode);
            expr2.setParent(plusNode);
            return plusNode;
        } else if(ctx.MULTIPLY() != null){
            Node expr1 = visit(ctx.expr(0));
            Node expr2 = visit(ctx.expr(1));
            MultNode multNode = new MultNode(null);
            multNode.setLeft(expr1);
            multNode.setRight(expr2);
            expr1.setParent(multNode);
            expr2.setParent(multNode);
            return multNode;
        } else if(ctx.DIVISION() != null) {
            Node expr1 = visit(ctx.expr(0));
            Node expr2 = visit(ctx.expr(1));
            DivisionNode divisionNode = new DivisionNode(null);
            divisionNode.setLeft(expr1);
            divisionNode.setRight(expr2);
            expr1.setParent(divisionNode);
            expr2.setParent(divisionNode);
            return divisionNode;
        } else if(ctx.MODULO() != null) {
            Node expr1 = visit(ctx.expr(0));
            Node expr2 = visit(ctx.expr(1));
            ModuloNode moduloNode = new ModuloNode(null);
            moduloNode.setLeft(expr1);
            moduloNode.setRight(expr2);
            expr1.setParent(moduloNode);
            expr2.setParent(moduloNode);
            return moduloNode;
        } else if(ctx.expr().size() == 1){
            return visit(ctx.expr(0));
        } else {
            return null;
        }
    }

    @Override
    public Node visitLoop(grammar_robotParser.LoopContext ctx) {
        Node condition = visit(ctx.conditions());
        LoopNode loopNode = new LoopNode(null, condition);
        condition.setParent(loopNode);
        ctx.definition().forEach(definition -> {
            Node definitionNode = visit(definition);
            definitionNode.setParent(loopNode);
            loopNode.addToBody(definitionNode);
        });
        return loopNode;
    }

    @Override
    public Node visitChecker(grammar_robotParser.CheckerContext ctx) {
        Node condition = visit(ctx.conditions());
        IfNode ifNode = new IfNode(null, condition);
        condition.setParent(ifNode);
        ctx.definition().forEach(definition -> {
            Node definitionNode = visit(definition);
            definitionNode.setParent(ifNode);
            ifNode.addToBody(definitionNode);
        });
        if(ctx.else_block() != null){
            ctx.else_block().definition().forEach(definition -> {
                Node definitionNode = visit(definition);
                definitionNode.setParent(ifNode);
                ifNode.addToElseBody(definitionNode);
            });
        }
        return ifNode;
    }

    @Override
    public Node visitOutput(grammar_robotParser.OutputContext ctx) {
        Node expr = visit(ctx.expr());
        OutputNode outputNode = new OutputNode(null, expr);
        expr.setParent(outputNode);
        return outputNode;
    }


    @Override
    public Node visitConditions(grammar_robotParser.ConditionsContext ctx) {
        if(ctx.NOT() != null){
            Node condition = visit(ctx.conditions(0));
            NotNode notNode = new NotNode(null, condition);
            condition.setParent(notNode);
            return notNode;
        } else if(ctx.logical_operator() != null){
            Node condition1 = visit(ctx.conditions(0));
            Node condition2 = visit(ctx.conditions(1));
            if(ctx.logical_operator().AND() != null){
                AndNode andNode = new AndNode(null, condition1, condition2);
                condition1.setParent(andNode);
                condition2.setParent(andNode);
                return andNode;
            } else {
                OrNode orNode = new OrNode(null, condition1, condition2);
                condition1.setParent(orNode);
                condition2.setParent(orNode);
                return orNode;
            }
        } else if(ctx.condition() != null){
            return visit(ctx.condition());
        } else {
            return null;
        }
    }

    @Override
    public Node visitCondition(grammar_robotParser.ConditionContext ctx) {
        ComparisonOperators operator;
        if(ctx.comparison_operator().EQ() != null){
            operator = ComparisonOperators.EQUALS;
        } else if(ctx.comparison_operator().GT() != null){
            operator = ComparisonOperators.GREATER_THAN;
        } else if(ctx.comparison_operator().GE() != null){
            operator = ComparisonOperators.GREATER_THAN_OR_EQUAL;
        } else if(ctx.comparison_operator().LT() != null){
            operator = ComparisonOperators.LESS_THAN;
        } else if(ctx.comparison_operator().LE() != null){
            operator = ComparisonOperators.LESS_THAN_OR_EQUAL;
        } else {
            operator = ComparisonOperators.NOT_EQUALS;
        }
        Node expr1 = visit(ctx.expr(0));
        Node expr2 = visit(ctx.expr(1));
        ConditionNode conditionNode = new ConditionNode(null, operator);
        conditionNode.setLeft(expr1);
        conditionNode.setRight(expr2);
        expr1.setParent(conditionNode);
        expr2.setParent(conditionNode);
        return conditionNode;
    }
}
