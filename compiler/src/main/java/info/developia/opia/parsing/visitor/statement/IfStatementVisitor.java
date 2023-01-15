package info.developia.opia.parsing.visitor.statement;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.ExpressionContext;
import info.developia.opia.OpiaParser.IfStatementContext;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.node.statement.IfStatement;
import info.developia.opia.domain.node.statement.Statement;
import info.developia.opia.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class IfStatementVisitor extends OpiaBaseVisitor<IfStatement> {
    private final StatementVisitor statementVisitor;
    private final ExpressionVisitor expressionVisitor;

    public IfStatementVisitor(StatementVisitor statementVisitor, ExpressionVisitor expressionVisitor) {
        this.statementVisitor = statementVisitor;
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public IfStatement visitIfStatement(@NotNull IfStatementContext ctx) {
        ExpressionContext conditionalExpressionContext = ctx.expression();
        Expression condition = conditionalExpressionContext.accept(expressionVisitor);
        Statement trueStatement = ctx.trueStatement.accept(statementVisitor);
        if (ctx.falseStatement != null) {
            Statement falseStatement = ctx.falseStatement.accept(statementVisitor);
            return new IfStatement(condition, trueStatement, falseStatement);
        }
        return new IfStatement(condition, trueStatement);
    }
}