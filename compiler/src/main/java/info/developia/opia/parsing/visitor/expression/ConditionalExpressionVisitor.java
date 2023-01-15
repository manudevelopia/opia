package info.developia.opia.parsing.visitor.expression;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.ConditionalExpressionContext;
import info.developia.opia.OpiaParser.ExpressionContext;
import info.developia.opia.domain.CompareSign;
import info.developia.opia.domain.node.expression.ConditionalExpression;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.node.expression.Value;
import info.developia.opia.domain.type.BultInType;
import org.antlr.v4.runtime.misc.NotNull;

public class ConditionalExpressionVisitor extends OpiaBaseVisitor<ConditionalExpression> {
    private final ExpressionVisitor expressionVisitor;

    public ConditionalExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public ConditionalExpression visitConditionalExpression(@NotNull ConditionalExpressionContext ctx) {
        ExpressionContext leftExpressionCtx = ctx.expression(0);
        ExpressionContext rightExpressionCtx = ctx.expression(1);
        Expression leftExpression = leftExpressionCtx.accept(expressionVisitor);
        Expression rightExpression = rightExpressionCtx != null ? rightExpressionCtx.accept(expressionVisitor) : new Value(BultInType.INT, "0");
        CompareSign cmpSign = ctx.cmp != null ? CompareSign.fromString(ctx.cmp.getText()) : CompareSign.NOT_EQUAL;
        return new ConditionalExpression(leftExpression, rightExpression, cmpSign);
    }
}