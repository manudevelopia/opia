package info.developia.opia.parsing.visitor.expression;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.*;
import info.developia.opia.domain.node.expression.ConditionalExpression;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.scope.Scope;
import info.developia.opia.parsing.visitor.expression.function.CallExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by kuba on 02.04.16.
 */
public class ExpressionVisitor extends OpiaBaseVisitor<Expression> {

    private final ArithmeticExpressionVisitor arithmeticExpressionVisitor;
    private final VariableReferenceExpressionVisitor variableReferenceExpressionVisitor;
    private final ValueExpressionVisitor valueExpressionVisitor;
    private final CallExpressionVisitor callExpressionVisitor;
    private final ConditionalExpressionVisitor conditionalExpressionVisitor;

    public ExpressionVisitor(Scope scope) {
        arithmeticExpressionVisitor = new ArithmeticExpressionVisitor(this);
        variableReferenceExpressionVisitor = new VariableReferenceExpressionVisitor(scope);
        valueExpressionVisitor = new ValueExpressionVisitor();
        callExpressionVisitor = new CallExpressionVisitor(this, scope);
        conditionalExpressionVisitor = new ConditionalExpressionVisitor(this);
    }

    @Override
    public Expression visitVarReference(@NotNull VarReferenceContext ctx) {
        return variableReferenceExpressionVisitor.visitVarReference(ctx);
    }

    @Override
    public Expression visitValue(@NotNull ValueContext ctx) {
        return valueExpressionVisitor.visitValue(ctx);
    }

    @Override
    public Expression visitFunctionCall(@NotNull FunctionCallContext ctx) {
        return callExpressionVisitor.visitFunctionCall(ctx);
    }

    @Override
    public Expression visitConstructorCall(@NotNull ConstructorCallContext ctx) {
        return callExpressionVisitor.visitConstructorCall(ctx);
    }

    @Override
    public Expression visitSupercall(@NotNull SupercallContext ctx) {
        return callExpressionVisitor.visitSupercall(ctx);
    }

    @Override
    public Expression visitAdd(@NotNull AddContext ctx) {

        return arithmeticExpressionVisitor.visitAdd(ctx);
    }

    @Override
    public Expression visitMultiply(@NotNull MultiplyContext ctx) {

        return arithmeticExpressionVisitor.visitMultiply(ctx);
    }

    @Override
    public Expression visitSubstract(@NotNull SubstractContext ctx) {

        return arithmeticExpressionVisitor.visitSubstract(ctx);
    }

    @Override
    public Expression visitDivide(@NotNull DivideContext ctx) {

        return arithmeticExpressionVisitor.visitDivide(ctx);
    }

    @Override
    public ConditionalExpression visitConditionalExpression(@NotNull ConditionalExpressionContext ctx) {
        return conditionalExpressionVisitor.visitConditionalExpression(ctx);
    }
}
