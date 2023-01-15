package info.developia.opia.parsing.visitor.statement;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.ReturnVoidContext;
import info.developia.opia.OpiaParser.ReturnWithValueContext;
import info.developia.opia.domain.node.expression.EmptyExpression;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.node.statement.ReturnStatement;
import info.developia.opia.domain.type.BultInType;
import info.developia.opia.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class ReturnStatementVisitor extends OpiaBaseVisitor<ReturnStatement> {
    private final ExpressionVisitor expressionVisitor;

    public ReturnStatementVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public ReturnStatement visitReturnVoid(@NotNull ReturnVoidContext ctx) {
        return new ReturnStatement(new EmptyExpression(BultInType.VOID));
    }

    @Override
    public ReturnStatement visitReturnWithValue(@NotNull ReturnWithValueContext ctx) {
        Expression expression = ctx.expression().accept(expressionVisitor);
        return new ReturnStatement(expression);
    }
}