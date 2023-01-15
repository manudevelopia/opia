package info.developia.opia.parsing.visitor.expression.function;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.ArgumentContext;
import info.developia.opia.OpiaParser.NamedArgumentContext;
import info.developia.opia.domain.node.expression.Argument;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Optional;

/**
 * Created by kuba on 09.05.16.
 */
public class ArgumentExpressionVisitor extends OpiaBaseVisitor<Argument> {

    private final ExpressionVisitor expressionVisitor;

    public ArgumentExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public Argument visitArgument(@NotNull ArgumentContext ctx) {
        Expression value = ctx.expression().accept(expressionVisitor);
        return new Argument(value, Optional.empty());
    }

    @Override
    public Argument visitNamedArgument(@NotNull NamedArgumentContext ctx) {
        Expression value = ctx.expression().accept(expressionVisitor);
        String name = ctx.name().getText();
        return new Argument(value, Optional.of(name));
    }

}
