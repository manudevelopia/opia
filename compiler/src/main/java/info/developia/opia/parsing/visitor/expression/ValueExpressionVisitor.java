package info.developia.opia.parsing.visitor.expression;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.ValueContext;
import info.developia.opia.domain.node.expression.Value;
import info.developia.opia.domain.type.Type;
import info.developia.opia.util.TypeResolver;
import org.antlr.v4.runtime.misc.NotNull;

public class ValueExpressionVisitor extends OpiaBaseVisitor<Value> {

    @Override
    public Value visitValue(@NotNull ValueContext ctx) {
        String value = ctx.getText();
        Type type = TypeResolver.getFromValue(ctx);
        return new Value(type, value);
    }
}