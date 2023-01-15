package info.developia.opia.parsing.visitor.expression.function;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.NamedArgumentsListContext;
import info.developia.opia.OpiaParser.UnnamedArgumentsListContext;
import info.developia.opia.domain.node.expression.Argument;
import info.developia.opia.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by kuba on 09.05.16.
 */
public class ArgumentExpressionsListVisitor extends OpiaBaseVisitor<List<Argument>> {
    private final ExpressionVisitor expressionVisitor;

    public ArgumentExpressionsListVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public List<Argument> visitUnnamedArgumentsList(@NotNull UnnamedArgumentsListContext ctx) {
        ArgumentExpressionVisitor argumentExpressionVisitor = new ArgumentExpressionVisitor(expressionVisitor);
        return ctx.argument().stream()
                .map(a -> a.accept(argumentExpressionVisitor)).collect(toList());
    }

    @Override
    public List<Argument> visitNamedArgumentsList(@NotNull NamedArgumentsListContext ctx) {
        ArgumentExpressionVisitor argumentExpressionVisitor = new ArgumentExpressionVisitor(expressionVisitor);
        return ctx.namedArgument().stream()
                .map(a -> a.accept(argumentExpressionVisitor)).collect(toList());
    }
}
