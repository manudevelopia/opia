package info.developia.opia.parsing.visitor.expression;

import info.developia.opia.OpiaBaseVisitor;
import info.developia.opia.OpiaParser.VarReferenceContext;
import info.developia.opia.domain.node.expression.FieldReference;
import info.developia.opia.domain.node.expression.LocalVariableReference;
import info.developia.opia.domain.node.expression.Reference;
import info.developia.opia.domain.scope.Field;
import info.developia.opia.domain.scope.LocalVariable;
import info.developia.opia.domain.scope.Scope;
import org.antlr.v4.runtime.misc.NotNull;

public class VariableReferenceExpressionVisitor extends OpiaBaseVisitor<Reference> {
    private final Scope scope;

    public VariableReferenceExpressionVisitor(Scope scope) {
        this.scope = scope;
    }

    @Override
    public Reference visitVarReference(@NotNull VarReferenceContext ctx) {
        String varName = ctx.getText();
        if (scope.isFieldExists(varName)) {
            Field field = scope.getField(varName);
            return new FieldReference(field);
        }
        LocalVariable variable = scope.getLocalVariable(varName);
        return new LocalVariableReference(variable);
    }
}