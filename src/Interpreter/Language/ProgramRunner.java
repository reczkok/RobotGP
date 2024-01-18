package Interpreter.Language;

import GP.LastAccessed;
import GP.ProgramVariable;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class ProgramRunner extends grammar_robotBaseVisitor<ExpressionResult>{
    int remainingSteps;
    Evaluator evaluator;
    ProgramVariable[] variables;
    List<ExpressionResult> outputs;
    List<List<ExpressionResult>> allOutputs;
    List<ExpressionResult> inputs;
    List<List<ExpressionResult>> allInputs;

    public ProgramRunner(int maxSteps, Evaluator evaluator, ProgramVariable[] variables){
        this.remainingSteps = maxSteps;
        this.evaluator = evaluator;
        this.variables = variables;
        this.outputs = new ArrayList<>();
        this.allOutputs = new ArrayList<>();
        this.inputs = new ArrayList<>();
        this.allInputs = new ArrayList<>();
    }

    public double getProgramFitness(ParseTree tree){
        double totalFitness = 0;
        while (evaluator.requiresNext()){
            runProgram(tree);
            double iterFitness = evaluator.evaluate(outputs);
            if (iterFitness == Double.POSITIVE_INFINITY) {
                return iterFitness;
            }
            totalFitness += iterFitness;
            resetVariables();
            allOutputs.add(new ArrayList<>());
            for (ExpressionResult output : outputs) {
                allOutputs.get(allOutputs.size() - 1).add(output);
            }
            allInputs.add(new ArrayList<>());
            for (ExpressionResult input : inputs) {
                allInputs.get(allInputs.size() - 1).add(input);
            }
            outputs.clear();
            inputs.clear();
        }
        return totalFitness;
    }

    private void resetVariables(){
        for(ProgramVariable variable : variables){
            variable.reset();
        }
    }

    public void runProgram(ParseTree tree){
        visit(tree);
    }

    @Override
    public ExpressionResult visitProgram(grammar_robotParser.ProgramContext ctx) {
        return visit(ctx.main());
    }

    @Override
    public ExpressionResult visitMain(grammar_robotParser.MainContext ctx) {
        List<grammar_robotParser.DefinitionContext> definitions = ctx.definition();
        if(!definitions.isEmpty()){
            definitions.forEach(this::visit);
        }
        return new ExpressionResult();
    }

    @Override
    public ExpressionResult visitDefinition(grammar_robotParser.DefinitionContext ctx) {
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
    public ExpressionResult visitAssignment(grammar_robotParser.AssignmentContext ctx) {
        remainingSteps--;
        if(remainingSteps <= 0){
            return new ExpressionResult();
        }
        String id = ctx.ID().getText();
        int idIndex = Integer.parseInt(id.substring(1));
        ExpressionResult expr = visit(ctx.expr());
        switch (expr.getType()){
            case Integer -> variables[idIndex].setIntValue(expr.intValue);
            case Float -> variables[idIndex].setFloatValue(expr.floatValue);
            case Boolean -> variables[idIndex].setBoolValue(expr.boolValue);
            case Null -> {
                return new ExpressionResult();
            }
        }
        return new ExpressionResult();
    }

    @Override
    public ExpressionResult visitExpr(grammar_robotParser.ExprContext ctx) {
        remainingSteps--;
        if(remainingSteps <= 0){
            return new ExpressionResult(0);
        }
        if(ctx.ID() != null){
            String id = ctx.ID().getText();
            int idIndex = Integer.parseInt(id.substring(1));
            LastAccessed lastAccessed = variables[Integer.parseInt(id.substring(1))].getLastAccessed();
            switch (lastAccessed) {
                case Integer -> {
                    return new ExpressionResult(variables[idIndex].getIntValue());
                }
                case Float -> {
                    return new ExpressionResult(variables[idIndex].getFloatValue());
                }
                case Boolean -> {
                    return new ExpressionResult(variables[idIndex].getBoolValue());
                }
            }
        } else if(ctx.INT() != null){
            String intAdd = ctx.INT().getText();
            return new ExpressionResult(Integer.parseInt(intAdd));
        } else if(ctx.FLOAT() != null){
            String floatAdd = ctx.FLOAT().getText();
            return new ExpressionResult(Float.parseFloat(floatAdd));
        } else if(ctx.boolean_() != null){
            if(ctx.boolean_().FALSE() != null){
                return new ExpressionResult(false);
            } else {
                return new ExpressionResult(true);
            }
        } else if(ctx.input() != null){
            ExpressionResult input = evaluator.getNextInput();
            if(input.getType() == ExpressionFlags.Null){
                //remainingSteps = 0;
                return new ExpressionResult(0);
            }
            inputs.add(input);
            return input;
        } else if(ctx.MINUS() != null){
            ExpressionResult expr1 = visit(ctx.expr(0));
            ExpressionResult expr2 = visit(ctx.expr(1));
            return ExpressionResult.subtract(expr1, expr2);
        } else if(ctx.PLUS() != null){
            ExpressionResult expr1 = visit(ctx.expr(0));
            ExpressionResult expr2 = visit(ctx.expr(1));
            return ExpressionResult.add(expr1, expr2);
        } else if(ctx.MULTIPLY() != null){
            ExpressionResult expr1 = visit(ctx.expr(0));
            ExpressionResult expr2 = visit(ctx.expr(1));
            return ExpressionResult.multiply(expr1, expr2);
        } else if(ctx.DIVISION() != null) {
            ExpressionResult expr1 = visit(ctx.expr(0));
            ExpressionResult expr2 = visit(ctx.expr(1));
            return ExpressionResult.divide(expr1, expr2);
        } else if(ctx.MODULO() != null) {
            ExpressionResult expr1 = visit(ctx.expr(0));
            ExpressionResult expr2 = visit(ctx.expr(1));
            return ExpressionResult.modulo(expr1, expr2);
        } else if(ctx.expr().size() == 1){
            remainingSteps++;
            return visit(ctx.expr(0));
        } else {
            return null;
        }
        return new ExpressionResult();
    }

    @Override
    public ExpressionResult visitLoop(grammar_robotParser.LoopContext ctx) {
        remainingSteps--;
        if(remainingSteps <= 0){
            return new ExpressionResult();
        }
        ExpressionResult condition = visit(ctx.conditions());
        while (condition.getType() == ExpressionFlags.Boolean && condition.boolValue && remainingSteps > 0){
            ctx.definition().forEach(this::visit);
            condition = visit(ctx.conditions());
        }
        return new ExpressionResult();
    }

    @Override
    public ExpressionResult visitChecker(grammar_robotParser.CheckerContext ctx) {
        remainingSteps--;
        if(remainingSteps <= 0){
            return new ExpressionResult();
        }
        ExpressionResult condition = visit(ctx.conditions());
        if(condition.getType() == ExpressionFlags.Boolean && condition.boolValue){
            ctx.definition().forEach(this::visit);
        }
        return new ExpressionResult();
    }

    @Override
    public ExpressionResult visitOutput(grammar_robotParser.OutputContext ctx) {
        remainingSteps--;
        if(remainingSteps <= 0){
            return new ExpressionResult();
        }
        ExpressionResult expr = visit(ctx.expr());
        outputs.add(expr);
        return new ExpressionResult();
    }


    @Override
    public ExpressionResult visitConditions(grammar_robotParser.ConditionsContext ctx) {
        remainingSteps--;
        if(remainingSteps <= 0){
            return new ExpressionResult(false);
        }
        if(ctx.NOT() != null){
            ExpressionResult condition = visit(ctx.conditions(0));
            if(condition.getType() == ExpressionFlags.Boolean){
                return new ExpressionResult(!condition.boolValue);
            } else {
                return new ExpressionResult(false);
            }
        } else if(ctx.logical_operator() != null){
            ExpressionResult condition1 = visit(ctx.conditions(0));
            ExpressionResult condition2 = visit(ctx.conditions(1));
            if(ctx.logical_operator().AND() != null){
                if (condition1.getType() == ExpressionFlags.Boolean && condition2.getType() == ExpressionFlags.Boolean) {
                    return new ExpressionResult(condition1.boolValue && condition2.boolValue);
                } else {
                    return new ExpressionResult(false);
                }
            } else {
                if (condition1.getType() == ExpressionFlags.Boolean && condition2.getType() == ExpressionFlags.Boolean) {
                    return new ExpressionResult(condition1.boolValue || condition2.boolValue);
                } else {
                    return new ExpressionResult(false);
                }
            }
        } else if(ctx.condition() != null){
            return visit(ctx.condition());
        } else {
            return null;
        }
    }

    @Override
    public ExpressionResult visitCondition(grammar_robotParser.ConditionContext ctx) {
        ExpressionResult expr1 = visit(ctx.expr(0));
        ExpressionResult expr2 = visit(ctx.expr(1));
        if(ctx.comparison_operator().EQ() != null){
            return ExpressionResult.equals(expr1, expr2);
        } else if(ctx.comparison_operator().GT() != null){
            return ExpressionResult.greaterThan(expr1, expr2);
        } else if(ctx.comparison_operator().GE() != null){
            return ExpressionResult.greaterThanOrEqual(expr1, expr2);
        } else if(ctx.comparison_operator().LT() != null){
            return ExpressionResult.lessThan(expr1, expr2);
        } else if(ctx.comparison_operator().LE() != null){
            return ExpressionResult.lessThanOrEqual(expr1, expr2);
        } else {
            return ExpressionResult.notEquals(expr1, expr2);
        }
    }
}
