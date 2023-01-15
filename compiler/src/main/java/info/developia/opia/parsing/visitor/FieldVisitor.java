package info.developia.opia.parsing.visitor;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser;
import info.developia.opia.domain.scope.Field;
import info.developia.opia.domain.scope.Scope;
import info.developia.opia.domain.type.Type;
import info.developia.opia.util.TypeResolver;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by kuba on 13.05.16.
 */
public class FieldVisitor extends OpiaBaseVisitor<Field> {

    private final Scope scope;

    public FieldVisitor(Scope scope) {
        this.scope = scope;
    }

    @Override
    public Field visitField(@NotNull OpiaParser.FieldContext ctx) {
        Type owner = scope.getClassType();
        Type type = TypeResolver.getFromTypeContext(ctx.type());
        String name = ctx.name().getText();
        return new Field(name, owner, type);
    }
}
