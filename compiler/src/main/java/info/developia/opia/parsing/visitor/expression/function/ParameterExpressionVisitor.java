package info.developia.opia.parsing.visitor.expression.function;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.ParameterContext;
import info.developia.opia.OpiaParser.ParameterWithDefaultValueContext;
import info.developia.opia.domain.node.expression.Expression;
import info.developia.opia.domain.node.expression.Parameter;
import info.developia.opia.domain.type.Type;
import info.developia.opia.parsing.visitor.expression.ExpressionVisitor;
import info.developia.opia.util.TypeResolver;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Optional;

/**
 * Created by kuba on 09.05.16.
 */
public class ParameterExpressionVisitor extends OpiaBaseVisitor<Parameter> {

    private final ExpressionVisitor expressionVisitor;

    public ParameterExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public Parameter visitParameter(@NotNull ParameterContext ctx) {
        String name = ctx.ID().getText();
        Type type = TypeResolver.getFromTypeContext(ctx.type());
        return new Parameter(name, type, Optional.empty());
    }

    @Override
    public Parameter visitParameterWithDefaultValue(@NotNull ParameterWithDefaultValueContext ctx) {
        String name = ctx.ID().getText();
        Type type = TypeResolver.getFromTypeContext(ctx.type());
        Expression defaultValue = ctx.defaultValue.accept(expressionVisitor);
        return new Parameter(name, type, Optional.of(defaultValue));
    }
}
