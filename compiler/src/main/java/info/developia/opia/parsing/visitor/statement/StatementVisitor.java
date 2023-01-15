package info.developia.opia.parsing.visitor.statement;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser;
import info.developia.opia.OpiaParser.*;
import info.developia.opia.domain.node.expression.ConditionalExpression;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.node.statement.Statement;
import info.developia.opia.domain.scope.Scope;
import info.developia.opia.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class StatementVisitor extends OpiaBaseVisitor<Statement> {

    private final ExpressionVisitor expressionVisitor;
    private final PrintStatementVisitor printStatementVisitor;
    private final VariableDeclarationStatementVisitor variableDeclarationStatementVisitor;
    private final ReturnStatementVisitor returnStatementVisitor;
    private final BlockStatementVisitor blockStatementVisitor;
    private final IfStatementVisitor ifStatementVisitor;
    private final ForStatementVisitor forStatementVisitor;
    private final AssignmentStatementVisitor assignmentStatementVisitor;

    public StatementVisitor(Scope scope) {
        expressionVisitor = new ExpressionVisitor(scope);
        printStatementVisitor = new PrintStatementVisitor(expressionVisitor);
        variableDeclarationStatementVisitor = new VariableDeclarationStatementVisitor(expressionVisitor, scope);
        returnStatementVisitor = new ReturnStatementVisitor(expressionVisitor);
        blockStatementVisitor = new BlockStatementVisitor(scope);
        ifStatementVisitor = new IfStatementVisitor(this, expressionVisitor);
        forStatementVisitor = new ForStatementVisitor(scope);
        assignmentStatementVisitor = new AssignmentStatementVisitor(expressionVisitor);
    }

    @Override
    public Statement visitPrintStatement(@NotNull PrintStatementContext ctx) {
        return printStatementVisitor.visitPrintStatement(ctx);
    }

    @Override
    public Statement visitVariableDeclaration(@NotNull VariableDeclarationContext ctx) {
        return variableDeclarationStatementVisitor.visitVariableDeclaration(ctx);
    }

    @Override
    public Statement visitReturnVoid(@NotNull ReturnVoidContext ctx) {
        return returnStatementVisitor.visitReturnVoid(ctx);
    }

    @Override
    public Statement visitReturnWithValue(@NotNull ReturnWithValueContext ctx) {
        return returnStatementVisitor.visitReturnWithValue(ctx);
    }

    @Override
    public Statement visitBlock(@NotNull BlockContext ctx) {
        return blockStatementVisitor.visitBlock(ctx);
    }

    @Override
    public Statement visitIfStatement(@NotNull IfStatementContext ctx) {
        return ifStatementVisitor.visitIfStatement(ctx);
    }

    @Override
    public Expression visitVarReference(@NotNull VarReferenceContext ctx) {
        return expressionVisitor.visitVarReference(ctx);
    }

    @Override
    public Expression visitValue(@NotNull ValueContext ctx) {
        return expressionVisitor.visitValue(ctx);
    }

    @Override
    public Expression visitFunctionCall(@NotNull FunctionCallContext ctx) {
        return expressionVisitor.visitFunctionCall(ctx);
    }

    @Override
    public Expression visitConstructorCall(@NotNull ConstructorCallContext ctx) {
        return expressionVisitor.visitConstructorCall(ctx);
    }

    @Override
    public Expression visitSupercall(@NotNull SupercallContext ctx) {
        return expressionVisitor.visitSupercall(ctx);
    }

    @Override
    public Expression visitAdd(@NotNull AddContext ctx) {
        return expressionVisitor.visitAdd(ctx);
    }

    @Override
    public Expression visitMultiply(@NotNull MultiplyContext ctx) {
        return expressionVisitor.visitMultiply(ctx);
    }

    @Override
    public Expression visitSubstract(@NotNull SubstractContext ctx) {
        return expressionVisitor.visitSubstract(ctx);
    }

    @Override
    public Expression visitDivide(@NotNull DivideContext ctx) {
        return expressionVisitor.visitDivide(ctx);
    }

    @Override
    public ConditionalExpression visitConditionalExpression(@NotNull ConditionalExpressionContext ctx) {
        return expressionVisitor.visitConditionalExpression(ctx);
    }

    @Override
    public Statement visitForStatement(@NotNull OpiaParser.ForStatementContext ctx) {
        return forStatementVisitor.visitForStatement(ctx);
    }

    @Override
    public Statement visitAssignment(@NotNull OpiaParser.AssignmentContext ctx) {
        return assignmentStatementVisitor.visitAssignment(ctx);
    }
}
