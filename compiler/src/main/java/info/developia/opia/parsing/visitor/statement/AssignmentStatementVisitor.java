package info.developia.opia.parsing.visitor.statement;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.node.statement.Assignment;
import info.developia.opia.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class AssignmentStatementVisitor extends OpiaBaseVisitor<Assignment> {
    private final ExpressionVisitor expressionVisitor;

    public AssignmentStatementVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public Assignment visitAssignment(@NotNull OpiaParser.AssignmentContext ctx) {
        OpiaParser.ExpressionContext expressionCtx = ctx.expression();
        Expression expression = expressionCtx.accept(expressionVisitor);
        String varName = ctx.name().getText();
        return new Assignment(varName, expression);
    }
}