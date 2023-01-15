package info.developia.opia.parsing.visitor.statement;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.ExpressionContext;
import info.developia.opia.OpiaParser.PrintStatementContext;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.node.statement.PrintStatement;
import info.developia.opia.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class PrintStatementVisitor extends OpiaBaseVisitor<PrintStatement> {
    private final ExpressionVisitor expressionVisitor;

    public PrintStatementVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public PrintStatement visitPrintStatement(@NotNull PrintStatementContext ctx) {
        ExpressionContext expressionCtx = ctx.expression();
        Expression expression = expressionCtx.accept(expressionVisitor);
        return new PrintStatement(expression);
    }
}